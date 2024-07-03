package com.example.uniclub05.repository;

import com.example.uniclub05.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartRepository extends JpaRepository<CartEntity , Integer> {
    //CartEntity findByUserIdAndProductIdAndColorIdAndSizeId(int idUser, int idProduct ,int idColor, int idSize );
    CartEntity findByUserIdAndProductIdAndColorIdAndSizeId(int idUser, int idProduct , int idColor , int idSize);
    List<CartEntity> findByUserId (int idUser);

}
