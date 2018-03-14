package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logout";
    }
}
