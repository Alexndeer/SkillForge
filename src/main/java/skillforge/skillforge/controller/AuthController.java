package skillforge.skillforge.controller;

import org.springframework.web.bind.annotation.*;
import skillforge.skillforge.dto.CreateUserRequest;
import skillforge.skillforge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute CreateUserRequest userRequest, Model model) {
        try {
            userService.create(userRequest);
            return "redirect:/auth/login?success=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}

