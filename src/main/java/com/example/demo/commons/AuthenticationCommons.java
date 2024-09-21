package com.example.demo.commons;

import com.example.demo.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        if(token == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        //it is combination of body and header
        HttpEntity<String> entity = new HttpEntity<>(headers);//here we don't have body , only sending header
        ResponseEntity<UserDto> response = restTemplate.exchange(
                "http://localhost:9000/users/validate",
                HttpMethod.POST,
                entity,
                UserDto.class
        );
        return response.getBody();
    }
}

