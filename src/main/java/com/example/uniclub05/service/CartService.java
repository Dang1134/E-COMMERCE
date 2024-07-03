package com.example.uniclub05.service;

import com.example.uniclub05.dto.ProductDTO;
import com.example.uniclub05.entity.*;
import com.example.uniclub05.payload.request.CartRequest;
import com.example.uniclub05.repository.CartRepository;
import com.example.uniclub05.service.imp.CartServiceImp;
import com.example.uniclub05.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CartService implements CartServiceImp {
    @Autowired
    private JwtHelper jwtHelper ;
    @Autowired
    private CartRepository cartRepository ;
    @Override
    public boolean insertCart(HttpServletRequest request, CartRequest cartRequest) {

        try{
            int idUser = jwtHelper.getIdUserFromToken(request);
            UserEntity userEntity = new UserEntity();
            userEntity.setId(idUser);

            ProductEntity productEntity = new ProductEntity() ;
            productEntity.setId(cartRequest.getIdProduct());

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(cartRequest.getIdColor());

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(cartRequest.getIdSize());

            CartEntity cartEntity = new CartEntity();
            cartEntity.setUser(userEntity);
            cartEntity.setProduct(productEntity);
            cartEntity.setColor(colorEntity);
            cartEntity.setSize(sizeEntity);
            cartEntity.setQuantity(cartEntity.getQuantity());

            CartEntity cartExist = cartRepository.findByUserIdAndProductIdAndColorIdAndSizeId(idUser,cartRequest.getIdProduct(),cartRequest.getIdColor(),cartRequest.getIdSize());
            if (cartExist != null){
                int oldQuantity = cartExist.getQuantity();
                cartExist.setQuantity(oldQuantity + cartRequest.getQuantity());
                cartRepository.save(cartExist);
            }else {
                cartRepository.save(cartEntity);
            }
            return true;

        }
        catch (Exception e){
            return false ;

        }
    }

    @Override
    public List<ProductDTO> getCart(HttpServletRequest request) {
        int idUser = jwtHelper.getIdUserFromToken(request);
        List<CartEntity> listCart = cartRepository.findByUserId(idUser);
        List<ProductDTO> listProductDTO = new ArrayList<>();
        listCart.forEach(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(item.getProduct().getId());
            productDTO.setName(item.getProduct().getName());

            List<String> images = new ArrayList<>();
            item.getProduct().getProductImages().forEach(itemImage ->{
                images.add("http://localhost:9095/file/" + itemImage.getName());
            });
            productDTO.setImages(images);
            productDTO.setPrice(item.getProduct().getPrice());
            productDTO.setQuantity(item.getQuantity());
            productDTO.setIdColor(item.getColor().getId());
            productDTO.setIdSize(item.getSize().getId());

            listProductDTO.add(productDTO);


        });
        return listProductDTO;
    }
}
