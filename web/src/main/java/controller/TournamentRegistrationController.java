package controller;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.utils.UserDetailDto;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("tournament")
public class TournamentRegistrationController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentRegistrationController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournamentRegList")
    public String showActiveTournamentsReadyForRegistration(Model model, @AuthenticationPrincipal UserDetailDto user) {
        List<TournamentShortViewDto> tournaments = tournamentService.getTournamentsFilterByState(1L, user.getId());
        model.addAttribute("tournaments", tournaments);
        return "show_reg_on_tournament";
    }

    @GetMapping("/tournamentReg")
    public String getTournamentById(Model model, Long id, @AuthenticationPrincipal UserDetailDto user) {
        model.addAttribute("tournament", tournamentService.findOne(id, user.getId()));
        return "show_tournament_registration";
    }

}
