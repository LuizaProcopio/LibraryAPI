package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.dto.CategoriaDto;
import com.luiza.primeiroprojetocomspring.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDto findById(@PathVariable Integer id){
        return categoriaService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaDto> findAll(){
        return categoriaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CategoriaDto categoriaDto){
        categoriaService.save(categoriaDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDto updateById(@PathVariable Integer id, @RequestBody CategoriaDto categoriaDto){
        return categoriaService.updateById(id, categoriaDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        categoriaService.delete(id);
    }

}
