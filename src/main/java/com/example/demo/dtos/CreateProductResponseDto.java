package com.example.demo.dtos;

import com.example.demo.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public CreateProductResponseDto from(Product product) {
        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }
}
