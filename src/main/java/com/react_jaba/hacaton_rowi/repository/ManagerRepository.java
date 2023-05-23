package com.react_jaba.hacaton_rowi.repository;

import com.react_jaba.hacaton_rowi.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
