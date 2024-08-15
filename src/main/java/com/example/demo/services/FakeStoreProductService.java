package com.example.demo.services;

import com.example.demo.dtos.CreateProductRequestDto;
import com.example.demo.dtos.FakeStoreProductRequestDto;
import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.dtos.ProductRequestDto;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );
        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductResponseDto[].class //Generic type eressor
        );

        //can be done using stram()
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto : responseDtos) {
            products.add(responseDto.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        CreateProductRequestDto createProductRequestDto=productRequestDto.toRequest();
        FakeStoreProductResponseDto responseDto=restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                createProductRequestDto,
                FakeStoreProductResponseDto.class
        );
        return responseDto.toProduct();
    }
}
