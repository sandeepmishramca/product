package com.example.demo.services;

import com.example.demo.dtos.ProductRequestDto;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDbService")
public class ProductDBService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductDBService(ProductRepository productRepository
    , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundExcepton {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundExcepton("Product not found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setCategory(getCategoryFromDB(productRequestDto.getCategoryName()));
        return productRepository.save(product);
    }

    @Override
    public Product partialUpdate(Long id, ProductRequestDto productRequestDto) {
        Optional<Product> productOptional = productRepository.findById(id);
//        if(productOptional.isPresent()) {
//            throw new ProductNotFoundExcepton("Product that you want to update is not found");
//        }
        Product product = productOptional.get();

        if(product.getTitle() != null) {
            product.setTitle(productRequestDto.getTitle());
        }
        if(product.getDescription() != null) {
            product.setDescription(productRequestDto.getDescription());

        }
        if(product.getPrice() != null) {
            product.setPrice(productRequestDto.getPrice());
        }
        if(product.getImageUrl() != null) {
            product.setImageUrl(productRequestDto.getImageUrl());
        }
        if(product.getCategory() != null) {
            product.setCategory(getCategoryFromDB(productRequestDto.getCategoryName()));
        }
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String categoryName){
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if(categoryOptional.isEmpty()){
            Category category = new Category();
            category.setName(categoryName);

//            return categoryRepository.save(category); it will be saved by ORM using CascadeType
            return category;
        }
        return categoryOptional.get();
    }
    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
