package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.database.model.AutorEntity;
import com.luiza.primeiroprojetocomspring.dto.AutorDto;
import com.luiza.primeiroprojetocomspring.dto.LivroDto;
import com.luiza.primeiroprojetocomspring.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequestMapping("/autor")
@RestController
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutorDto findById(@PathVariable Integer id){
        return autorService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AutorEntity> findAll(){
        return autorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody AutorDto autorDto){
        autorService.save(autorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AutorDto updateById(@PathVariable Integer id, @RequestBody AutorDto autorDto){
        return autorService.updateById(id, autorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        autorService.delete(id);
    }
}
