package com.nisum.ejerciciojavaspringboot.controller.dto;


import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class NuevoUsuarioRequestDto {

    @NotBlank
    @NonNull
    @Schema(example = "Diego Aliaga")
    private String nombre;
    @NotBlank
    @NonNull
    @Schema(example = "diegoaliaga@gmail.com")
    private String email;
    @NotBlank
    @NonNull
    @Schema(example = "Qwerty12")
    private String password;
    @Valid
    @NotEmpty(message = "Debe ingresar al menos un telefono.")
    private List<TelefonoDto> telefonos;
}
