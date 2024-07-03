package com.example.uniclub05.controller;

import com.example.uniclub05.payload.request.InsertProductRequest;
import com.example.uniclub05.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")


public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp ;
    @GetMapping
    public ResponseEntity<?> getAllProduct(){



        return new ResponseEntity<>(productServiceImp.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> insertProduct(InsertProductRequest request){
        productServiceImp.insertProduct(request);

        //System.out.println("Kiem tra" + file.getOriginalFilename());


        return new ResponseEntity<>("Insert Product", HttpStatus.OK);
    }

}
