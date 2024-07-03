package com.example.uniclub05.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration

public class RedisConfig {
    @Value("${redis.host}")
    private String host ;

    @Value("${redis.port}")
    private int port ;

    @Bean
    public LettuceConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);

        return new LettuceConnectionFactory(config);
    }
    @Bean
    @Primary
    public RedisTemplate<String , String > redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String , String > template = new RedisTemplate<>();
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        template.setConnectionFactory(connectionFactory);
        return template ;
    }
}
