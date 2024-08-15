package com.example.demo.controllers;

import com.example.demo.dtos.*;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

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

    @PatchExchange("/product/{id}")
    public ResponseEntity<ProductResponseDto> partialUpdateProduct(@PathVariable Long id,@RequestBody ProductRequestDto productRequestDto){
        Product product = productService.partialUpdate(id, productRequestDto);
        ResponseEntity<ProductResponseDto> responseEntity =  new ResponseEntity<>(ProductResponseDto.from(product),HttpStatusCode.valueOf(200));
        return responseEntity;
    }
    public void updateProduct(){}
    public void deleteProduct(){}

}
