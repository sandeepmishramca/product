package com.example.demo.services;

import com.example.demo.dtos.CreateProductRequestDto;
import com.example.demo.dtos.ProductRequestDto;
import com.example.demo.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    public List<Product> getAllProducts();
    //public Product createProduct(String title, String description, Double price, String imageUrl,String categoryName);

    Product createProduct(ProductRequestDto productRequestDto);
    //we do not take DTOs in services, keep servies generics
    // take all parameters or create a separate
    // take a product , to reduce parameters
    // can be use
}
