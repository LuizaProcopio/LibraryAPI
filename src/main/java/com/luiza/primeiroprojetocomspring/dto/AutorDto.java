package com.luiza.primeiroprojetocomspring.dto;

import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorDto {

    private String nome;
    private String nacionalidade;
    private Set<LivroEntity> livros;
}
