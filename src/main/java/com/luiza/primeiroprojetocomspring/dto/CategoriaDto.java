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

    private String nome;
    @Builder.Default /* Se ninguém informar um valor para esse atributo, use o valor padrão que eu defini aqui. */
    private Set<Integer> livros = new HashSet<>();

}
