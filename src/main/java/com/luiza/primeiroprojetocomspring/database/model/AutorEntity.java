package com.luiza.primeiroprojetocomspring.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "autor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String nacionalidade;


    @ManyToMany(mappedBy = "autores")
    private Set<LivroEntity> livros = new HashSet<>();
}
