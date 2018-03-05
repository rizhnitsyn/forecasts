package controller;

import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournament")
    public String getTournamentById(Model model, Long id) {
        model.addAttribute("tournament", tournamentService.findOne(id));
        return "show_tournament";
    }

    @GetMapping("/tournamentList")
    public String showTournamentList(Model model, Authentication authentication, Principal principal) {



        return "show_tournament_list";
    }

    @PostMapping("/tournamentList")
    public String createNewTournament() {
        return "new_tournament";
    }
}
