package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Group;
import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.service.GroupService;
import by.forecasts.service.PlayoffGroupService;
import by.forecasts.service.RegularGroupService;
import by.forecasts.service.TeamService;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"tournament"})
public class GroupController {

    private final GroupService groupService;
    private final PlayoffGroupService playoffGroupService;
    private final RegularGroupService regularGroupService;
    private final TournamentService tournamentService;
    private final TeamService teamService;

    @Autowired
    public GroupController(GroupService groupService, PlayoffGroupService playoffGroupService, RegularGroupService regularGroupService, TournamentService tournamentService, TeamService teamService) {
        this.groupService = groupService;
        this.playoffGroupService = playoffGroupService;
        this.regularGroupService = regularGroupService;
        this.tournamentService = tournamentService;
        this.teamService = teamService;
    }

    @GetMapping("/tournament/groups")
    public String showGroups(Model model, Long trId) {
        List<Group> groups = groupService.getAllGroupsByTournamentID(trId);
        TournamentShortViewDto tournament = tournamentService.findOne(trId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("groups", groups);
        return "show_groups";
    }

    @PostMapping("/tournament/groups")
    public String createNewGroup(int groupTypeId) {
        return groupTypeId == 1
                ? "redirect: /tournament/groups/createRegularGroup"
                : "redirect: /tournament/groups/createPlayoffGroup";
    }

    @GetMapping("/tournament/groups/createRegularGroup")
    public String createRegularGroup(Model model) {
        RegularGroup regularGroup = new RegularGroup();
        model.addAttribute("regularGroup", regularGroup);
        return "new_regular_group";
    }

    @PostMapping("/tournament/groups/createRegularGroup")
    public String saveRegularGroup(RegularGroup regularGroup, @ModelAttribute("tournament") TournamentShortViewDto tournament) {
        regularGroupService.save(regularGroup, tournament.getId());
        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }

    @GetMapping("/tournament/groups/createPlayoffGroup")
    public String createPlayoffGroup(Model model) {
        PlayoffGroup playoffGroup = new PlayoffGroup();
        model.addAttribute("playoffGroup", playoffGroup);
        return "new_playoff_group";
    }

    @PostMapping("/tournament/groups/createPlayoffGroup")
    public String savePlayoffGroup(PlayoffGroup playoffGroup, @ModelAttribute("tournament") TournamentShortViewDto tournament) {
        playoffGroupService.save(playoffGroup, tournament.getId());
        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }

    @GetMapping("/group")
    public String showGroup(Model model, Long id, @ModelAttribute("tournament") TournamentShortViewDto tournament) {
        Group group = groupService.findOne(id);
        if (group instanceof RegularGroup) {
            RegularGroup regularGroup = (RegularGroup) group;
            model.addAttribute("regGroup", regularGroup);
            model.addAttribute("teams", teamService.findAllRegularTeamsNotInUseInTournament(tournament.getId()));
            return "show_regular_group";
        }
        if (group instanceof PlayoffGroup) {
            PlayoffGroup playoffGroup = (PlayoffGroup) group;
            model.addAttribute("offGroup", playoffGroup);
            model.addAttribute("teams", teamService.findAllPlayoffTeamsNotInUseInTournament(tournament.getId()));
            return "show_playoff_group";
        }
        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }

    @PostMapping("/group")
    public String addTeam(@ModelAttribute("tournament") TournamentShortViewDto tournament, Long id, Long newTeam) {
        System.out.println(id);
        System.out.println(newTeam);



        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }
}
