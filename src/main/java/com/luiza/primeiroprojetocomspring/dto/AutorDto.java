package com.luiza.primeiroprojetocomspring.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorDto {

    private Integer id;
    private String nome;
    private String nacionalidade;
    @Builder.Default
    private Set<Integer> livros = new HashSet<>();
}
