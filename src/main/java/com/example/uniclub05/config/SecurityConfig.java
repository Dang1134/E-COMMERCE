package com.example.uniclub05.config;

import com.example.uniclub05.filter.CustomFilterSecurity;
import com.example.uniclub05.sercurity.CustomAuthenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity // Khai bao cho spring boot biet custom sercurity


public class SecurityConfig {
    @Bean
    public PasswordEncoder  passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager (HttpSecurity http , CustomAuthenProvider customAuthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    // Spring Sercurity
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http , CustomFilterSecurity customFilterSecurity ,CorsConfigurationSource corsConfigurationSource ) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource))// bat buoc phai tat
            .authorizeHttpRequests( author -> {
                author.requestMatchers("/login/**","/file/**").permitAll(); // Tu do k can dang nhap
                // ý nghĩa của ** : là quy định tất cả các link sau /login
                author.requestMatchers(HttpMethod.GET, "/product").permitAll();
                author.requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN");

                author.anyRequest().authenticated(); // Tat ca cac link con lai phai chung thuc
            }).addFilterBefore(customFilterSecurity , UsernamePasswordAuthenticationFilter.class) // add filter truoc fiter chung thuc

            .build();
    }
    @Bean // fix loi cors
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
