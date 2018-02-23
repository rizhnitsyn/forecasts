package controller;

import by.forecasts.entities.Forecast;
import by.forecasts.service.ForecastService;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"pageCount", "forecasts", "tournamentId", "userId", "matchState", "recordsCnt"})
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

    @ModelAttribute("pageCount")
    public int prepopulatePages() {
        return 0;
    }

    @GetMapping("/listOfForecasts")
    public String getListOfUsersForecasts(Model model, Long pageId) {
        model.addAttribute("tournaments", tournamentService.findAll());
        model.addAttribute("users", userService.findAll());

        if (pageId != null) {
            Page<Forecast> forecastPage = forecastService.getUserForecasts(
                    (Long) model.asMap().get("tournamentId"),
                    (Long) model.asMap().get("userId"),
                    (Long) model.asMap().get("matchState"),
                    (int) model.asMap().get("recordsCnt"),
                     pageId.intValue());
            model.addAttribute("forecasts", forecastPage.getContent());
        }
        return "show_user_forecasts";
    }

    @PostMapping("/listOfForecasts")
    public String postListOfUsersForecasts(Model model, Long tournamentId, Long userId, Long matchState, int recordsCnt) {
        if (matchState == null) {
            matchState = 2L;
        }
        Page<Forecast> forecastPage = forecastService.getUserForecasts(tournamentId, userId, matchState, recordsCnt, 0);

        int totalPages = forecastPage.getTotalPages() == 0 ? 0 : forecastPage.getTotalPages() - 1;

        model.addAttribute("pageCount", totalPages);
        model.addAttribute("forecasts", forecastPage.getContent());
        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("userId", userId);
        model.addAttribute("matchState", matchState);
        model.addAttribute("recordsCnt", recordsCnt);
        return "redirect:/listOfForecasts";
    }




}
