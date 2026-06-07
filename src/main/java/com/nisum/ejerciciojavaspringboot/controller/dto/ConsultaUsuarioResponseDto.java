package com.nisum.ejerciciojavaspringboot.controller.dto;

import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaUsuarioResponseDto {

    @Schema(example = "21e64603-a9a2-45d3-b49a-b210c28a0022")
    private UUID id;
    @Schema(example = "Diego Aliaga")
    private String nombre;
    @Schema(example = "diegoaliagad@gmail.com")
    private String email;
    @Schema(example = "2026-06-06 23:17:06.905395")
    private LocalDateTime created;
    @Schema(example = "2026-06-06 23:17:06.905395")
    private LocalDateTime modified;
    @Schema(example = "2026-06-06 23:17:06.905395")
    private LocalDateTime lastLogin;
    @Schema(example = "eyJhbGciOiJIUzM4NCJ9...")
    private String token;
    @Schema(example = "true/false")
    private Boolean isActive;
    private List<TelefonoDto> telefonos;
}

