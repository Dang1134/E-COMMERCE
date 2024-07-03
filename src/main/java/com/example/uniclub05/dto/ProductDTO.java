package com.example.uniclub05.dto;

import lombok.Data;

import java.util.List;

@Data

public class ProductDTO {
    private int id ;
    private String name;
    private double price ;
    private List<String> images ;
    private int quantity;
    private int idSize;
    private int idColor;
}
