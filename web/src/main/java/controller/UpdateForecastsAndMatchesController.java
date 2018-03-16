package controller;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpdateForecastsAndMatchesController {

    private final MatchService matchService;

    @Autowired
    public UpdateForecastsAndMatchesController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/match")
    public String showMatch(Long matchId, Model model) {
        MatchHardViewDto match = matchService.findById(matchId);
        model.addAttribute("match", match);
        return "show_match";
    }
}
