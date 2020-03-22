package com.shop.config;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan({"com.shop"})
@EntityScan("com.shop.model")
@EnableJpaRepositories("com.shop.repository")
@EnableJpaAuditing
public class AppConfig {
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}