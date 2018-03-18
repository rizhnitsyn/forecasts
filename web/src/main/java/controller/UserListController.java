package controller;

import by.forecasts.entities.User;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserListController {

    private final UserService userService;

    @Autowired
    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userList")
    public String showUserListPageable(Model model, Long pageId) {
        Page<User> userPage = userService.findAllPageOrdered(pageId == null ? 0 : pageId);
        model.addAttribute("userPage", userPage);
        model.addAttribute("totalPages", userPage.getTotalPages() - 1);
        return "show_user_list";
    }

    @PostMapping("userList")
    public String showUserListPageablePost(Long pageId) {
        return "redirect:/userList?pageId=" + pageId;
    }
}
