package com.luiza.primeiroprojetocomspring.service;

import com.luiza.primeiroprojetocomspring.database.model.UsuarioEntity;
import com.luiza.primeiroprojetocomspring.database.repository.IUsuarioRepository;
import com.luiza.primeiroprojetocomspring.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioDto findById(UUID id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow();

        return UsuarioDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }

    public List<UsuarioEntity> findAll(){
        return usuarioRepository.findAll();

    }

    public void save(UsuarioDto usuarioDto){
        usuarioRepository.save(UsuarioEntity.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail()).build());
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
                .nome(usuario.getNome())
                .email(usuario.getEmail()).build();
    }
}
