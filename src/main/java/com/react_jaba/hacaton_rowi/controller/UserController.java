package com.react_jaba.hacaton_rowi.controller;


import com.react_jaba.hacaton_rowi.entity.User;
import com.react_jaba.hacaton_rowi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/by_email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/by_client_id/{id}")
    public ResponseEntity<User> getUserByClientID(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserByClientID(id));
    }

//    @GetMapping("/by_manager_id/{id}")
//    public ResponseEntity<User> getUserByManagerID(@PathVariable long id) {
//        return ResponseEntity.ok(userService.getUserByClientID(id));
//    }
//
//    @GetMapping("/by_id/{id}")
//    public ResponseEntity<User> getUserByManagerID(@PathVariable long id) {
//        return ResponseEntity.ok(userService.findById(id));
//    }
}
