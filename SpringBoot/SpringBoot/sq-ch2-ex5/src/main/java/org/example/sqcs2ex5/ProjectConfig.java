package org.example.sqcs2ex5;

import main.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ProjectConfig {

    @Bean
    Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    @Primary
    Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Primarul");
        return p;
    }
    @Bean
    Parrot parrot3() {
        Parrot p = new Parrot();
        p.setName("MiKi");
        return p;
    }
}
