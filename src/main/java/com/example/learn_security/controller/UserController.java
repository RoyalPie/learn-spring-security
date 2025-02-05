package com.example.learn_security.controller;

import com.example.learn_security.entity.UserInfo;
import com.example.learn_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("WELCOME " + userDetails.getUsername());
    }
}
