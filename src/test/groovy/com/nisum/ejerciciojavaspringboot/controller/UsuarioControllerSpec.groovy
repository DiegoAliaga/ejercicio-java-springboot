package com.nisum.ejerciciojavaspringboot.controller

import com.nisum.ejerciciojavaspringboot.exception.BusinessException
import spock.lang.Specification
import com.nisum.ejerciciojavaspringboot.service.UsuarioService
import com.nisum.ejerciciojavaspringboot.builder.TestBuilder
import org.springframework.http.HttpStatus

class UsuarioControllerSpec extends Specification {

    def service
    def controller

    def setup() {
        service = Mock(UsuarioService.class)
        controller = new UsuarioController(service)
    }

    def "Crear usuario exitosamente"() {

        given: "Request válido"

        def requestDto = TestBuilder.crearUsuarioRequestBuilder()
        def responseDto = TestBuilder.crearUsuarioResponseBuilder()

        service.crearUsuario(requestDto) >> responseDto

        when:

        def response = controller.crearUsuario(requestDto)

        then:

        response != null
        response.statusCode == HttpStatus.CREATED
        response.body == responseDto
    }

    def "Crear usuario correo ya registrado"() {

        given:

        def requestDto = TestBuilder.crearUsuarioRequestBuilder()

        service.crearUsuario(requestDto) >> {
            throw new BusinessException(
                    "El correo ya registrado.",
                    HttpStatus.CONFLICT)
        }

        when:

        controller.crearUsuario(requestDto)

        then:

        def ex = thrown(BusinessException)

        ex.message == "El correo ya registrado."
    }
}