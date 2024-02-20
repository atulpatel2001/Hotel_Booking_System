package com.user.service.service;

import com.user.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public User saveUser(User user);

    public List<User> getAllUser();

    public User getUserById(String userId);
}
