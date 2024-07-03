package com.example.uniclub05.controller;

import com.example.uniclub05.entity.UserEntity;
import com.example.uniclub05.payload.request.SignupRequest;
import com.example.uniclub05.payload.response.BaseResponse;
import com.example.uniclub05.service.LoginService;
import com.example.uniclub05.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
@CrossOrigin // tat ca domain duoc truy cap vao API
@RestController
@RequestMapping("/login")


public class LoginController {
    @Autowired
    private LoginService loginService ;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper ;


    // Spel : Spring boot express language
    // @PreAuthorize() : Khi goi link thi kiem tra luon quyen truoc khi chay logic code
    // @PostAuthorize() : chay logic code xong moi kiem tra quyen
    @PostMapping("/signin")
    public ResponseEntity <?> signIn(@RequestParam String username , @RequestParam String password){

//        SecretKey secretKey = Jwts.SIG.HS256.key().build(); // tao 1 key
//        String key = Encoders.BASE64.encode(secretKey.getEncoded()); // chuyen doi key thanh 1 chuoi de luu tru
//        System.out.println("kiem tra " + key);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(token);

        UserEntity userEntity = loginService.finbyEmail(username);
        String roleUser = userEntity.getRole().getName();
        // sinh ra token khi dang nhap thanh cong
        String jwtToken = jwtHelper.generateToken(userEntity.getId(),roleUser);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(jwtToken);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity <?> signUp( @Valid SignupRequest signupRequest){

        loginService.inserUser(signupRequest);

        return new ResponseEntity<>("Hello SignUp", HttpStatus.OK);
    }
}
