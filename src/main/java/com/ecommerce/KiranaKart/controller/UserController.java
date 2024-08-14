package com.ecommerce.KiranaKart.controller;


import com.ecommerce.KiranaKart.dto.AuthenticationRequest;
import com.ecommerce.KiranaKart.dto.AuthenticationResponse;
import com.ecommerce.KiranaKart.dto.UserRegistrationDto;
import com.ecommerce.KiranaKart.entity.User;
import com.ecommerce.KiranaKart.facade.UserFacade;
import com.ecommerce.KiranaKart.service.UserService;
import com.ecommerce.KiranaKart.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserFacade userFacade;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public UserService userService;

    @Autowired
    public JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@RequestBody UserRegistrationDto userRegistrationDto) {
        String username = userRegistrationDto.getUsername();
        String password = userRegistrationDto.getPassword();

        try {
            userFacade.registerUser(username, password);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @GetMapping("/list")
    public List<String> getUserList() {
        // Ensure the request is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return userService.getAllUsers();
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

}
