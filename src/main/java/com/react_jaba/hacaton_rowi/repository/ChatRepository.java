package com.react_jaba.hacaton_rowi.repository;

import com.react_jaba.hacaton_rowi.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(nativeQuery = true, value = "select chat.* from chat " +
                                        "join client on chat.client_id = ?1")
    List<Chat> getByClientID(long id);
}
