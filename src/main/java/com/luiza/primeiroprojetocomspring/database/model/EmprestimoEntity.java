package com.luiza.primeiroprojetocomspring.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "emprestimo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "data_emprestimo", nullable = false)
    private LocalDateTime dataEmprestimo;
    @Column(name = "data_devolucao", nullable = false)
    private LocalDateTime dataDevolucao;
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private LivroEntity livro;
}
