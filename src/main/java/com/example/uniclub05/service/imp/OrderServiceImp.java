package com.example.uniclub05.service.imp;

import com.example.uniclub05.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderServiceImp {
    void insertOrder(HttpServletRequest request , OrderRequest orderRequest);

}
