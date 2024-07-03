package com.example.uniclub05.service.imp;

import com.example.uniclub05.dto.ProductDTO;
import com.example.uniclub05.payload.request.InsertProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct (InsertProductRequest request);
    List<ProductDTO> getAll();
}
