package com.example.uniclub05.entity;

import com.example.uniclub05.entity.key.ProductDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product_detail")
@Data
public class ProductDetailEntity {
    @EmbeddedId
    private ProductDetailID id ;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price ;

    @ManyToOne
    @JoinColumn(name = "id_product" , insertable = false , updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_size" , insertable = false , updatable = false)
    private SizeEntity size ;

    @ManyToOne
    @JoinColumn(name = "id_color" , insertable = false , updatable = false)
    private ColorEntity color;



}
