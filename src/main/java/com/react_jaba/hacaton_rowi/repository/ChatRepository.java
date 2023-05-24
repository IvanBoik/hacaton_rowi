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

    @Query(nativeQuery = true, value = "select chat.* from chat " +
            "join manager on chat.manager_id = ?1")
    List<Chat> getByManagerId(long id);

    @Query(nativeQuery = true, value = "select chat.* from chat " +
            "join manager on chat.manager_id = manager.id " +
            "join product on manager.product_id = product.id " +
            "where product.id = ?1")
    List<Chat> getByProductId(long id);
}
