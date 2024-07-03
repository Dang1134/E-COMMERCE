package com.example.uniclub05.repository;

import com.example.uniclub05.entity.OrderDetailEntity;
import com.example.uniclub05.entity.key.OrderDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity , OrderDetailID> {
}
