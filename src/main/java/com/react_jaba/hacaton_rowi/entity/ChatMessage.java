package com.react_jaba.hacaton_rowi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @Column (name = "chat")
    private long chatId;

    private String text;

    private Timestamp date;
}
