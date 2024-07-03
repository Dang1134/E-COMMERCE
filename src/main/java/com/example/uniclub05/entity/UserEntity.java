package com.example.uniclub05.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "users")
@Data

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "email")
    private String email ;
    @Column(name = "password")
    private String password ;
//    @Column(name = "id_role")
//    private int idRole ;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role ;

    @OneToMany(mappedBy = "user")
    private List<CartEntity> carts ;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

}
