package com.example.demo.controllers;

import com.example.demo.dtos.CreateProductRequestDto;
import com.example.demo.dtos.CreateProductResponseDto;
import com.example.demo.dtos.ProductRequestDto;
import com.example.demo.dtos.ProductResponseDto;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> responseDtos = new ArrayList<>();

        for(Product productResponseDtos : products){
            responseDtos.add(ProductResponseDto.from(productResponseDtos));
        }
        return responseDtos;
    }

    // what kind of Request will be use to create add data it should be POST request
    @PostMapping("/product")
    public CreateProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.createProduct(productRequestDto);
        CreateProductResponseDto createProductResponseDto =  new CreateProductResponseDto();
        return createProductResponseDto.from(product);

    }
    public void updateProduct(){}
    public void deleteProduct(){}

}
