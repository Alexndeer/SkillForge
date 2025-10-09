package com.skillforge.skillforge.user;

import com.skillforge.skillforge.dto.CreateUserRequest;
import com.skillforge.skillforge.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest req) {

        return ResponseEntity.ok(userService.create(req));
    }

    @GetMapping("/id")
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}
