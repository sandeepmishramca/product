package com.example.demo.repositories;

public class NativeSQLQuery {
    public static final String GET_PRODUCT_BY_CATEGORY_NAME = "select p.* from product p join category c on p.category_id = c.id where c.name= :categoryName";
}
