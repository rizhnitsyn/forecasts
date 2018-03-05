package controller;

import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournament")
    public String getTournamentById(Model model) {
        model.addAttribute("tournament", tournamentService.findOne(1L));
        return "show_tournament";
    }

    @GetMapping("/tournamentList")
    public String showTournamentList() {
        return "show_tournament_list";
    }

    @PostMapping("/tournamentList")
    public String createNewTournament() {
        return "new_tournament";
    }
}
