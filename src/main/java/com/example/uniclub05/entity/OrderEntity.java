package com.example.uniclub05.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Data

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "address")
    private  String address;
    @Column(name = "phone")
    private String phone ;
    @Column(name = "total")
    private double total;
    @Column(name = "create_date")
    private LocalDateTime createDate ;
    @Column(name = "status")
    private String status ;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private  UserEntity user ;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;

}
