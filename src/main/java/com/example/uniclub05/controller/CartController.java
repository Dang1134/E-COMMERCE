package com.example.uniclub05.controller;

import com.example.uniclub05.dto.ProductDTO;
import com.example.uniclub05.payload.request.CartRequest;
import com.example.uniclub05.payload.response.BaseResponse;
import com.example.uniclub05.service.imp.CartServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")

public class CartController {
    @Autowired
    private CartServiceImp cartServiceImp ;
    @PostMapping("")
    public ResponseEntity<?> insertToCart(HttpServletRequest request, @RequestBody CartRequest cartRequest){

        boolean isSuccess = cartServiceImp.insertCart(request, cartRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "them thanh cong" : "them that bai");
        baseResponse.setData(isSuccess);


        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCart(HttpServletRequest request){
        List<ProductDTO> list = cartServiceImp.getCart(request);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(list);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
