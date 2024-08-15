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
    private String imageUrl;
    private String categoryName;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);

        Category category = new Category();
        category.setName(this.categoryName);
        product.setCategory(category);
        return product;
    }
}
