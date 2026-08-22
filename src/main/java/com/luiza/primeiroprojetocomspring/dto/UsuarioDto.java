package com.luiza.primeiroprojetocomspring.dto;

import com.luiza.primeiroprojetocomspring.database.model.EmprestimoEntity;
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
