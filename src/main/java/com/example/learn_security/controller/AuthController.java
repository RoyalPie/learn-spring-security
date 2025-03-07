package com.example.learn_security.controller;

import com.example.learn_security.dto.LoginDto;
import com.example.learn_security.entity.RefreshToken;
import com.example.learn_security.jwt.JwtUtil;
import com.example.learn_security.payload.response.JwtResponse;
import com.example.learn_security.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil tokenProvider;

    @Autowired
    RefreshTokenService refreshTokenService;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(loginRequest.getUsername());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken();
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), loginRequest.getUsername()));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication,HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return new ResponseEntity<>("User logged out successfully! " + SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
    }
}