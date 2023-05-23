package com.react_jaba.hacaton_rowi.repository;

import com.react_jaba.hacaton_rowi.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM chat_message WHERE chat = ?1")
    List<ChatMessage> getMessagesByChatId(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM chat_message WHERE chat = ?1 ORDER BY date desc LIMIT 1")
    ChatMessage getLastMessageByChatId(long id);
}
