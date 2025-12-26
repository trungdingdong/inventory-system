package com.example.inventory_system.service;

import com.example.inventory_system.entity.User;
import com.example.inventory_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(String username, String password) {
        // hash password
        String encodedPassword = passwordEncoder.encode(password);

        // them user moi
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);

        // check xem username da ton tai chua
        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("This username is already taken");
        } catch (Exception e) {
            throw new RuntimeException("Server Error");
        }
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new RuntimeException("Please Enter a valid username");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }
        return user;
    }
}
