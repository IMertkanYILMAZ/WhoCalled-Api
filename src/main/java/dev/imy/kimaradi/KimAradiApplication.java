package dev.imy.kimaradi;

import dev.imy.kimaradi.repository.*;
import dev.imy.kimaradi.services.CallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
public class KimAradiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KimAradiApplication.class, args);
    }


    @Bean
    CallService callService(CallDao callDao){
        return new CallService(callDao);
    }

    @Bean
    CallDao CallDao(CallJpaRepository callJpaRepository){
        return new CallDao(callJpaRepository);
    }



}
