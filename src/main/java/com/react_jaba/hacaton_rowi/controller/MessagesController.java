package com.react_jaba.hacaton_rowi.controller;

import com.react_jaba.hacaton_rowi.entity.ChatMessage;
import com.react_jaba.hacaton_rowi.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesController {
    private MessageService messageService;
    @GetMapping("/{id}")
    public ResponseEntity<List<ChatMessage>> getByChatId(@PathVariable long id) {
        return ResponseEntity.ok(messageService.getByChatId(id));
    }

    @PostMapping("/addMessage")
    public void postMessage(@RequestBody ChatMessage message) {
        messageService.saveMessage(message);
    }

}
