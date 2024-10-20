package com.example.demo.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
//    @LoadBalanced //Client side load balancing
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());

//        RestTemplate restTemplate = new RestTemplate();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(1000);
//////        requestFactory.setReadTimeout(1000);
//        restTemplate.setRequestFactory(requestFactory);
//        return restTemplate;
    }
}
