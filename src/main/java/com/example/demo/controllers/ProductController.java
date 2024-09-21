package com.example.demo.controllers;

import com.example.demo.commons.AuthenticationCommons;
import com.example.demo.dtos.*;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("productDbService") ProductService productService
    , AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token
    ) throws ProductNotFoundExcepton {
        //Integration for Autherization for access this api
        UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto == null) {
            throw new RuntimeException("Invalid token");//TODO: proper exception
        }

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
    public ResponseEntity<CreateProductResponseDto> partialUpdateProduct(@PathVariable Long id,@RequestBody ProductRequestDto productRequestDto){
        Product product = productService.partialUpdate(id, productRequestDto);
        CreateProductResponseDto createProductResponseDto =  new CreateProductResponseDto();
        ResponseEntity<CreateProductResponseDto> responseEntity =  new ResponseEntity<>(createProductResponseDto.from(product),HttpStatusCode.valueOf(200));
        return responseEntity;
    }
    public void updateProduct(){}

    @DeleteMapping("/product/{id}")
    public ResponseEntity<CreateProductResponseDto> deleteProduct(@PathVariable Long id){
        Product product = productService.deleteProduct(id);
        CreateProductResponseDto createProductResponseDto =  new CreateProductResponseDto();
        ResponseEntity<CreateProductResponseDto> responseEntity =  new ResponseEntity<>(createProductResponseDto.from(product),HttpStatusCode.valueOf(200));
        return responseEntity;
    }

    //Added my own excepton Handler which will be handle for Product Controller class instance
    // becuase of this annotation spring will call this handler and dispach as response to FE
//    @ExceptionHandler(NullPointerException.class)
//    public ErrorDto nullPointerExceptionHandler(){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Something went wrong");
//        errorDto.setStatus("FAILED");
//        return errorDto;
//    }

}
