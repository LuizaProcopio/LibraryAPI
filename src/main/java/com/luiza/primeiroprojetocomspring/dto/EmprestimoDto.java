package com.luiza.primeiroprojetocomspring.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoDto {

    private Integer id;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private String status;
    private UUID usuario;
    private Integer livro;

}
