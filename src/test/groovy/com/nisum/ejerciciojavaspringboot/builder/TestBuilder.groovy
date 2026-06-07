package com.nisum.ejerciciojavaspringboot.builder

import com.nisum.ejerciciojavaspringboot.controller.dto.ConsultaUsuarioResponseDto
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioRequestDto
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioResponseDto
import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto

import java.time.LocalDateTime

class TestBuilder {

    static LocalDateTime now = LocalDateTime.now();
    static crearUsuarioRequestBuilder() {
        return new NuevoUsuarioRequestDto("Diego Aliaga"
                , "diego.baliagad@gmail.com"
                , "String12")
    }

    static crearTelefonoBuilder() {
        return TelefonoDto.builder()
                .codigoCiudad("9")
                .codigoPais("+56")
                .numero("488503106")
                .build()
    }

    static crearUsuarioResponseBuilder() {
        return NuevoUsuarioResponseDto.builder()
                .created(now)
                .lastLogin(now)
                .lastLogin(now)
                .modified(now)
                .isActive(true)
                .token(UUID.randomUUID()
                        .toString())
                .id(UUID.randomUUID())
                .build()
    }

    static crearConsultaUsuarioResponseBuilder() {
         TelefonoDto tel =TelefonoDto.builder()
                .codigoCiudad("9")
                .codigoPais("+56")
                .numero("488503106")
                .build()

        return ConsultaUsuarioResponseDto.builder()
                .id(UUID.randomUUID())
                .nombre("")
                .email("")
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .telefonos(List.of(tel))
                .build()
    }
}
