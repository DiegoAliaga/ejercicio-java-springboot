package com.nisum.ejerciciojavaspringboot.entity;

import com.nisum.ejerciciojavaspringboot.service.dto.TelefonoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;

    @Column(nullable = false)
    private LocalDateTime lastLogin;

    @Column(nullable = false, length = 1000)
    private String token;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TelefonoEntity> telefonos;
}