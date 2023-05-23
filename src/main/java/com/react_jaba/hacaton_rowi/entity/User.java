package com.react_jaba.hacaton_rowi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private char gender;
    private int age;
}