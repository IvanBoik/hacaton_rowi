package com.react_jaba.hacaton_rowi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "sender")
    private long from;

    @Column(name = "recipient")
    private long to;

    @Column (name = "chat")
    private long chatId;

    @Column (name = "text")
    private String text;

    @Column (name = "date")
    private Timestamp date;
}
