package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
