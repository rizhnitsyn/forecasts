package controller;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Match;
import by.forecasts.entities.Team;
import by.forecasts.service.MatchService;
import by.forecasts.service.TeamService;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("team")
public class TeamStatisticsController {

    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final MatchService matchService;

    @Autowired
    public TeamStatisticsController(TeamService teamService, TournamentService tournamentService, MatchService matchService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.matchService = matchService;
    }

    @GetMapping("/team")
    public String showTeamFilter(Model model) {
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("tournaments", new ArrayList<TournamentShortViewDto>());
        return "show_team_statistic_select_team";
    }

    @PostMapping("/team")
    public String applyTeamFilter(Long teamId, Model model) {
        if (teamId != null) {
            model.addAttribute("tournaments", tournamentService.getTournamentsByTeamParticipant(teamId));
        }
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("team", teamService.findOne(teamId));
        return "show_team_statistic_select_team";
    }

    @GetMapping("/team/teamStatistics")
    public String showTeamStatistics(Long tournamentId, Model model, @ModelAttribute("team") Team team) {
        List<MatchShortViewDto> matches = matchService.findMatchesOfSelectedTeam(team.getId(), tournamentId);
        model.addAttribute("matches", matches);
        model.addAttribute("tournament", tournamentService.findOne(tournamentId));
        return "show_team_statistics";
    }
}
