package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    //Id will not send from FE that is why removed Id
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
}
