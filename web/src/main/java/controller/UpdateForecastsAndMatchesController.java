package controller;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("match")
public class UpdateForecastsAndMatchesController {

    private final MatchService matchService;

    @Autowired
    public UpdateForecastsAndMatchesController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/match")
    public String showMatch(Long matchId, Model model, @AuthenticationPrincipal UserDetailDto user) {
        MatchHardViewDto match = matchService.findById(matchId, user.getId());
        model.addAttribute("match", match);
        return "show_match";
    }

    @GetMapping("/match/addForecast")
    public String addForecast(Long matchId, @AuthenticationPrincipal UserDetailDto user) {

        MatchHardViewDto match = matchService.findById(matchId, user.getId());
        return "save-forecast";
    }


}
