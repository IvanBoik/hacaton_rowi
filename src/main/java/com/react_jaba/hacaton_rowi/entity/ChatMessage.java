package com.react_jaba.hacaton_rowi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat_message")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "from")
    private User from;

    @OneToOne
    @JoinColumn(name = "to")
    private User to;

    private String text;

    private Timestamp date;
}
