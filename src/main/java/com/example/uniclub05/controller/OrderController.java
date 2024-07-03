package com.example.uniclub05.controller;

import com.example.uniclub05.payload.request.OrderRequest;
import com.example.uniclub05.service.imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp ;

    @PostMapping
    public ResponseEntity<?> insertOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest){
        orderServiceImp.insertOrder(request, orderRequest);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
