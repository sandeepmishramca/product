package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

}
