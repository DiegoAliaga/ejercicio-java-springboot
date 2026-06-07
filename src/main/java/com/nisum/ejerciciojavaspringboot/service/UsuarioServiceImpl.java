package com.nisum.ejerciciojavaspringboot.service;

import com.nisum.ejerciciojavaspringboot.config.ValidacionesProperties;
import com.nisum.ejerciciojavaspringboot.constant.Constantes;
import com.nisum.ejerciciojavaspringboot.controller.dto.ConsultaUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioRequestDto;
import com.nisum.ejerciciojavaspringboot.controller.dto.NuevoUsuarioResponseDto;
import com.nisum.ejerciciojavaspringboot.entity.TelefonoEntity;
import com.nisum.ejerciciojavaspringboot.entity.UsuarioEntity;
import com.nisum.ejerciciojavaspringboot.exception.BusinessException;
import com.nisum.ejerciciojavaspringboot.mapper.UsuarioMapper;
import com.nisum.ejerciciojavaspringboot.repository.UsuarioRepository;
import com.nisum.ejerciciojavaspringboot.security.JwtService;
import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ValidacionesProperties validacionesProperties;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ConsultaUsuarioResponseDto consultarUsuario(UUID idUsuario) {
        logger.info("[consultarUsuario][INI] ID: {},", idUsuario);
        UsuarioEntity usuario =
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() ->
                                new BusinessException(
                                        Constantes.MENSAJE_ERROR_NOENCONTRADO,
                                        HttpStatus.NOT_FOUND));

        List<TelefonoDto> telefonos =
                usuario.getTelefonos()
                        .stream()
                        .map(usuarioMapper::toTelefonoDto)
                        .toList();
        ConsultaUsuarioResponseDto response =
                usuarioMapper.toConsultaUsuarioResponseDto(usuario);
        response.setTelefonos(telefonos);

        logger.info("[consultarUsuario][FINOK]");

        return response;
    }

    @Override
    public NuevoUsuarioResponseDto crearUsuario(NuevoUsuarioRequestDto requestDto) {
        logger.info("[crearUsuario][INI] request: {},", requestDto);

        if (usuarioRepository.existsByEmail(requestDto.getEmail())) {
            logger.error("[crearUsuario][ERROR] {}", Constantes.MENSAJE_CORREO_EXISTENTE);
            throw new BusinessException(
                    Constantes.MENSAJE_CORREO_EXISTENTE,
                    HttpStatus.CONFLICT);
        }

        if (!requestDto.getEmail()
                .matches(validacionesProperties.getEmailRegex())) {
            logger.error("[crearUsuario][ERROR] {}", Constantes.MENSAJE_CORREO_INVALIDO);
            throw new BusinessException(
                    Constantes.MENSAJE_CORREO_INVALIDO,
                    HttpStatus.BAD_REQUEST);
        }

        if (!requestDto.getPassword()
                .matches(validacionesProperties.getPasswordRegex())) {
            logger.error("[crearUsuario][ERROR] {}", Constantes.MENSAJE_PASS_INVALIDA);
            throw new BusinessException(
                    Constantes.MENSAJE_PASS_INVALIDA,
                    HttpStatus.BAD_REQUEST);
        }

        boolean telefonoInvalido =
                requestDto.getTelefonos()
                        .stream()
                        .anyMatch(t ->
                                !t.getNumero()
                                        .matches(validacionesProperties.getTelefonoRegex()));

        if (telefonoInvalido) {
            logger.error("[crearUsuario][ERROR] {}", Constantes.MENSAJE_ERROR_TELEFONO);
            throw new BusinessException(
                    Constantes.MENSAJE_ERROR_TELEFONO,
                    HttpStatus.BAD_REQUEST);
        }
        UsuarioEntity usuario = usuarioMapper.toUsuarioEntity(requestDto);
        usuario.setPassword(
                passwordEncoder.encode(
                        requestDto.getPassword()));


        usuario.setId(UUID.randomUUID());
        LocalDateTime now = LocalDateTime.now();

        usuario.setCreated(now);
        usuario.setModified(now);
        usuario.setLastLogin(now);

        String token =
                jwtService.generarToken(
                        requestDto.getEmail());
        usuario.setToken(token);
        usuario.setIsActive(true);

        // Obtiene la lista de teléfonos enviada en el request
        // requestDto.getTelefonos() devuelve List<TelefonoDto>
        List<TelefonoEntity> telefonos =
                // Convierte la lista en un Stream para poder procesar cada elemento
                requestDto.getTelefonos()
                        .stream()
                        // Por cada TelefonoDto ejecuta:
                        // usuarioMapper.toTelefonoEntity(telefonoDto)
                        // transformando el DTO en una entidad JPA
                        .map(usuarioMapper::toTelefonoEntity)
                        // Recolecta todos los resultados en una nueva lista
                        // El resultado final es List<TelefonoEntity>
                        .toList();

        // Recorre cada teléfono de la lista
        telefonos.forEach(t -> t.setUsuario(usuario));
        // Asigna el usuario propietario del teléfono
        // Esto llena el campo:
        // private UsuarioEntity usuario;
        usuario.setTelefonos(telefonos);



        NuevoUsuarioResponseDto responseDto = usuarioMapper.toNuevoUsuarioResponseDto(
                usuarioRepository.save(usuario));
        logger.info("[crearUsuario][FINOK] response Ok: {}", responseDto);
        return responseDto;
    }




}
