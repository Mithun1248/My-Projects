package com.api.chat.service;

import com.api.chat.model.User;
import com.api.chat.execeptions.NotAuthorizedUser;
import com.api.chat.execeptions.UserAlreadyException;
import com.api.chat.execeptions.UserNotfoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User getUserByEmail(String email);
    public List<User> getAllUsers();
    public User saveUser(User user) throws UserAlreadyException;
    public User updateUser(User user, String email, Authentication authentication) throws UserNotfoundException, NotAuthorizedUser;
    public User getById(Integer id) throws UserNotfoundException;
}
