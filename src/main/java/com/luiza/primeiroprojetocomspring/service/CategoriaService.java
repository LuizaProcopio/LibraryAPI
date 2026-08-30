package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.CategoriaEntity;
import com.luiza.primeiroprojetocomspring.database.model.LivroEntity;
import com.luiza.primeiroprojetocomspring.database.repository.ICategoriaRepository;
import com.luiza.primeiroprojetocomspring.database.repository.ILivroRepository;
import com.luiza.primeiroprojetocomspring.dto.CategoriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final ILivroRepository livroRepository;

    public CategoriaDto findById(Integer id){
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow();

        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .livros(categoria.getLivros().stream()
                        .map(LivroEntity::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public List<CategoriaDto> findAll(){
        return categoriaRepository.findAll().stream()
                .map(categoria -> CategoriaDto.builder()
                        .id(categoria.getId())
                        .nome(categoria.getNome())
                        .livros(categoria.getLivros().stream()
                                .map(LivroEntity::getId)
                                .collect(Collectors.toSet())).build()).toList();
    }

    public CategoriaDto save(CategoriaDto categoriaDto){
        CategoriaEntity categoria = categoriaRepository.save(CategoriaEntity.builder()
                .nome(categoriaDto.getNome())
                .build());

        Set<LivroEntity> livros = new HashSet<>();
        if (categoriaDto.getLivros() != null) {
            for (Integer livroId : categoriaDto.getLivros()) {
                LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
                livro.getCategorias().add(categoria);
                livros.add(livro);
            }
            livroRepository.saveAll(livros);
        }

        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .livros(livros.stream().map(LivroEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    public CategoriaDto updateById(Integer id, CategoriaDto categoriaDto){
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow();

        for (LivroEntity livroAntigo : categoria.getLivros()) {
            livroAntigo.getCategorias().remove(categoria);
        }
        livroRepository.saveAll(categoria.getLivros());

        Set<LivroEntity> novosLivros = new HashSet<>();
        if (categoriaDto.getLivros() != null) {
            for (Integer livroId : categoriaDto.getLivros()) {
                LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
                livro.getCategorias().add(categoria);
                novosLivros.add(livro);
            }
            livroRepository.saveAll(novosLivros);
        }

        categoria.setNome(categoriaDto.getNome());
        categoriaRepository.save(categoria);

        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .livros(novosLivros.stream().map(LivroEntity::getId).collect(Collectors.toSet()))
                .build();
    }

    public void delete(Integer id){
        categoriaRepository.deleteById(id);
    }
}
