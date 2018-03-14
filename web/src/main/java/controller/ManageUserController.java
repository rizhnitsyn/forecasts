package controller;

import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class ManageUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ManageUserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public String showUser(Model model, Long id) {
        model.addAttribute("currentUser", userService.findOne(id));
        model.addAttribute("userStates", userService.findAllUserStates());
        model.addAttribute("state", new UserState());
        return "show_user";
    }

    @PostMapping("/user/edit")
    public String editUser(UserState newState, @SessionAttribute("currentUser") User currentUser) {
        currentUser.setUserState(newState);
        userService.updateUser(currentUser);
        return "redirect: /user?id=" + currentUser.getId();
    }

    @PostMapping("/user")
    public String changePassRedirect() {
        return "update_password";
    }

    @PostMapping("/user/changePass")
    public String saveNewPass(String newPass, @SessionAttribute("currentUser") User currentUser) {
        currentUser.setPassword(passwordEncoder.encode(newPass));
        userService.updateUser(currentUser);
        return "redirect: /user?id=" + currentUser.getId();
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
