package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
}
