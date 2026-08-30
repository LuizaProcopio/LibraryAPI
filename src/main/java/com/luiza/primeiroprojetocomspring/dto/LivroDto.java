package com.luiza.primeiroprojetocomspring.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {

    private Integer id;
    private String titulo;
    private String isbn;
    private Integer ano;
    private Integer quantidadeDisponivel;
    @Builder.Default
    private Set<Integer> autores = new HashSet<>();
    @Builder.Default
    private Set<Integer> categorias = new HashSet<>();

}