package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchResultsController {


    @GetMapping("/")
    public String showListOfTournaments() {

        return "show_tournament_list_results";
    }
}
