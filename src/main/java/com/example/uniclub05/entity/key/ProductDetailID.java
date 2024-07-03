package com.example.uniclub05.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor // phuong thuc khoi tao co tham so
@NoArgsConstructor // phuong thuc khoi tao k co tham so
@Embeddable
public class ProductDetailID implements Serializable {
    @Column(name = "id_product")
    private int idProduct ;
    @Column(name = "id_size")
    private int idSize ;
    @Column(name = "id_color")
    private int idColor ;

}
