package com.luiza.primeiroprojetocomspring.dto;

import com.luiza.primeiroprojetocomspring.database.model.EmprestimoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private String nome;
    private String email;
    private EmprestimoEntity emprestimos;
}
