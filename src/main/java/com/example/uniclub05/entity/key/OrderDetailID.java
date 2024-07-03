package com.example.uniclub05.entity.key;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailID implements Serializable {
    @Column(name = "id_order")
    private int idOder;
    @Column(name = "id_product")
    private int idProduct ;
    @Column(name = "id_size")
    private int idSize ;
    @Column(name = "id_color")
    private int idColor ;
}
