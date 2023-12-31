package com.api.chat.controller;

import com.api.chat.exec.PasswordErrorException;
import com.api.chat.model.LoginRequest;
import com.api.chat.model.User;
import com.api.chat.exec.NotAuthorizedUser;
import com.api.chat.exec.UserAlreadyException;
import com.api.chat.exec.UserNotfoundException;
import com.api.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws UserAlreadyException {
        return userService.saveUser(user);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public User getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER')")
    public User updateUserData(@RequestBody User user, @RequestParam String email
    , Authentication authentication)
    throws UserNotfoundException, NotAuthorizedUser {
        return userService.updateUser(user,email,authentication);
    }

    @GetMapping("/byId")
    public User getUerById(@RequestParam Integer id) throws UserNotfoundException {
        return userService.getById(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) throws PasswordErrorException, UserNotfoundException {
        return userService.login(loginRequest.getUsername(),loginRequest.getPassword());
    }
}
