package com.example.uniclub05.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "cart")
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;


    @Column(name = "quantity")
    private int quantity ;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product ;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private SizeEntity size ;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private ColorEntity color ;






}
