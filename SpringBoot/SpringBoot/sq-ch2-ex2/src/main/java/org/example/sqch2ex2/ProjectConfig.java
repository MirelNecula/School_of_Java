package org.example.sqch2ex2;

import main.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    String hello(){
        return "hello";
    }

    @Bean
    Integer number() {
        return 42;
    }
}
