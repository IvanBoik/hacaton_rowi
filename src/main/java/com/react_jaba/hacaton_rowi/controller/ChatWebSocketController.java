package com.react_jaba.hacaton_rowi.controller;

import com.react_jaba.hacaton_rowi.entity.ChatMessage;
import com.react_jaba.hacaton_rowi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserService userService;

    @MessageMapping("/private-message") // /app/private-message
    public ChatMessage receiveMessage(@Payload ChatMessage message) {
        simpMessagingTemplate.convertAndSendToUser(userService.findById(message.getTo()).getName(), "/private", message); // /user/Ivan/private
        return message;
    }
}