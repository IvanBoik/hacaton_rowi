package com.react_jaba.hacaton_rowi.service;

import com.react_jaba.hacaton_rowi.entity.Chat;
import com.react_jaba.hacaton_rowi.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getByClientID(long id) {
        return chatRepository.getByClientID(id);
    }
}
