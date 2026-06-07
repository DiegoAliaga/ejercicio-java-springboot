package com.nisum.ejerciciojavaspringboot.security

import com.nisum.ejerciciojavaspringboot.config.JwtProperties
import com.nisum.ejerciciojavaspringboot.security.JwtService
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
                "MiClaveSuperSecretaParaNisum2026MiClaveSuperSecreta"

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
