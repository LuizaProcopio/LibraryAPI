package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.AutorEntity;
import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IAutorRepository;
import com.luiza.primeiroprojetocomspring.dto.AutorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final IAutorRepository autorRepository;

    public AutorDto findById(Integer id){
        AutorEntity autor = autorRepository.findById(id).orElseThrow();
        return AutorDto.builder()
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .livros(autor.getLivros())
                .build();
    }

    public List<AutorEntity> findAll(){
        return autorRepository.findAll();
    }

    public void save(AutorDto autorDto){
        Set<LivroEntity> livros = autorDto.getLivros();
        autorRepository.save(AutorEntity.builder()
                        .nome(autorDto.getNome())
                        .nacionalidade(autorDto.getNacionalidade())
                        .livros(livros)
                .build());
    }

    public AutorDto updateById(Integer id, AutorDto autorDto){
        AutorEntity autor = autorRepository.findById(id).orElseThrow();

        autor.setNome(autorDto.getNome());
        autor.setNacionalidade(autorDto.getNacionalidade());
        autor.setLivros(autorDto.getLivros());

        autorRepository.save(autor);

        return AutorDto.builder()
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .livros(autor.getLivros())
                .build();
    }

    public void delete(Integer id){
        autorRepository.deleteById(id);
    }
}
