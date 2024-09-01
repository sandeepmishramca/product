package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
//    private Long id;
    private String name;

    @OneToMany(mappedBy = "category") //Default is FetchType.LAZY
    private List<Product> products; //By default list is lazily loaded
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER) //Default is FetchType.EAGER

//    @Fetch(FetchMode.JOIN)
//    private List<Product> products; //By default list is lazily loaded
//    @Fetch(FetchMode.SELECT)
//    private List<Product> products; //By default list is lazily loaded
//    @Fetch(FetchMode.SUBSELECT)
//    private List<Product> products; //By default list is lazily loaded

}
