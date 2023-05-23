package com.react_jaba.hacaton_rowi.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String from;
    private String to;
}
