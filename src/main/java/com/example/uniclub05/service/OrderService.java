package com.example.uniclub05.service;

import com.example.uniclub05.entity.CartEntity;
import com.example.uniclub05.entity.OrderDetailEntity;
import com.example.uniclub05.entity.OrderEntity;
import com.example.uniclub05.entity.UserEntity;
import com.example.uniclub05.entity.key.OrderDetailID;
import com.example.uniclub05.payload.request.OrderRequest;
import com.example.uniclub05.repository.CartRepository;
import com.example.uniclub05.repository.OrderDetailRepository;
import com.example.uniclub05.repository.OrderRepository;
import com.example.uniclub05.service.imp.OrderServiceImp;
import com.example.uniclub05.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtHelper jwtHelper;
    @Transactional
    @Override
    public void insertOrder(HttpServletRequest request, OrderRequest orderRequest) {
        int idUser = jwtHelper.getIdUserFromToken(request);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPhone(orderRequest.getPhone());
        orderEntity.setAddress(orderRequest.getAddress());
        orderEntity.setTotal(orderRequest.getTotal());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(idUser);
        orderEntity.setUser(userEntity);
        orderRepository.save(orderEntity);




        List<CartEntity> carts = cartRepository.findByUserId(idUser);
        carts.forEach(item ->{
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            OrderDetailID id = new OrderDetailID();
            id.setIdOder((orderEntity.getId()));
            id.setIdProduct(item.getProduct().getId());
            id.setIdColor(item.getColor().getId());
            id.setIdSize(item.getSize().getId());
            orderDetailEntity.setId(id);
            orderDetailEntity.setPrice(item.getProduct().getPrice());
            orderDetailEntity.setQuantity(item.getQuantity());

            orderDetailRepository.save(orderDetailEntity);
        });

    }
}
