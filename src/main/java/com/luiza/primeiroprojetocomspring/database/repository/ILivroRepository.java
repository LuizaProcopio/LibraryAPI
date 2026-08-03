package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILivroRepository extends JpaRepository<LivroEntity, UUID> {
}
