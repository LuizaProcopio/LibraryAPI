package com.luiza.primeiroprojetocomspring.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto {

    private Integer id;
    private String nome;
    @Builder.Default
    private Set<Integer> livros = new HashSet<>();

}
