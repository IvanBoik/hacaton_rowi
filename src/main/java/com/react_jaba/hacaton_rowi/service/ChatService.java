package com.react_jaba.hacaton_rowi.service;

import com.react_jaba.hacaton_rowi.entity.Chat;
import com.react_jaba.hacaton_rowi.entity.ChatMessage;
import com.react_jaba.hacaton_rowi.entity.Product;
import com.react_jaba.hacaton_rowi.repository.ChatMessageRepository;
import com.react_jaba.hacaton_rowi.repository.ChatRepository;
import com.react_jaba.hacaton_rowi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Chat> getByClientID(long id) {
        var chats = chatRepository.getByClientID(id);

        addLastMessages(chats);
        return chats;
   }

   public List<Chat> getByProductId(long id) {
        var chats = chatRepository.getByProductId(id);
        addLastMessages(chats);
        return chats;
   }

   private void addLastMessages(List<Chat> chats) {
       for (Chat chat : chats) {
           var lastMessage = chatMessageRepository.getLastMessageByChatId(chat.getId());
           if (lastMessage != null)
               chat.setLastMessageText(lastMessage.getText());
           else chat.setLastMessageText("");
       }
   }
}
