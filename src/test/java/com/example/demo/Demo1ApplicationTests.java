package com.example.demo;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Demo1ApplicationTests {
    //spring context is not available in test like all annotation by SpringBootTest provides SC spring context
    // it avoid to call whole api call like controller,service, repository to run DB method
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testProductCallingTwoMethod(){
        Optional<Category> categoryOptional = categoryRepository.findByName("laptops");//first get categoryId
        if (categoryOptional.isEmpty()) {
            System.out.println("Category not found");
        }
        List<Product> products = productRepository.findByCategory(categoryOptional.get()); //get product where categoryId=?
        System.out.println(products);
    }
    @Test
    public void testProductCallingOneMethod(){
        List<Product> products = productRepository.findByCategory_NameEquals("Mobile"); //it trigger Join Query by ORM
        System.out.println(products);
    }

    @Test
    public void testGetProductBasedOnCategoryNameUsingJPAQuery(){
        List<Product> products = productRepository.getProductBasedOnCategoryNameUsingJPAQuery("laptops");
        System.out.println(products);
    }

    @Test
    public void testGetProductBasedOnCategoryNameUsingSQLQuery(){
        List<Product> products = productRepository.getProductBasedOnCategoryNameUsingSQLQuery("laptops");
        System.out.println(products);
    }

    @Test
    @Transactional //To force in one hibernate session, since test does not work in same session/transaction
    public void testGetCategory(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L);
        System.out.println(categoryOptional.get().getProducts());
        System.out.println("debug"); //only one table category will, since by default List is Lazily
    }

    @Test
    public void testGetCategoryUsingFetchModJoin(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testLazyAndJoinAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testLazyAndJoinNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    @Transactional
    public void testLazyAndSelectAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testLazyAndSelectNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    @Transactional
    public void testLazyAndSubSelectAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testLazyAndSubSelectNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testEagerAndJoinAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testEagerAndJoinNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    @Transactional
    public void testEagerAndSelectAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testEagerAndSelectNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    @Transactional
    public void testEagerAndSubSelectAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
        System.out.println(categoryOptional.get().getProducts()); //access product
    }

    @Test
    public void testEagerAndSubSelectNotAccess(){
        Optional<Category> categoryOptional = categoryRepository.findById(1L); //no accessing product
//        System.out.println(categoryOptional.get().getProducts()); //access product
    }


    @Test
    @Transactional
    public void nPlusOneProblem(){
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            for(Product product : category.getProducts()) {
                System.out.println(product.getTitle());
            }
        }
    }

}
