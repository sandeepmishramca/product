package com.example.demo.services;

import com.example.demo.controllers.SearchController;
import com.example.demo.dtos.SearchRequestDto;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceTest {
    @Autowired
    private SearchService searchService;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void serachService() {
        searchService = new SearchService(productRepository);
        Page<Product> page = searchService.search("laptop",0,1);
        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());
    }
}