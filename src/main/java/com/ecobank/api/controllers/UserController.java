package com.ecobank.api.controllers;

import com.ecobank.api.services.AuthenticationService;
import com.ecobank.api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public Object Me() {
        var token = SecurityContextHolder.getContext().getAuthentication();

        var user = userService.getUserByEmail(token.getName());
        if(user == null)
            return ResponseEntity.notFound();
        return ResponseEntity.ok(user);
    }
}

