package com.example.demo.repositories;

public class JPAQuery {
    public static final String GET_PRODUCT_BY_CATEGORY_NAME = "select p from Product p where p.category.name = :categoryName";
}
