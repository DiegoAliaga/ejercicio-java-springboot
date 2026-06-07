package com.nisum.ejerciciojavaspringboot.service

import com.nisum.ejerciciojavaspringboot.builder.TestBuilder
import com.nisum.ejerciciojavaspringboot.config.ValidacionesProperties
import com.nisum.ejerciciojavaspringboot.entity.TelefonoEntity
import com.nisum.ejerciciojavaspringboot.entity.UsuarioEntity
import com.nisum.ejerciciojavaspringboot.mapper.UsuarioMapper
import com.nisum.ejerciciojavaspringboot.repository.UsuarioRepository
import com.nisum.ejerciciojavaspringboot.exception.BusinessException
import com.nisum.ejerciciojavaspringboot.security.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UsuarioServiceSpec extends Specification{

    def usuarioRepository
    def usuarioMapper
    def validacionesProperties
    def jwtService
    def passwordEncoder
    def service

    def setup() {
        usuarioRepository = Mock(UsuarioRepository.class)
        usuarioMapper = Mock(UsuarioMapper.class)
        validacionesProperties = Mock(ValidacionesProperties.class)
        jwtService = Mock(JwtService.class)
        passwordEncoder = Mock(PasswordEncoder.class)
        service = new UsuarioServiceImpl(usuarioRepository
                ,usuarioMapper
                ,validacionesProperties
                ,jwtService
                ,passwordEncoder)
    }

    def "Crear usuario exitosamente"() {

        given: "Ingresa peticion para crear usuario con todos sus valores correctos"

        def telefonoDto = TestBuilder.crearTelefonoBuilder()
        def requestDto = TestBuilder.crearUsuarioRequestBuilder()
        requestDto.setTelefonos(List.of(telefonoDto))
        def usuarioEntity = new UsuarioEntity()
        def telefonoEntity = new TelefonoEntity()
        validacionesProperties.getEmailRegex() >> '^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'
        validacionesProperties.getPasswordRegex() >> '^[A-Za-z]{4,8}[0-9]{1,3}$'
        validacionesProperties.getTelefonoRegex() >> '^\\d{9}$'
        def responseDto = TestBuilder.crearUsuarioResponseBuilder()
        usuarioMapper.toUsuarioEntity(requestDto) >> usuarioEntity
        usuarioMapper.toTelefonoEntity(telefonoDto) >> telefonoEntity
        usuarioRepository.existsByEmail(requestDto.getEmail()) >> false
        usuarioRepository.save(_ as UsuarioEntity) >> usuarioEntity
        usuarioMapper.toNuevoUsuarioResponseDto(usuarioEntity) >> responseDto

        when:
        def result = service.crearUsuario(requestDto)

        then:     result != null
        result == responseDto
    }

    def "Crear usuario Exceptions"() {

        given: "Ingresa peticion para crear usuario con algunos valores incorrectos"

        def telefonoDto = TestBuilder.crearTelefonoBuilder()
        def requestDto = TestBuilder.crearUsuarioRequestBuilder()
        requestDto.setTelefonos(List.of(telefonoDto))
        def usuarioEntity = new UsuarioEntity()
        def telefonoEntity = new TelefonoEntity()
        validacionesProperties.getEmailRegex() >> '^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'
        validacionesProperties.getPasswordRegex() >> '^[A-Za-z]{4,8}[0-9]{1,3}$'
        def responseDto = TestBuilder.crearUsuarioResponseBuilder()
        usuarioMapper.toUsuarioEntity(requestDto) >> usuarioEntity
        usuarioMapper.toTelefonoEntity(telefonoDto) >> telefonoEntity
        usuarioRepository.existsByEmail(requestDto.getEmail()) >> booleano
        usuarioRepository.save(_ as UsuarioEntity) >> usuarioEntity
        usuarioMapper.toNuevoUsuarioResponseDto(usuarioEntity) >> responseDto
        requestDto.setEmail(email)
        requestDto.setPassword(password)

        when:
        service.crearUsuario(requestDto)

        then:
        def ex = thrown(BusinessException)
        ex.getMessage() == mensaje

        where:
        email     | password        | booleano | mensaje
        "eeee.cl" | "String12"      | false    | "Formato de correo inválido."
        "ee@ee.cl"| "Stringggggggg" | false    | "Formato de contraseña inválido."
    }

    def "Consultar usuario exitosamente"() {

        given:

        def id = UUID.randomUUID()

        def telefonoEntity = new TelefonoEntity()

        def usuarioEntity = new UsuarioEntity()
        usuarioEntity.setTelefonos(List.of(telefonoEntity))

        def responseDto =
                TestBuilder.crearConsultaUsuarioResponseBuilder()

        usuarioRepository.findById(id) >>
                Optional.of(usuarioEntity)

        usuarioMapper.toConsultaUsuarioResponseDto(
                usuarioEntity) >> responseDto

        when:

        def result = service.consultarUsuario(id)

        then:

        result == responseDto
    }

    def "Consultar usuario no encontrado"() {

        given:

        def id = UUID.randomUUID()

        usuarioRepository.findById(id) >>
                Optional.empty()

        when:

        service.consultarUsuario(id)

        then:

        def ex = thrown(BusinessException)

        ex.message == "Usuario no encontrado."
    }
}
