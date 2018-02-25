package controller;

import by.forecasts.entities.Forecast;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.service.ForecastService;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;
import by.forecasts.dto.ForecastFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("forecastFilter")
public class ForecastsController {

    private final TournamentService tournamentService;
    private final UserService userService;
    private final ForecastService forecastService;

    @Autowired
    public ForecastsController(TournamentService tournamentService, UserService userService, ForecastService forecastService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
        this.forecastService = forecastService;
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
        }
        return "show_user_forecasts";
    }

    @PostMapping("/listOfForecasts")
    public String postListOfUsersForecasts(ForecastFilter forecastFilter) {
        return "redirect:/listOfForecasts";
    }
}
