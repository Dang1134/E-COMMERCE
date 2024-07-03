package com.example.uniclub05.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "color")
@Data
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "name")
    private String name ;

    @OneToMany(mappedBy = "color")
    private List<ProductDetailEntity> productDetails;

    @OneToMany(mappedBy = "color")
    private List<CartEntity> carts ;

    @OneToMany(mappedBy = "color")
    private List<OrderDetailEntity> orderDetails;


}
