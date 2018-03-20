package controller;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.dto.TournamentShortViewDto;
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
@SessionAttributes("sessionTeam")
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
    public String getListOfTournaments(Long teamId) {
        return "redirect: /team/trList?teamId=" + teamId;
    }

    @GetMapping("/team/trList")
    public String applyTeamFilter(Long teamId, Model model) {
        if (teamId != null) {
            model.addAttribute("tournaments", tournamentService.getTournamentsByTeamParticipant(teamId));
            model.addAttribute("sessionTeam", teamService.findOne(teamId));
        }
        model.addAttribute("teams", teamService.findAll());
        return "show_team_statistic_tr_list";
    }

    @GetMapping("/team/teamStatistics")
    public String showTeamStatistics(Long tournamentId, Long teamId, Model model/*, @ModelAttribute("sessionTeam") Team sessionTeam*/) {
//        List<MatchShortViewDto> matches = matchService.findMatchesOfSelectedTeam(sessionTeam.getId(), tournamentId);
        List<MatchShortViewDto> matches = matchService.findMatchesOfSelectedTeam(teamId, tournamentId);
        model.addAttribute("sessionTeam", teamService.findOne(teamId));
        model.addAttribute("matches", matches);
        model.addAttribute("tournament", tournamentService.findOne(tournamentId));
        return "show_team_statistics";
    }
}
