package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.EmprestimoEntity;
import com.luiza.primeiroprojetocomspring.database.model.UsuarioEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IUsuarioRepository;
import com.luiza.primeiroprojetocomspring.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioDto findById(UUID id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow();

        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .emprestimos(usuario.getEmprestimos().stream()
                        .map(EmprestimoEntity::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public List<UsuarioDto> findAll(){
        return usuarioRepository.findAll().stream()
                .map(usuario -> UsuarioDto.builder()
                        .id(usuario.getId())
                        .nome(usuario.getNome())
                        .email(usuario.getEmail())
                        .emprestimos(usuario.getEmprestimos().stream()
                                .map(EmprestimoEntity::getId)
                                .collect(Collectors.toSet())).build()).toList();

    }

    public UsuarioDto save(UsuarioDto usuarioDto){
        UsuarioEntity usuario = usuarioRepository.save(UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .build());

        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }

    public void deleteById(UUID id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto updateById(UUID id, UsuarioDto usuarioDto){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());

        usuarioRepository.save(usuario);

        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail()).build();
    }
}
