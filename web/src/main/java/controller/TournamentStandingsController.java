package controller;

import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TournamentStandingsController {

    private final TournamentService tournamentService;
    private final UserService userService;

    @Autowired
    public TournamentStandingsController(TournamentService tournamentService, UserService userService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
    }

    @GetMapping("/tournament/standings")
    public String showListOfTournaments(@AuthenticationPrincipal UserDetailDto user, Model model) {
        model.addAttribute("tournaments", tournamentService.getTournamentsFilterByUser(user.getId()));
        return "show_tournament_list_standings";
    }

    @GetMapping("/tournament/resultTable")
    public String showResultTable(Long tournamentId, Model model) {
        model.addAttribute("tournament", tournamentService.findOne(tournamentId));
        model.addAttribute("users", userService.getUsersWithStatistic(tournamentId));
        return "show_tournament_result_table";
    }
}
