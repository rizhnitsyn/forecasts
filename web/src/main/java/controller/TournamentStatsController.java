package controller;


import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.service.PlayoffGroupService;
import by.forecasts.service.RegularGroupService;
import by.forecasts.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TournamentStatsController {

    private final TournamentService tournamentService;
    private final RegularGroupService regularGroupService;
    private final PlayoffGroupService playoffGroupService;

    public TournamentStatsController(TournamentService tournamentService, RegularGroupService regularGroupService, PlayoffGroupService playoffGroupService) {
        this.tournamentService = tournamentService;
        this.regularGroupService = regularGroupService;
        this.playoffGroupService = playoffGroupService;
    }

    @GetMapping("/tournamentStats")
    public String showTeamFilter(Model model) {
        List<Tournament> tournaments = tournamentService.findAll();
        model.addAttribute("tournaments", tournaments);
        return "show_tournament_list_stats";
    }

    @GetMapping("/tournamentStats/results")
    public String showTeamFilter(Model model, Long tournamentId) {
        model.addAttribute("tournament", tournamentService.findById(tournamentId));
        model.addAttribute("regGroups", regularGroupService.findAllByTournamentId(tournamentId));
        model.addAttribute("offGroups", playoffGroupService.findAllByTournamentId(tournamentId));

        return "show_tournament_statistics";
    }

}
