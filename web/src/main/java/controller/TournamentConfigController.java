package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.entities.Tournament;
import by.forecasts.service.TeamService;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("tournament")
public class TournamentConfigController {

    private final TournamentService tournamentService;
    private final TeamService teamService;

    @Autowired
    public TournamentConfigController(TournamentService tournamentService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.teamService = teamService;
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
    public String createNewTournamentBtn() {
        return "redirect: /tournament/create";
    }

    @GetMapping("/tournament/create")
    public String createNewTournament(Model model) {
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("newTournament", new Tournament());
        return "new_tournament";
    }

    @PostMapping("/tournament/create")
    public String saveNewTournamentBt(@ModelAttribute("newTournament") @Valid Tournament tournament, Errors errors, Model model) {
        if (errors.getErrorCount() > 0) {
            model.addAttribute("newTournament", tournament);
            model.addAttribute("teams", teamService.findAll());
            return "new_tournament";
        } else {
            Tournament saved = tournamentService.save(tournament);
            return "redirect: /tournament?id=" + saved.getId();
        }
    }

    @PostMapping("/tournament/reg")
    public String registerOnTournamentBtn(@AuthenticationPrincipal UserDetailDto user,
                                          @ModelAttribute("tournament") TournamentShortViewDto tournament) {
        tournamentService.registerOnTournament(tournament.getId(), user.getId());
        return "redirect: /tournament?id=" + tournament.getId();
    }

    @PostMapping("/tournament/close")
    public RedirectView closeTournamentBtn(@ModelAttribute("tournament") TournamentShortViewDto tournament) {
        tournamentService.closeTournament(tournament.getId());
        return new RedirectView("/tournamentList");
//        return "redirect: /tournamentList";
    }

    @PostMapping("/tournament/config")
    public String configTournamentBtn(@ModelAttribute("tournament") TournamentShortViewDto tournament) {
        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }

}
