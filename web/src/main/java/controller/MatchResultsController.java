package controller;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.dto.UserDetailDto;
import by.forecasts.service.MatchService;
import by.forecasts.service.TournamentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MatchResultsController {

    private final TournamentService tournamentService;
    private final MatchService matchService;

    public MatchResultsController(TournamentService tournamentService, MatchService matchService) {
        this.tournamentService = tournamentService;
        this.matchService = matchService;
    }

    @GetMapping("/tournament/myList")
    public String showListOfTournaments(@AuthenticationPrincipal UserDetailDto user, Model model) {
        model.addAttribute("tournaments", tournamentService.getTournamentsFilterByUser(user.getId()));
        return "show_tournament_list_results";
    }

    @GetMapping("/tournament/allMatches")
    public String showAllMatchesOfTournament(Long tournamentId, Long pageId, @AuthenticationPrincipal UserDetailDto user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("tournament", tournamentService.findOne(tournamentId));

        List<MatchShortViewDto> matchPage =
                matchService.findAllByTournamentIdUserIdPageable(tournamentId, user.getId(), pageId == null ? 0 : pageId);
        Integer pagesCount = matchPage.stream()
                .map(MatchShortViewDto::getPageCount)
                .findFirst().orElse(0);

        model.addAttribute("matches", matchPage);
        model.addAttribute("totalPages", pagesCount - 1);
        return "show_matches_of_selected_tournament";
    }

    @PostMapping("/tournament/allMatches")
    public String showUserListPageablePost(Long tournamentId, Long pageId) {
        return "redirect: /tournament/allMatches?tournamentId=" + tournamentId + "&pageId=" + pageId;
    }
}
