package com.ecobank.api.controllers;

import com.ecobank.api.models.account.NewAccountRequest;
import com.ecobank.api.models.authentication.AuthenticateRequest;
import com.ecobank.api.services.AccountService;
import com.ecobank.api.services.AuthenticationService;
import com.ecobank.api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;

    public AccountController(UserService userService, AccountService accountService) {
        this.accountService = accountService;
        this.userService = userService;
    }
    @PostMapping("")
    public Object CreateAccount() {
        var token = SecurityContextHolder.getContext().getAuthentication();
        var account = accountService.createAccountForUser(token.getName(), "EURO");
        return ResponseEntity.ok(account);
    }

//    @PostMapping("")
//    public Object CreateAccount(@RequestBody NewAccountRequest request) {
//        var account = accountService.createAccountForUser(request.getEmail(), "EURO");
//        return ResponseEntity.ok(account);
//    }
}
