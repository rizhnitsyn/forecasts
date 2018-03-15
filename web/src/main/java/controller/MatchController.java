package controller;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Group;
import by.forecasts.entities.Match;
import by.forecasts.entities.Tournament;
import by.forecasts.service.GroupService;
import by.forecasts.service.MatchService;
import by.forecasts.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"tournament", "errorMsg"})
public class MatchController {

    private final MatchService matchService;
    private final GroupService groupService;
    private final TeamService teamService;

    public MatchController(MatchService matchService, GroupService groupService, TeamService teamService) {
        this.matchService = matchService;
        this.groupService = groupService;
        this.teamService = teamService;
    }

    @ModelAttribute("errorMsg")
    public String errorMsg() {
        return "";
    }

    @GetMapping("/matches/calendar")
    public String showMatchesOfGroup(Long grId, Model model, @SessionAttribute("tournament") TournamentShortViewDto tournamentDto) {
        Group group = groupService.findOne(grId);
        List<Match> matches = matchService.findAllByTournamentIdAndGroupId(tournamentDto.getId(), grId);
        model.addAttribute("tournament", tournamentDto);
        model.addAttribute("matches", matches);
        model.addAttribute("group", group);
        return "show_matches_of_group";
    }

    @PostMapping("/matches/calendar")
    public String addMatchToCalendar(Model model, Long grId) {
        MatchShortViewDto match = new MatchShortViewDto();
        match.setGroupId(grId);
        model.addAttribute("newMatch", match);
        model.addAttribute("teams", teamService.findAllTeamsByGroupId(grId));
        return "new_match";
    }

    @PostMapping("/matches/create")
    public String saveMatchToCalendar(@ModelAttribute("newMatch") @Valid MatchShortViewDto match, Errors errors, Model model,
                                      @ModelAttribute("tournament") TournamentShortViewDto tournamentDto) {
        if (errors.getErrorCount() > 0) {
            model.addAttribute("newMatch", match);
            model.addAttribute("teams", teamService.findAllTeamsByGroupId(match.getGroupId()));
            return "new_match";
        } else {
            Tournament tournament = new Tournament();
            tournament.setId(tournamentDto.getId());
            match.setTournament(tournament);
            model.addAttribute("errorMsg", matchService.save(match).getError());
            return "redirect: /matches/calendar?grId=" + match.getGroupId();
        }



    }

}
