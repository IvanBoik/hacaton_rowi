package com.react_jaba.hacaton_rowi.repository;

import com.react_jaba.hacaton_rowi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
