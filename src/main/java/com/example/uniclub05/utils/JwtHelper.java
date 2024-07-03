package com.example.uniclub05.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${jwt.private-key}")
    private String key ;
    private long plusTime =  8 * 60 * 60 * 1000 ;
    public String generateToken(int id , String data){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)); // chuyen doi 1 chuoi thanh 1 doi tuong
        // tao thoi gian
        Date currentDate = new Date();
        long futureTime = currentDate.getTime() + plusTime ; // muon su dung bai toan tinh thoi gian thi doi qua minisecond
        Date futureDate = new Date(futureTime);

        String token = Jwts.builder()
                .subject(data)
                .id(String.valueOf(id))
                .expiration(futureDate)
                .signWith(secretKey)
                .compact();
        return token ;
    }

    public int getIdUserFromToken(HttpServletRequest request){

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        try{
            String headerAuthor = request.getHeader("Authorization");
            String token = headerAuthor.substring(7);

            String id = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getId();
            return Integer.parseInt(id);
        }catch (Exception e ){
            throw new RuntimeException("Error" + e.getMessage());
        }

    }

    public String decodeToken (String token ) {
        String roleName = "";
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        try{
            roleName = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();

        }catch (Exception e ){
            System.out.println("Error decode " + e.getMessage());
        }

        return roleName;
    }
}
