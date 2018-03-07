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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@SessionAttributes("tournament")
public class TournamentController {

    private final TournamentService tournamentService;
    private final TeamService teamService;

    @Autowired
    public TournamentController(TournamentService tournamentService, TeamService teamService) {
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
        model.addAttribute("newTournament", new TournamentShortViewDto());
        return "new_tournament";
    }

    @PostMapping("/tournament/create")
    public String saveNewTournamentBt(TournamentShortViewDto tournament) {
        Tournament saved = tournamentService.save(tournament);
        return "redirect: /tournament?id=" + saved.getId();
    }

    @PostMapping("/tournament/reg")
    public String registerOnTournamentBtn(@AuthenticationPrincipal UserDetailDto user, Model model) {
        TournamentShortViewDto tournament = (TournamentShortViewDto) model.asMap().get("tournament");
        tournamentService.registerOnTournament(tournament.getId(), user.getId());
        return "redirect: /tournament?id=" + tournament.getId();
    }

    @PostMapping("/tournament/close")
    public String closeTournamentBtn(Model model) {
        TournamentShortViewDto tournament = (TournamentShortViewDto) model.asMap().get("tournament");
        tournamentService.closeTournament(tournament.getId());
        return "redirect: /tournamentList";
    }

    @PostMapping("/tournament/config")
    public String configTournamentBtn(Model model) {
        TournamentShortViewDto tournament = (TournamentShortViewDto) model.asMap().get("tournament");
        return "redirect: /tournament/groups?trId=" + tournament.getId();
    }

}
