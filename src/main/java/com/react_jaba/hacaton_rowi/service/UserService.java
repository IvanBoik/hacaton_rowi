package com.react_jaba.hacaton_rowi.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.react_jaba.hacaton_rowi.entity.User;
import com.react_jaba.hacaton_rowi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public User findById(long id) {
        var user = userRepository.findById(id).orElse(null);
        var credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();

        if (user == null) {
            var userMap = objectMapping(credentials);

            return null;
        }
        return user;
    }

    private Map<String, Object> objectMapping(Object object) throws ParseException {
        JWT jwt = (JWT) object;
        JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
        return jwtClaimsSet.getClaims();
    }

    private User createUser(Map<String, Object> userMap) {
        var user = new User();
//            user.setId(userMap.get("sub"));
        user.setName(((String) userMap.get("name")).split(" ")[0]);
        user.setSurname(((String) userMap.get("name")).split(" ")[1]);
        user.setEmail((String) userMap.get("email"));

        return user;
    }
}
