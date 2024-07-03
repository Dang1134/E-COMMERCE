package com.example.uniclub05.payload.request;

import lombok.Data;

@Data

public class CartRequest {

    private int idProduct;
    private int idSize;
    private int idColor ;
    private int quantity ;
}
