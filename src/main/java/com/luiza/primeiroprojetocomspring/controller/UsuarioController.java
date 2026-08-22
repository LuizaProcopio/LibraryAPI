package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.database.model.UsuarioEntity;
import com.luiza.primeiroprojetocomspring.dto.UsuarioDto;
import com.luiza.primeiroprojetocomspring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto findById(@PathVariable UUID id){
        return usuarioService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> findAll(){
        return usuarioService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UsuarioDto usuarioDto){
        usuarioService.save(usuarioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        usuarioService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto updateById(@PathVariable UUID id, @RequestBody UsuarioDto usuarioDto){
        return usuarioService.updateById(id, usuarioDto);
    }
}
