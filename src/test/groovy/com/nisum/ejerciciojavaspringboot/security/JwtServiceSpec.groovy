package com.nisum.ejerciciojavaspringboot.security

import com.nisum.ejerciciojavaspringboot.config.JwtProperties
import spock.lang.Specification

class JwtServiceSpec extends Specification{

    def jwtProperties
    def service

    def setup() {
        jwtProperties = Mock(JwtProperties.class)

        service = new JwtService(jwtProperties)
    }

    def "Debe generar JWT valido"() {

        given:

        jwtProperties.getSecret() >>
                "test-jwt-secret-ojala-guardarlo-en-vault"

        jwtProperties.getExpiration() >>
                86400000L

        when:

        def token =
                service.generarToken("test@test.cl")

        then:

        token != null
        token.startsWith("eyJ")
    }
}
