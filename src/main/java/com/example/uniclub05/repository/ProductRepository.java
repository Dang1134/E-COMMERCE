package com.example.uniclub05.repository;

import com.example.uniclub05.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<ProductEntity , Integer> {

}
