package com.example.demo.controllers;

import com.example.demo.dtos.ProductResponseDto;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("productDbService")    //here we are telling to conroller to mock fakeStoreProductService by default it will got to check two sevice fakeStore and DB service
    private ProductService productService;

    @Test
    public void testGetProductByIdProductIsNull() throws ProductNotFoundExcepton {
        when(productService.getProductById(anyLong())).thenReturn(null);

        ProductResponseDto productResponseDto = productController.getProductById(1L);
        Assertions.assertNull(productResponseDto);
    }

    @Test
    void getProductById() throws ProductNotFoundExcepton {
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Mobile Phone");
        expectedProduct.setDescription("Mobile Phone Description");
        expectedProduct.setPrice(100.0);
        expectedProduct.setImageUrl("https:xyz.com");

        Category category = new Category();
        category.setId(1L);
        category.setName("Mobile Phone");

        expectedProduct.setCategory(category);

        when(productService.getProductById(anyLong())).thenReturn(expectedProduct);

        ProductResponseDto actual = productController.getProductById(1L);

        System.out.println("DEBUG");
        Assertions.assertNull(actual);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedProduct.getId(), actual.getId());
        Assertions.assertEquals(expectedProduct.getTitle(), actual.getTitle());
        Assertions.assertEquals(expectedProduct.getDescription(), actual.getDescription());

    }

    @Test
    void getProductByIdWhenCategoryNameIsNull() throws ProductNotFoundExcepton {
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Mobile Phone");
        expectedProduct.setDescription("Mobile Phone Description");
        expectedProduct.setPrice(100.0);
        expectedProduct.setImageUrl("https:xyz.com");

        Category category = new Category();
        category.setId(1L);
        category.setName(null);

        expectedProduct.setCategory(category);

        when(productService.getProductById(anyLong())).thenReturn(expectedProduct);

        ProductResponseDto actual = productController.getProductById(1L);

        Assertions.assertEquals(expectedProduct.getCategory().getName(), actual.getCategoryName());

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void partialUpdateProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}