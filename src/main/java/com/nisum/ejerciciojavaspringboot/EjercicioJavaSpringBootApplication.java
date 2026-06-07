package com.nisum.ejerciciojavaspringboot;

import com.nisum.ejerciciojavaspringboot.config.JwtProperties;
import com.nisum.ejerciciojavaspringboot.config.ValidacionesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        ValidacionesProperties.class,
        JwtProperties.class
})
public class EjercicioJavaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjercicioJavaSpringBootApplication.class, args);
    }

}
