package com.example.uniclub05.entity;

import com.example.uniclub05.entity.key.OrderDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "order_detail")

public class OrderDetailEntity {

    @EmbeddedId
    private OrderDetailID id ;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private  OrderEntity order ;

    @ManyToOne
    @JoinColumn(name = "id_size" , insertable = false, updatable = false)
    private SizeEntity size;

    @ManyToOne
    @JoinColumn(name = "id_color" , insertable = false, updatable = false)
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "id_product" , insertable = false, updatable = false)
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price ;
}
