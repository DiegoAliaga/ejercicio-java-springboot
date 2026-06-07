package com.nisum.ejerciciojavaspringboot.repository;

import com.nisum.ejerciciojavaspringboot.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository
        extends JpaRepository<UsuarioEntity, UUID> {

    boolean existsByEmail(String email);
}
