package com.nisum.ejerciciojavaspringboot.controller;

import com.nisum.ejerciciojavaspringboot.controller.dto.ConsultaUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioRequestDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Desafio Java Nisum", description = "API RestFul para gestion de Usuarios")
@RestController
@RequestMapping(value = "/api/v1.0/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Consultar Usuario",
            description = "Endpoint que permite consultar un usuario existente mediante una peticion GET a la URL especificada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado."),
            @ApiResponse(responseCode = "401", description = "Token Invalido."),
            @ApiResponse(responseCode = "403", description = "Clave secreta demasiado debil."),
            @ApiResponse(responseCode = "409", description = "Correo ya se Encuentra Registrado.")})
    @GetMapping(value = "/consultar/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaUsuarioResponseDto> consultarUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.consultarUsuario(idUsuario));
    }

    @Operation(summary = "Crear un Nuevo Usuario", description = "Endpoint que permite crear un Usuario mediante una peticion POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario Creado Correctamente."),
            @ApiResponse(responseCode = "400", description = "Valores de entrada incorrectos/invalidos."),
            @ApiResponse(responseCode = "409", description = "Correo ya se Encuentra Registrado.")})
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NuevoUsuarioResponseDto> crearUsuario(@Valid @RequestBody NuevoUsuarioRequestDto usuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearUsuario(usuario));
    }
}
