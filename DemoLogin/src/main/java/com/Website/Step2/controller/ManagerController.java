package com.Website.Step2.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ManagerController {
    @Autowired
    private final UserDetailsService userDetailsService;
    @GetMapping("/")
    public ResponseEntity<String> info() {
            return new ResponseEntity<>("Hello Page", HttpStatus.OK);

    }

    @GetMapping("/manager")
    public ResponseEntity<String> manager(Principal principal) {
        String username = principal.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("Manager"));
        if (authorized) {
            return new ResponseEntity<>("Manager page", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin(Principal principal) {
        String username = principal.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("Admin"));
        if (authorized) {
            return new ResponseEntity<>("Admin page", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<String> user(Principal principal) {
        String username = principal.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorized = authorities.contains(new SimpleGrantedAuthority("Admin"));
        if (authorized) {
            return new ResponseEntity<>("User page", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
