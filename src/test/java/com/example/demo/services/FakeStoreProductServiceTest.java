package com.example.demo.services;

import com.example.demo.dtos.FakeStoreProductRequestDto;
import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.dtos.ProductRequestDto;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import com.example.demo.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FakeStoreProductServiceTest {
    @Autowired
    private FakeStoreProductService fakeStoreProductService;

    //private RestTemplate restTemplate = Mockito.mock(RestTemplate.class)

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getProductById() throws ProductNotFoundExcepton {
        FakeStoreProductResponseDto expectedFakeStoreProductResponseDto = new FakeStoreProductResponseDto();
        expectedFakeStoreProductResponseDto.setId(1L);
        expectedFakeStoreProductResponseDto.setTitle("Mobile");
        expectedFakeStoreProductResponseDto.setDescription("Mobile");
        expectedFakeStoreProductResponseDto.setPrice("100.0");

        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductResponseDto.class
        )).thenReturn(expectedFakeStoreProductResponseDto);

        Product actual = fakeStoreProductService.getProductById(1L);
        //Assertions.assertEquals(expectedFakeStoreProductResponseDto.getTitle(), actual.getTitle());
        Assertions.assertThrows(ProductNotFoundExcepton.class, () -> actual.getId());
        System.out.println("DEBUG");

    }

    @Test
    void getAllProducts() {

    }

    @Test
    void createProduct() {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle("Mobile");

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle("Mobile");
        productRequestDto.setPrice(100.0);

        FakeStoreProductResponseDto expectedFakeStoreProductResponseDto = new FakeStoreProductResponseDto();
        expectedFakeStoreProductResponseDto.setId(1L);
        expectedFakeStoreProductResponseDto.setDescription("Mobile");
        expectedFakeStoreProductResponseDto.setPrice("100.0");


        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products/"),
                any(),
                eq(FakeStoreProductResponseDto.class)
        )).thenReturn(expectedFakeStoreProductResponseDto);

        Product product = fakeStoreProductService.createProduct(productRequestDto);
        Assertions.assertEquals(expectedFakeStoreProductResponseDto.getTitle(), product.getTitle());

    }

    @Test
    void testCreateProductWhenResponseIsNull() {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle("Mobile");

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle("Mobile");
        productRequestDto.setPrice(100.0);

        FakeStoreProductResponseDto expectedFakeStoreProductResponseDto = new FakeStoreProductResponseDto();
        expectedFakeStoreProductResponseDto.setId(1L);
        expectedFakeStoreProductResponseDto.setDescription("Mobile");
        expectedFakeStoreProductResponseDto.setPrice("100.0");


        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products/"),
                any(),
                eq(FakeStoreProductResponseDto.class)
        )).thenReturn(expectedFakeStoreProductResponseDto);

        Product product = fakeStoreProductService.createProduct(productRequestDto);
//        Assertions.assertEquals(expectedFakeStoreProductResponseDto.getTitle(), product.getTitle());
        //it is checking restTemplate is call, how many times
        verify(restTemplate, times(1)).postForObject(
                eq("https://fakestoreapi.com/products/"),
                any(),
                eq(FakeStoreProductResponseDto.class)

        );

    }

    @Test
    void partialUpdate() {
    }

    @Test
    void deleteProduct() {
    }
}