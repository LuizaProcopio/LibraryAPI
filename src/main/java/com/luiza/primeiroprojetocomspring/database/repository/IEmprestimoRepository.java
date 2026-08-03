package com.luiza.primeiroprojetocomspring.database.repository;

import com.luiza.primeiroprojetocomspring.database.model.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmprestimoRepository extends JpaRepository<EmprestimoEntity, UUID> {
}
