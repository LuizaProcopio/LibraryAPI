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

        Set<Integer> livrosId = categoria.getLivros()
                .stream()
                .map(LivroEntity::getId)
                .collect(Collectors.toSet());

        return CategoriaDto.builder()
                .nome(categoria.getNome())
                .livros(livrosId)
                .build();
    }

    public List<CategoriaEntity> findAll(){
        return categoriaRepository.findAll();
    }

    public void save(CategoriaDto categoriaDto){
        Set<LivroEntity> livros = new HashSet<>();

        for (Integer livroId : categoriaDto.getLivros()){
            LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
            livros.add(livro);
        }

        CategoriaEntity categoria = categoriaRepository.save(CategoriaEntity.builder()
                        .nome(categoriaDto.getNome())
                        .livros(livros)
                .build());
    }

    public CategoriaDto updateById(Integer id, CategoriaDto categoriaDto){
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow();

        Set<LivroEntity> livros = new HashSet<>();

        for (Integer livroId : categoriaDto.getLivros()) {
            LivroEntity livro = livroRepository.findById(livroId).orElseThrow();
            livros.add(livro);
        }

        categoria.setNome(categoriaDto.getNome());
        categoria.setLivros(livros);

        categoriaRepository.save(categoria);

        Set<Integer> livrosId = categoria.getLivros()
                .stream()
                .map(LivroEntity::getId)
                .collect(Collectors.toSet());

        return CategoriaDto.builder()
                .nome(categoria.getNome())
                .livros(livrosId)
                .build();
    }

    public void delete(Integer id){
        categoriaRepository.deleteById(id);
    }
}
