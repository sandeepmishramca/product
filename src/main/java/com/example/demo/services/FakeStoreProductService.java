package com.example.demo.services;

import com.example.demo.dtos.*;
import com.example.demo.exceptions.ProductNotFoundExcepton;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(
            RestTemplate restTemplate,
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundExcepton {
        Product productFromCache = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
        if(productFromCache != null) {
            return productFromCache;
        }
        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );
        if (responseDto == null) {
            throw new ProductNotFoundExcepton("Product Not Found!");
        }

        Product product = responseDto.toProduct();
        redisTemplate.opsForValue().set(String.valueOf(id), product);
        return product;
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

    @Override
    public Product partialUpdate(Long id, ProductRequestDto productRequestDto) {
        CreateProductRequestDto createProductRequestDto = productRequestDto.toRequest();
        HttpEntity<CreateProductRequestDto> createProductRequestDtoHttpEntity = new HttpEntity<>(createProductRequestDto);
        ResponseEntity<FakeStoreProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+ id,
                HttpMethod.PATCH,
                createProductRequestDtoHttpEntity, //expect HTTPEntry consit of header , body
                FakeStoreProductResponseDto.class
        );
        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        HttpEntity<Void> requestEnity = new HttpEntity<>(null);
        ResponseEntity<FakeStoreProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+ id,
                HttpMethod.DELETE,
                requestEnity, //expect HTTPEntry consit of header , body
                FakeStoreProductResponseDto.class
        );
        return responseEntity.getBody().toProduct();
    }


}
