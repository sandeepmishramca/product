package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query,int pageNumber,int pageSize) {
        Sort sort = Sort.by("title").descending()
                .and(Sort.by("price").ascending());

//        List<String> sortValues = new ArrayList<>();
//        for(String sortValue : sortValues) {
//            sort = Sort.by(sortValue).ascending();
//        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepository.findByTitleContaining(query,pageable);

    }
}
