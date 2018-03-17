package controller;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.User;
import by.forecasts.service.ForecastService;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("match")
public class UpdateForecastsAndMatchesController {

    private final MatchService matchService;
    private final ForecastService forecastService;

    @Autowired
    public UpdateForecastsAndMatchesController(MatchService matchService, ForecastService forecastService) {
        this.matchService = matchService;
        this.forecastService = forecastService;
    }

    @GetMapping("/match")
    public String showMatch(Long matchId, Model model, @AuthenticationPrincipal UserDetailDto user) {
        MatchHardViewDto match = matchService.findById(matchId, user.getId());
        model.addAttribute("match", match);
        return "show_match";
    }

    @PostMapping("/match/addForecast")
    public String addForecast(@ModelAttribute("match") MatchHardViewDto match, Model model) {
        model.addAttribute("score", new MatchScore());
        return "save_forecast";
    }

    @PostMapping("/match/saveForecast")
    public String saveForecast(@ModelAttribute("score")  MatchScore score,
                               @ModelAttribute("match") MatchHardViewDto match,
                               @AuthenticationPrincipal UserDetailDto user,
                               Model model) {
        forecastService.saveForecast(score, match.getId(), user.getId());

        return "redirect: /match?matchId=" + match.getId();
    }

    @PostMapping("/match/saveForecast")
    public String saveMatchScore(@ModelAttribute("score")  MatchScore score,
                               @ModelAttribute("match") MatchHardViewDto match,
                               @AuthenticationPrincipal UserDetailDto user,
                               Model model) {
        forecastService.saveForecast(score, match.getId(), user.getId());

        return "redirect: /match?matchId=" + match.getId();
    }



}
