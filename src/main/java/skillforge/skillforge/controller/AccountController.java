package skillforge.skillforge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import skillforge.skillforge.dto.UserResponse;
import skillforge.skillforge.service.UserService;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/account/me")
    public String myAccount(Authentication auth, Model model) {
        String email = auth.getName();

        UserResponse user = userService.getByEmail(email);

        model.addAttribute("user", user);
        return "account";
    }
}
