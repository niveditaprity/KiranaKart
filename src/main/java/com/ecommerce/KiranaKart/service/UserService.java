package com.ecommerce.KiranaKart.service;

import com.ecommerce.KiranaKart.entity.User;
import com.ecommerce.KiranaKart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    public UserRepository  userRepository;
    public void registerUser(String username, String password) {
        // Check if username already exists
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles("CUSTOMER");

        userRepository.save(user);
    }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }

     public List<String> getAllUsers() {
        List<User>users = userRepository.findAll();

         return users.stream()
                 .map(User::getUsername)
                 .collect(Collectors.toList());
    }

}
