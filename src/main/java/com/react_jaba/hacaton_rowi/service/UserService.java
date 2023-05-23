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
}
