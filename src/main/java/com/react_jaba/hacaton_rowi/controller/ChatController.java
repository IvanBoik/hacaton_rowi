package com.react_jaba.hacaton_rowi.controller;

import com.react_jaba.hacaton_rowi.entity.Chat;
import com.react_jaba.hacaton_rowi.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/by_product_id/{id}")
    public ResponseEntity<List<Chat>> getByProductId(@PathVariable long id) {
        return ResponseEntity.ok(chatService.getByProductId(id));
    }

    @GetMapping("/by_client_id/{id}")
    public ResponseEntity<List<Chat>> getByClientID(@PathVariable long id) {
        return ResponseEntity.ok(chatService.getByClientID(id));
    }
}
