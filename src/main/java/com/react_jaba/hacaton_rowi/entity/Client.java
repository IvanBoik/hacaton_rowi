package com.react_jaba.hacaton_rowi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
}
