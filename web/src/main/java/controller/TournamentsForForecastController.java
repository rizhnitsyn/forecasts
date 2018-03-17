package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.MatchService;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TournamentsForForecastController {

    private final TournamentService tournamentService;
    private final MatchService matchService;

    @Autowired
    public TournamentsForForecastController(TournamentService tournamentService, MatchService matchService) {
        this.tournamentService = tournamentService;
        this.matchService = matchService;
    }

    @GetMapping("/forecastTournaments")
    public String showListOfTournamentsForForecast(@AuthenticationPrincipal UserDetailDto user, Model model) {
        List<TournamentShortViewDto> tournaments = tournamentService.getActiveTournamentsFilterByUser(user.getId()).stream()
                .peek(tournament -> {
                      Long count = matchService.findCountOfMatchesAvailableForForecasts(tournament.getId(), user.getId());
                      tournament.setMatchesCount(count);
                })
                .collect(Collectors.toList());
        model.addAttribute("tournaments", tournaments);
        return "show_tournaments_for_forecasts";
    }
}
