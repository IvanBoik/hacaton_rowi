package com.react_jaba.hacaton_rowi.service;

import com.react_jaba.hacaton_rowi.entity.User;
import com.react_jaba.hacaton_rowi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email).orElse(null);
    }

    public User getUserByClientID(long id) {
        return userRepository.getByClientID(id).orElse(null);
    }

    public User getUserByManagerID(long id) {
        return userRepository.getByManagerID(id).orElse(null);
    }
}
