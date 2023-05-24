package com.react_jaba.hacaton_rowi.controller;

import com.react_jaba.hacaton_rowi.entity.LoginRequest;
import com.react_jaba.hacaton_rowi.entity.Token;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/")
    public ResponseEntity<Token> getAccessToken(@RequestBody LoginRequest loginRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", loginRequest.getEmail());
        params.add("password", loginRequest.getPassword());
        params.add("client_id", "chatservice-client");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Token> response = restTemplate.exchange("http://localhost:9090/auth/realms/chatservice-realm/protocol/openid-connect/token", HttpMethod.POST, request, Token.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Token responseBody = response.getBody();
            HttpHeaders httpHeaders1 = new HttpHeaders();
            httpHeaders1.add("Access-Control-Allow-Origin", "*");
            return ResponseEntity.ok(responseBody);
        }

        return null;
    }
}
