package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.AutorEntity;
import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IAutorRepository;
import com.luiza.primeiroprojetocomspring.database.repository.ILivroRepository;
import com.luiza.primeiroprojetocomspring.dto.AutorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final IAutorRepository autorRepository;
    private final ILivroRepository livroRepository;

    public AutorDto findById(Integer id){
        AutorEntity autor = autorRepository.findById(id).orElseThrow();

        return AutorDto.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .livros(autor.getLivros().stream()
                        .map(LivroEntity::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public List<AutorDto> findAll(){
        return autorRepository.findAll().stream()
                .map(autor -> AutorDto.builder()
                        .id(autor.getId())
                        .nome(autor.getNome())
                        .nacionalidade(autor.getNacionalidade())
                        .livros(autor.getLivros().stream()
                                .map(LivroEntity::getId)
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }

    public AutorDto save(AutorDto autorDto){
        AutorEntity autor = autorRepository.save(AutorEntity.builder()
                .nome(autorDto.getNome())
                .nacionalidade(autorDto.getNacionalidade())
                .build());

        Set<LivroEntity> livros = new HashSet<>();
        if (autorDto.getLivros() != null) {
            for (Integer livroId : autorDto.getLivros()) {
                LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
                livro.getAutores().add(autor);
                livros.add(livro);
            }
            livroRepository.saveAll(livros);
        }

        return AutorDto.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .livros(livros.stream().map(LivroEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    public AutorDto updateById(Integer id, AutorDto autorDto){
        AutorEntity autor = autorRepository.findById(id).orElseThrow();

        for (LivroEntity livroAntigo : autor.getLivros()) {
            livroAntigo.getAutores().remove(autor);
        }
        livroRepository.saveAll(autor.getLivros());

        Set<LivroEntity> novosLivros = new HashSet<>();
        if (autorDto.getLivros() != null) {
            for (Integer livroId : autorDto.getLivros()) {
                LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
                livro.getAutores().add(autor);
                novosLivros.add(livro);
            }
            livroRepository.saveAll(novosLivros);
        }

        autor.setNome(autorDto.getNome());
        autor.setNacionalidade(autorDto.getNacionalidade());
        autorRepository.save(autor);

        return AutorDto.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .livros(novosLivros.stream().map(LivroEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    public void delete(Integer id){
        autorRepository.deleteById(id);
    }
}
