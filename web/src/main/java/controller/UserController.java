package controller;

import by.forecasts.entities.User;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public String showUser(Model model, Long id) {
        model.addAttribute("currentUser", userService.findOne(id));
        model.addAttribute("userStates", userService.findAllUserStates());
        return "show_user";
    }

    @PostMapping("/user")
    public String editUser(Model model,@ModelAttribute("currentUser") User user) {
        System.out.println(user);
        return "show_user";
    }

    @GetMapping("/saveUser")
    public String regUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String regUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "/home";
    }
}
