package controller;

import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TournamentController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/tournament")
    public String getTournamentById(Model model) {
        TournamentService bean = context.getBean(TournamentService.class);
        model.addAttribute("tournament", bean.findOne(1L));
        return "show_tournament";
    }
}
