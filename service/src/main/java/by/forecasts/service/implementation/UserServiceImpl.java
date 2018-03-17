package by.forecasts.service.implementation;

import by.forecasts.aspects.Loggable;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.dto.UserWithResultsDto;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.Match;
import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import by.forecasts.repositories.ForecastRepository;
import by.forecasts.repositories.UserRepository;
import by.forecasts.repositories.UserStateRepository;
import by.forecasts.service.MatchService;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserStateRepository userStateRepository;
    private final ForecastRepository forecastRepository;
    private final MatchService matchService;
    private static final int RECORDS_ON_PAGE = 20;
    private final int sixPoints = 6;
    private final int fourPoints = 4;
    private final int threePoint = 3;
    private final int onePoint = 1;
    private final int zeroPoint = 0;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStateRepository userStateRepository, ForecastRepository forecastRepository, MatchService matchService) {
        this.userRepository = userRepository;
        this.userStateRepository = userStateRepository;
        this.forecastRepository = forecastRepository;
        this.matchService = matchService;
    }

    @Override
    @Loggable
    public UserDetailDto loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedUser = userRepository.findByLogin(login);
        if (loggedUser == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        List<GrantedAuthority> userRoles = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(loggedUser.getUserState().getUserState());
        userRoles.add(simpleGrantedAuthority);
        UserDetailDto userDetailDto = new UserDetailDto(loggedUser.getLogin(), loggedUser.getPassword(), userRoles);
        userDetailDto.setFirstName(loggedUser.getFirstName());
        userDetailDto.setSecondName(loggedUser.getSecondName());
        userDetailDto.setId(loggedUser.getId());
        return userDetailDto;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setUserState(userStateRepository.getOne(1L));
        return userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<UserState> findAllUserStates() {
        return userStateRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAllPageOrdered(Long pageId) {
        PageRequest pageRequest = new PageRequest(pageId.intValue(), RECORDS_ON_PAGE);
        return userRepository.findAllByOrderByIdDesc(pageRequest);
    }

    @Override
    public List<UserWithResultsDto> getUsersWithStatistic(Long tournamentId) {
        List<User> usersOfTournament = userRepository.findAllByTournamentsId(tournamentId);
        if (usersOfTournament == null) {
            return null;
        }

        return usersOfTournament.stream()
                .map(this::createDto)
                .map(user -> setForecasts(user, tournamentId))
                .map(this::generateStatistics)
                .sorted(Comparator.comparing(UserWithResultsDto::getTotalPoints).reversed())
                .collect(Collectors.toList());
    }

    private UserWithResultsDto generateStatistics(UserWithResultsDto user) {
        user.setTotalPoints(calculateTotalPointsOfUser(user));
        user.setGuessedResultCount(guessedResultsCount(user));
        user.setGuessedWinnersCount(guessedWinnersCount(user));
        user.setGuessedDiffInResultsCount(guessedDiffInResultsCount(user));
        user.setGuessedDrawCount(guessedDrawCount(user));

        return user;
    }

    private UserWithResultsDto setForecasts(UserWithResultsDto dto, Long tournamentId) {
        List<Forecast> forecasts = forecastRepository.findAllByUserIdAndMatchTournamentId(dto.getUserId(), tournamentId);
        dto.setForecasts(forecasts);
        return dto;
    }

    private UserWithResultsDto createDto(User user) {
        UserWithResultsDto dto = new UserWithResultsDto();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSecondName(user.getSecondName());
        return dto;
    }

    private int calculateTotalPointsOfUser(UserWithResultsDto user) {
        return user.getForecasts().stream()
                .filter(forecast -> forecast.getMatch().getMatchFinalResult() != null)
                .map(forecast -> matchService.calculateUserPointsPerMatch(forecast.getMatch().getMatchFinalResult(), forecast.getMatchForecast()))
                .mapToInt(points -> points)
                .sum();
    }

    private int guessedResultsCount(UserWithResultsDto user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getMatch().getMatchFinalResult() != null)
                .filter(forecast ->
                            forecast.getMatchForecast().getFirstResult().intValue() == forecast.getMatch().getMatchFinalResult().getFirstResult().intValue()
                               &&
                            forecast.getMatchForecast().getSecondResult().intValue() == forecast.getMatch().getMatchFinalResult().getSecondResult().intValue())
                 .count();
    }

    private int guessedWinnersCount(UserWithResultsDto user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getMatch().getMatchFinalResult() != null)
                .filter(forecast -> {
                    Integer forecastFirstResult = forecast.getMatchForecast().getFirstResult();
                    Integer forecastSecondResult = forecast.getMatchForecast().getSecondResult();
                    Integer matchFirstResult = forecast.getMatch().getMatchFinalResult().getFirstResult();
                    Integer matchSecondResult = forecast.getMatch().getMatchFinalResult().getSecondResult();

                    return (Integer.compare(forecastFirstResult, forecastSecondResult) == Integer.compare(matchFirstResult, matchSecondResult))
                            &&
                            (forecastFirstResult.intValue() != matchFirstResult.intValue()
                                    || forecastSecondResult.intValue() != matchSecondResult.intValue())
                            &&
                            (forecastFirstResult - forecastSecondResult) != (matchFirstResult - matchSecondResult);
                })
                .count();
    }

    private int guessedDiffInResultsCount(UserWithResultsDto user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getMatch().getMatchFinalResult() != null)
                .filter(forecast -> {
                    Integer forecastFirstResult = forecast.getMatchForecast().getFirstResult();
                    Integer forecastSecondResult = forecast.getMatchForecast().getSecondResult();
                    Integer matchFirstResult = forecast.getMatch().getMatchFinalResult().getFirstResult();
                    Integer matchSecondResult = forecast.getMatch().getMatchFinalResult().getSecondResult();

                    return (((forecastFirstResult - forecastSecondResult) == (matchFirstResult - matchSecondResult))
                            && (matchFirstResult - matchSecondResult) != 0)
                            &&
                            (forecastFirstResult.intValue() !=  matchFirstResult.intValue()
                                    || forecastSecondResult.intValue() != matchSecondResult.intValue());
                })
                .count();
    }

    private int guessedDrawCount(UserWithResultsDto user) {
        return (int) user.getForecasts().stream()
                .filter(forecast -> forecast.getMatch().getMatchFinalResult() != null)
                .filter(forecast -> {
                    Integer forecastFirstResult = forecast.getMatchForecast().getFirstResult();
                    Integer forecastSecondResult = forecast.getMatchForecast().getSecondResult();
                    Integer matchFirstResult = forecast.getMatch().getMatchFinalResult().getFirstResult();
                    Integer matchSecondResult = forecast.getMatch().getMatchFinalResult().getSecondResult();

                    return forecastFirstResult.equals(forecastSecondResult) &&
                           matchFirstResult.equals(matchSecondResult) &&
                           !(forecastFirstResult.equals(matchFirstResult));
                })
                .count();
    }
}
