package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAutorRepository extends JpaRepository<AutorEntity, UUID> {
}
