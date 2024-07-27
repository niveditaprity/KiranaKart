package com.ecommerce.KiranaKart.facade;

import com.ecommerce.KiranaKart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;
    public void registerUser(String username, String password) {
        password = passwordEncoder.encode(password);
        userService.registerUser(username,password);

    }
}
