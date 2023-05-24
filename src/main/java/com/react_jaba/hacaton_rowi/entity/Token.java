package com.react_jaba.hacaton_rowi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {
    private String access_token, refresh_token, token_type, session_state, scope;
    private int expires_in, refresh_expires_in;
}
