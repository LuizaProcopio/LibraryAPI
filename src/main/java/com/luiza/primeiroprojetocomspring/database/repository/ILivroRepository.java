package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ILivroRepository extends JpaRepository<LivroEntity, Integer> {
}
