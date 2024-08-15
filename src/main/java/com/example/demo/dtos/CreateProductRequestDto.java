package com.example.demo.dtos;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
