package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Group;
import by.forecasts.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("tournamentId")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/tournament/groups")
    public String showGroups(Model model, Long trId) {
        List<Group> groups = groupService.getAllGroupsByTournamentID(trId);
        model.addAttribute("tournamentId", trId);
        model.addAttribute("groups", groups);
        return "show_groups";
    }

    @PostMapping("/tournament/groups")
    public String createNewGroup(Model model) {
        Long tournamentId = (Long) model.asMap().get("tournamentId");
        return "redirect: /tournament/groups?trId=" + tournamentId;
    }

    @GetMapping("/group")
    public String configGroup(Model model, Long id) {
        Group group = groupService.findOne(id);
        model.addAttribute("group", group);
        return "show_group";
    }
}
