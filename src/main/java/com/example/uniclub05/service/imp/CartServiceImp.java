package com.example.uniclub05.service.imp;

import com.example.uniclub05.dto.ProductDTO;
import com.example.uniclub05.payload.request.CartRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CartServiceImp {
    boolean insertCart (HttpServletRequest request , CartRequest cartRequest);
    List<ProductDTO> getCart(HttpServletRequest request);
}
