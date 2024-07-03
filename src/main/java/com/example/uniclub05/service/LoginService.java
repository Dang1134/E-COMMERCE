package com.example.uniclub05.service;

import com.example.uniclub05.entity.UserEntity;
import com.example.uniclub05.payload.request.SignupRequest;
import com.example.uniclub05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    public boolean inserUser(SignupRequest signupRequest){
        boolean isSuccess =  false ;

        String passwordEncoded = passwordEncoder.encode(signupRequest.getPassword()); // ma hoa password
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPassword(passwordEncoded);
        //userEntity.setId(2);

        try{
            userRepository.save(userEntity);
            isSuccess = true ;
        }catch ( Exception e){
            System.out.println("Error insert user ");
        }
        return isSuccess ;
    }

    public UserEntity finbyEmail(String email){
        return userRepository.findByEmail(email);
    }
}
