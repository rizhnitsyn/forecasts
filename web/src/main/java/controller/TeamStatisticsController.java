package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Tournament;
import by.forecasts.service.TeamService;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class TeamStatisticsController {

    private final TeamService teamService;
    private final TournamentService tournamentService;

    @Autowired
    public TeamStatisticsController(TeamService teamService, TournamentService tournamentService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/team")
    public String showTeamFilter(Model model) {
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("tournaments", new ArrayList<TournamentShortViewDto>());
        return "show_team_statistic_select_team";
    }

    @PostMapping("/team")
    public String applyTeamFilter(Long teamId, Long tournamentId, Model model) {
        if (teamId != null) {
            model.addAttribute("tournaments", tournamentService.getTournamentsByTeamParticipant(teamId));
        }
        model.addAttribute("teams", teamService.findAll());
        return "show_team_statistic_select_team";
    }

    @GetMapping("/team/teamStatistics")
    public String showTeamStatistics() {

        return "show_team_statistics";
    }
}
