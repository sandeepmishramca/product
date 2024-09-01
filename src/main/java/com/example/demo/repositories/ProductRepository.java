package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_NameEquals(String categoryName);

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> getProductBasedOnCategoryNameUsingJPAQuery(@Param("categoryName") String categoryName);
    //ORM does not understand this patter, when you write @Query annotation then ORM will understand
    //however this is JPA Query not actual SQL query you have to learn it, it will do join

    @Query(JPAQuery.GET_PRODUCT_BY_CATEGORY_NAME)
    List<Product> getProductBasedOnCategoryNameUsingJPAQuery2(String categoryName);
    //withoug @Param it will also works

    @Query(value = NativeSQLQuery.GET_PRODUCT_BY_CATEGORY_NAME, nativeQuery = true)
    List<Product> getProductBasedOnCategoryNameUsingSQLQuery(String categoryName);
    //ORM does not understand this patter, when you write @Query annotation then ORM will understand
    //however this is JPA Query not actual SQL query you have to learn it

//    @Query(value = "select * from Product p join Category c on p.category_id=c.id", nativeQuery = true)
//    List<Product> getProductBasedOnCategoryNameUsingSQLQuery();
//

}
