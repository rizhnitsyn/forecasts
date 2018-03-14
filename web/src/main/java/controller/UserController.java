package controller;

import by.forecasts.entities.User;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/saveUser")
    public String regUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String regUser(User user) {
        userService.saveUser(user);
        return "/home";
    }
}
