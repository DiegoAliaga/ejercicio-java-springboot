package com.nisum.ejerciciojavaspringboot.repository;


import com.nisum.ejerciciojavaspringboot.entity.UsuarioEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
        extends JpaRepository<UsuarioEntity, UUID> {

    boolean existsByEmail(String email);
}
