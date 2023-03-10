package com.Website.Step2.controller;
import com.Website.Step2.dto.AuthenticationResponse;


import com.Website.Step2.dto.LoginRequest;
import com.Website.Step2.dto.RegisterRequest;
import com.Website.Step2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

//    @GetMapping("accountVerification/{token}")
//    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
//        authService.verifyAccount(token);
//        return new ResponseEntity<>("Account Activated Successully", OK);
//    }

//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('Admin')")
//    public ResponseEntity<String> admin() {
//        return new ResponseEntity<>("Admin page",OK);
//    }
//    @GetMapping("/manager")
//    @PreAuthorize("hasRole('Manager')")
//    public ResponseEntity<String> manager() {
//        return new ResponseEntity<>("Manager page",OK);
//    }
//    @GetMapping("/user")
//    @PreAuthorize("hasRole('User')")
//    public ResponseEntity<String> user() {
//        return new ResponseEntity<>("User page",OK);
//    }
}