package com.example.demo.dtos;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    //it should be extact match with 3rd party API response
    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setPrice(Double.valueOf(this.price));
        product.setDescription(this.description);
        product.setImageUrl(this.image);

        Category category1 = new Category();
        category1.setName(this.category);

        product.setCategory(category1);

        return product;
    }
}
