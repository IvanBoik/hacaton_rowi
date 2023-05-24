package com.react_jaba.hacaton_rowi.repository;

import com.react_jaba.hacaton_rowi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByEmail(String email);

    @Query(nativeQuery = true, value = "select usr.* from usr join client on usr.id = ?1")
    Optional<User> getByClientID(long id);

    @Query(nativeQuery = true, value = "select usr.* from usr join manager on usr.id = ?1")
    Optional<User> getByManagerID(long id);
}
