package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.EmprestimoEntity;
import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import com.luiza.primeiroprojetocomspring.database.model.UsuarioEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IEmprestimoRepository;
import com.luiza.primeiroprojetocomspring.database.repository.ILivroRepository;
import com.luiza.primeiroprojetocomspring.database.repository.IUsuarioRepository;
import com.luiza.primeiroprojetocomspring.dto.EmprestimoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final IEmprestimoRepository emprestimoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ILivroRepository livroRepository;

    public EmprestimoDto findById(Integer id){
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id).orElseThrow();

        return EmprestimoDto.builder()
                .id(emprestimo.getId())
                .dataEmprestimo(emprestimo.getDataEmprestimo())
                .dataDevolucao(emprestimo.getDataDevolucao())
                .usuario(emprestimo.getUsuario().getId())
                .livro(emprestimo.getLivro().getId())
                .status(emprestimo.getStatus())
                .build();
    }

    public List<EmprestimoDto> findAll(){
        return emprestimoRepository.findAll()
                .stream()
                .map(emprestimo -> EmprestimoDto.builder()
                        .id(emprestimo.getId())
                        .dataEmprestimo(emprestimo.getDataEmprestimo())
                        .dataDevolucao(emprestimo.getDataDevolucao())
                        .status(emprestimo.getStatus())
                        .usuario(emprestimo.getUsuario().getId())
                        .livro(emprestimo.getLivro().getId())
                        .build())
                .toList();
    }


    public EmprestimoDto save(EmprestimoDto emprestimoDto){
        UsuarioEntity usuario = usuarioRepository.findById(emprestimoDto.getUsuario()).orElseThrow();
        LivroEntity livro = livroRepository.findById(emprestimoDto.getLivro()).orElseThrow();

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Livro sem exemplares disponíveis");
        }

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroRepository.save(livro);

        EmprestimoEntity emprestimo = emprestimoRepository.save(EmprestimoEntity.builder()
                .dataEmprestimo(emprestimoDto.getDataEmprestimo())
                .dataDevolucao(emprestimoDto.getDataDevolucao())
                .usuario(usuario)
                .livro(livro)
                .status(emprestimoDto.getStatus())
                .build());

        return EmprestimoDto.builder()
                .id(emprestimo.getId())
                .dataEmprestimo(emprestimo.getDataEmprestimo())
                .dataDevolucao(emprestimo.getDataDevolucao())
                .usuario(emprestimo.getUsuario().getId())
                .livro(emprestimo.getLivro().getId())
                .status(emprestimo.getStatus())
                .build();
    }

    public EmprestimoDto updateById(Integer id, EmprestimoDto emprestimoDto) {
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id).orElseThrow();
        LivroEntity livro = livroRepository.findById(emprestimoDto.getLivro()).orElseThrow();
        UsuarioEntity usuario = usuarioRepository.findById(emprestimoDto.getUsuario()).orElseThrow();

        boolean estavaEmprestado = !"DEVOLVIDO".equalsIgnoreCase(emprestimo.getStatus());
        boolean agoraDevolvido = "DEVOLVIDO".equalsIgnoreCase(emprestimoDto.getStatus());

        if (estavaEmprestado && agoraDevolvido) {
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
            livroRepository.save(livro);
        }

        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatus(emprestimoDto.getStatus());

        emprestimoRepository.save(emprestimo);

        return EmprestimoDto.builder()
                .id(emprestimo.getId())
                .dataEmprestimo(emprestimo.getDataEmprestimo())
                .dataDevolucao(emprestimo.getDataDevolucao())
                .usuario(emprestimo.getUsuario().getId())
                .livro(emprestimo.getLivro().getId())
                .status(emprestimo.getStatus())
                .build();
    }

    public void delete(Integer id){
        emprestimoRepository.deleteById(id);
    }
}
