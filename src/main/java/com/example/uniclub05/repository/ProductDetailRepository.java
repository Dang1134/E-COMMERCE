package com.example.uniclub05.repository;

import com.example.uniclub05.entity.ProductDetailEntity;
import com.example.uniclub05.entity.key.ProductDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, ProductDetailID> {
}
