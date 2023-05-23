package com.react_jaba.hacaton_rowi.service;

import com.react_jaba.hacaton_rowi.entity.ChatMessage;
import com.react_jaba.hacaton_rowi.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getByChatId(long id) {
        return chatMessageRepository.getMessagesByChatId(id);
    }

    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }
}
