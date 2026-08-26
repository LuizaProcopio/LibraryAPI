package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.dto.LivroDto;
import com.luiza.primeiroprojetocomspring.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LivroDto> findAll(){
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivroDto findById(@PathVariable Integer id){
        return livroService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody LivroDto livroDto){
        livroService.save(livroDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivroDto updateById(@PathVariable Integer id, @RequestBody LivroDto livroDto){
        return livroService.updateById(id, livroDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        livroService.delete(id);
    }

}
