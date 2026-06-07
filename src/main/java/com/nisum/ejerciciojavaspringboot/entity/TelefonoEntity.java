package com.nisum.ejerciciojavaspringboot.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefonos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefonoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String codigoCiudad;

    @Column(nullable = false)
    private String codigoPais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
