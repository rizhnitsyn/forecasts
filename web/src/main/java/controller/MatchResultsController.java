package controller;

import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.MatchService;
import by.forecasts.service.TournamentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchResultsController {

    private final TournamentService tournamentService;
    private final MatchService matchService;

    public MatchResultsController(TournamentService tournamentService, MatchService matchService) {
        this.tournamentService = tournamentService;
        this.matchService = matchService;
    }

    @GetMapping("/tournament/myList")
    public String showListOfTournaments(@AuthenticationPrincipal UserDetailDto user, Model model) {
        model.addAttribute("tournaments",tournamentService.getTournamentsFilterByUser(user.getId()));
        return "show_tournament_list_results";
    }

    @GetMapping("/tournament/allMatches")
    public String showAllMatchesOfTournament(Long tournamentId, @AuthenticationPrincipal UserDetailDto user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("tournament", tournamentService.findOne(tournamentId));
        model.addAttribute("matches", matchService.findAllByTournamentIdUserId(tournamentId, user.getId()));
        return "show_matches_of_selected_tournament";
    }
}
