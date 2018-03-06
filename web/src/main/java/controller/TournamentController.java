package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournament")
    public String getTournamentById(Model model, Long id, @AuthenticationPrincipal UserDetailDto user) {
        model.addAttribute("tournament", tournamentService.findOne(id, user.getId()));
        return "show_tournament";
    }

    @GetMapping("/tournamentList")
    public String showActiveTournamentList(Model model, @AuthenticationPrincipal UserDetailDto user) {
        List<TournamentShortViewDto> tournaments = tournamentService.getTournamentsFilterByState(1L, user.getId());
        model.addAttribute("tournaments", tournaments);
        return "show_tournament_list";
    }

    @PostMapping("/tournamentList")
    public String createNewTournament() {
        return "new_tournament";
    }

    @PostMapping("/tournament/reg")
    public String registerOnTournament() {
        return "show_tournament";
    }

    @PostMapping("/tournament/close")
    public String closeTournament() {
        return "show_tournament";
    }

    @PostMapping("/tournament/config")
    public String configTournament() {
        return "show_tournament";
    }
}
