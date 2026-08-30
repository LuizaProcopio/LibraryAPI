package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.AutorEntity;
import com.luiza.primeiroprojetocomspring.database.model.CategoriaEntity;
import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IAutorRepository;
import com.luiza.primeiroprojetocomspring.database.repository.ICategoriaRepository;
import com.luiza.primeiroprojetocomspring.database.repository.ILivroRepository;
import com.luiza.primeiroprojetocomspring.dto.LivroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final ILivroRepository livroRepository;
    private final IAutorRepository autorRepository;
    private final ICategoriaRepository categoriaRepository;

    public LivroDto findById(Integer id){
        LivroEntity livro = livroRepository.findById(id).orElseThrow();

        return LivroDto.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .categorias(livro.getCategorias().stream().map(CategoriaEntity::getId).collect(Collectors.toSet()))
                .autores(livro.getAutores().stream().map(AutorEntity::getId).collect(Collectors.toSet()))
                .ano(livro.getAno())
                .isbn(livro.getIsbn())
                .quantidadeDisponivel(livro.getQuantidadeDisponivel())
                .build();
    }

    public List<LivroDto> findAll(){
        return livroRepository.findAll().stream()
                .map(livro -> LivroDto.builder()
                        .id(livro.getId())
                        .titulo(livro.getTitulo())
                        .isbn(livro.getIsbn())
                        .ano(livro.getAno())
                        .quantidadeDisponivel(livro.getQuantidadeDisponivel())
                        .autores(livro.getAutores().stream().map(AutorEntity::getId)
                                .collect(Collectors.toSet()))
                        .categorias(livro.getCategorias().stream().map(CategoriaEntity::getId)
                                .collect(Collectors.toSet())).build()).toList();
    }


    public LivroDto save(LivroDto livroDto){
        Set<AutorEntity> autores = new HashSet<>();
        if (livroDto.getAutores() != null) {
            for (Integer autorId : livroDto.getAutores()){
                AutorEntity autor = autorRepository.findById(autorId).orElseThrow();
                autores.add(autor);
            }
        }

        Set<CategoriaEntity> categorias = new HashSet<>();
        if (livroDto.getCategorias() != null) {
            for (Integer categoriaId : livroDto.getCategorias()){
                CategoriaEntity categoria = categoriaRepository.findById(categoriaId).orElseThrow();
                categorias.add(categoria);
            }
        }

        LivroEntity livro = livroRepository.save(LivroEntity.builder()
                .titulo(livroDto.getTitulo())
                .isbn(livroDto.getIsbn())
                .ano(livroDto.getAno())
                .quantidadeDisponivel(livroDto.getQuantidadeDisponivel())
                .autores(autores)
                .categorias(categorias)
                .build());

        return LivroDto.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .isbn(livro.getIsbn())
                .ano(livro.getAno())
                .quantidadeDisponivel(livro.getQuantidadeDisponivel())
                .autores(livro.getAutores().stream().map(AutorEntity::getId).collect(Collectors.toSet()))
                .categorias(livro.getCategorias().stream().map(CategoriaEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    public LivroDto updateById(Integer id, LivroDto livroDto){
        LivroEntity livro = livroRepository.findById(id).orElseThrow();

        Set<AutorEntity> autores = new HashSet<>();

        for (Integer autoresId : livroDto.getAutores()){
            AutorEntity autor = autorRepository.findById(autoresId).orElseThrow();
            autores.add(autor);
        }

        Set<CategoriaEntity> categorias = new HashSet<>();

        for (Integer categoriaId : livroDto.getCategorias()){
            CategoriaEntity categoria = categoriaRepository.findById(categoriaId).orElseThrow();
            categorias.add(categoria);
        }

        livro.setTitulo(livroDto.getTitulo());
        livro.setAutores(autores);
        livro.setCategorias(categorias);
        livro.setIsbn(livroDto.getIsbn());
        livro.setAno(livroDto.getAno());
        livro.setQuantidadeDisponivel(livroDto.getQuantidadeDisponivel());

        livroRepository.save(livro);

        return LivroDto.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autores(livro.getAutores().stream().map(AutorEntity::getId).collect(Collectors.toSet()))
                .categorias(livro.getCategorias().stream().map(CategoriaEntity::getId).collect(Collectors.toSet()))
                .isbn(livro.getIsbn())
                .ano(livro.getAno())
                .quantidadeDisponivel(livro.getQuantidadeDisponivel())
                .build();
    }

    public void delete(Integer id){
        livroRepository.deleteById(id);
    }
}
