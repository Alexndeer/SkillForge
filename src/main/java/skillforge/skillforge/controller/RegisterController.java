package skillforge.skillforge.controller;

import skillforge.skillforge.dto.CreateUserRequest;
import skillforge.skillforge.dto.UserResponse;
import skillforge.skillforge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register" )
    public String showForm() {
        return "register";
    }

    @PostMapping("/register" )
    public String register(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            UserResponse user = userService.create(new CreateUserRequest(email, password));
            return "redirect:/account/" + user.id();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/account/{id}" )
    public String account(@PathVariable Long id, Model model) {
        UserResponse user = userService.getById(id);
        model.addAttribute("user", user);
        return "account";
    }
}

