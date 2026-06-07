package com.nisum.ejerciciojavaspringboot.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefonoDto {

    @NotBlank(message = "Debe enviar un numero de telefono.")
    private String numero;
    @NotBlank(message = "Debe enviar un codigo de ciudad.")
    private String codigoCiudad;
    @NotBlank(message = "Debe enviar un numero de pais")
    private String codigoPais;
}
