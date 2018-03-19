package controller;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.service.ForecastService;
import by.forecasts.service.MatchService;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;
import by.forecasts.utils.ForecastFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"forecastFilter"})
public class UserForecastsController {

    private final TournamentService tournamentService;
    private final UserService userService;
    private final ForecastService forecastService;
    private final MatchService matchService;

    @Autowired
    public UserForecastsController(TournamentService tournamentService, UserService userService, ForecastService forecastService, MatchService matchService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.forecastService = forecastService;
        this.matchService = matchService;
    }

    @GetMapping("/allUserForecasts")
    public String showUserForecasts(Long userId, @SessionAttribute("tournament") TournamentShortViewDto tournament,
                                    Long pageId, Model model) {
        model.addAttribute("user", userService.findOne(userId));
        model.addAttribute("tournament", tournament);

        List<MatchShortViewDto> matchPage =
                matchService.findAllByTournamentIdUserIdPageable(tournament.getId(), userId, pageId == null ? 0 : pageId);
        Integer pagesCount = matchPage.stream()
                .map(MatchShortViewDto::getPageCount)
                .findFirst().orElse(0);

        model.addAttribute("matches", matchPage);
        model.addAttribute("totalPages", pagesCount - 1);
        return "show_forecasts_of_user";
    }

    @PostMapping("/allUserForecasts")
    public String showUserListPageablePost(Long userId, Long pageId) {
        return "redirect: /allUserForecasts?userId=" + userId + "&pageId=" + pageId;
    }

    @ModelAttribute("forecastFilter")
    public ForecastFilter createFilter() {
        return new ForecastFilter();
    }

    @ModelAttribute("tournaments")
    public List<Tournament> listOfTournaments() {
        return tournamentService.findAll();
    }

    @ModelAttribute("users")
    public List<User> listOfUsers() {
        return userService.findAll();
    }

    @GetMapping("/listOfForecasts")
    public String getListOfUsersForecasts(Model model, ForecastFilter forecastFilter, Long pageId) {
        if (forecastFilter.getTournamentId() != null) {
            forecastFilter.setPageNo(pageId == null ? 0 : pageId.intValue());
            Page<Forecast> forecastPage = forecastService.getUserForecasts(forecastFilter);
            int totalPages = forecastPage.getTotalPages();

            forecastFilter.setPagesCount(totalPages == 0 ? 0 : totalPages - 1);
            model.addAttribute("forecasts", forecastPage.getContent());
            return "show_user_forecasts";
        }
        model.addAttribute("forecasts", new ArrayList<Forecast>());
        return "show_user_forecasts";
    }

    @PostMapping("/listOfForecasts")
    public String postListOfUsersForecasts(ForecastFilter forecastFilter) {
        return "redirect:/listOfForecasts";
    }
}
