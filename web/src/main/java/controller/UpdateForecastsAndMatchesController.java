package controller;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.entities.MatchScore;
import by.forecasts.service.ForecastService;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

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
        return "new_forecast";
    }

    @PostMapping("/match/addScore")
    public String addMatchScore(@ModelAttribute("match") MatchHardViewDto match, Model model) {
        model.addAttribute("score", new MatchScore());
        return "new_match_score";
    }

    @PostMapping("/match/saveForecast")
    public String saveForecast(@ModelAttribute("score") @Valid  MatchScore score, Errors errors,
                               @ModelAttribute("match") MatchHardViewDto match,
                               @SessionAttribute("tournament") TournamentShortViewDto tournament,
                               @AuthenticationPrincipal UserDetailDto user) {
        if (errors.getErrorCount() > 0) {
            return "new_forecast";
        } else {
            forecastService.saveForecast(score, match.getId(), user.getId());
            return "redirect: /forecastMatches?trId=" + tournament.getId();
        }
    }

    @PostMapping("/match/saveMatchScore")
    public String saveMatchScore(@ModelAttribute("score") @Valid MatchScore score, Errors errors,
                                 @ModelAttribute("match") MatchHardViewDto match) {
        if (errors.getErrorCount() > 0) {
            return "new_match_score";
        } else {
            matchService.addMatchScore(match.getId(), score);
            return "redirect: /match?matchId=" + match.getId();
        }
    }
}
