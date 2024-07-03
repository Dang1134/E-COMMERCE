package com.example.uniclub05.repository;

import com.example.uniclub05.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductImageRepository extends JpaRepository<ProductImageEntity , Integer> {
}
