package com.nisum.ejerciciojavaspringboot.service;


import com.nisum.ejerciciojavaspringboot.controller.dto.ConsultaUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioRequestDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioResponseDto;
import java.util.UUID;


public interface UsuarioService {

    ConsultaUsuarioResponseDto consultarUsuario(UUID idUsuario);
    NuevoUsuarioResponseDto crearUsuario(NuevoUsuarioRequestDto requestDto);
}
