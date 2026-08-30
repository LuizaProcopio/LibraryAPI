package com.luiza.primeiroprojetocomspring.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private UUID id;
    private String nome;
    private String email;
    @Builder.Default
    private Set<Integer> emprestimos = new HashSet<>();
}
