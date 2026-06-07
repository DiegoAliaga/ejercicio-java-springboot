package com.nisum.ejerciciojavaspringboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties("app.validation")
public class ValidacionesProperties {

    private String emailRegex;

    private String passwordRegex;

    private String telefonoRegex;
}
