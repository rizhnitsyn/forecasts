package controller;

import by.forecasts.config.ApplicationContextHolder;
import by.forecasts.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TournamentController {

    @GetMapping("/tournament")
    public String getTournamentById(Model model) {
        TournamentService tournamentService = ApplicationContextHolder.getBean(TournamentService.class);
        model.addAttribute("tournament", tournamentService.findOne(1L));
        return "show_tournament";
    }
}
