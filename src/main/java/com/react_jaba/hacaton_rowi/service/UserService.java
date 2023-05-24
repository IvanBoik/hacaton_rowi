package com.react_jaba.hacaton_rowi.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.react_jaba.hacaton_rowi.entity.User;
import com.react_jaba.hacaton_rowi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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

    public User findById(String id) {
        var user = userRepository.findById(id).orElse(null);
        var authDetails = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();

        if (user == null) {
            /*
            Парсинг токена, создание нового пользователя, добавление в БД
             */
            return null;
        }
        return user;
    }

    private Map<String, Object> objectMapping(String object) throws ParseException {
        JWT jwt = JWTParser.parse(object);
        JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
        return jwtClaimsSet.getClaims();
    }
}
