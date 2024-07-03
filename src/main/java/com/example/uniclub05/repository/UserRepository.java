package com.example.uniclub05.repository;

import com.example.uniclub05.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmail (String email);
}
