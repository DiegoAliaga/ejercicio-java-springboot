package com.nisum.ejerciciojavaspringboot.mapper;

import com.nisum.ejerciciojavaspringboot.controller.dto.ConsultaUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioRequestDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.entity.TelefonoEntity;
import com.nisum.ejerciciojavaspringboot.entity.UsuarioEntity;
import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioEntity toUsuarioEntity(NuevoUsuarioRequestDto request) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        return usuario;
    }

    public NuevoUsuarioResponseDto toNuevoUsuarioResponseDto(UsuarioEntity usuario) {
        return NuevoUsuarioResponseDto.builder()
                .id(usuario.getId()).token(usuario.getToken())
                .created(usuario.getCreated())
                .isActive(usuario.getIsActive())
                .modified(usuario.getModified()).lastLogin(usuario.getLastLogin())
                .build();
    }

    public TelefonoEntity toTelefonoEntity(TelefonoDto telefonoDto) {
        TelefonoEntity telefono = new TelefonoEntity();

        telefono.setNumero(telefonoDto.getNumero());
        telefono.setCodigoCiudad(telefonoDto.getCodigoCiudad());
        telefono.setCodigoPais(telefonoDto.getCodigoPais());

        return telefono;
    }

    public TelefonoDto toTelefonoDto(TelefonoEntity telefonoEntity) {
        return TelefonoDto.builder().numero(telefonoEntity.getNumero())
                .codigoCiudad(telefonoEntity.getCodigoCiudad())
                .codigoPais(telefonoEntity.getCodigoPais())
                .build();
    }

    public ConsultaUsuarioResponseDto toConsultaUsuarioResponseDto(UsuarioEntity usuario) {
        return ConsultaUsuarioResponseDto.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .token(usuario.getToken())
                .created(usuario.getCreated())
                .isActive(usuario.getIsActive())
                .lastLogin(usuario.getLastLogin())
                .modified(usuario.getModified())
                .nombre(usuario.getNombre())
                .build();

    }
}
