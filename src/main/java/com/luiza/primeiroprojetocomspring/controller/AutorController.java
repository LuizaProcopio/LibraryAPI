package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.dto.AutorDto;
import com.luiza.primeiroprojetocomspring.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public List<AutorDto> findAll(){
        return autorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody AutorDto autorDto){
        autorService.save(autorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutorDto updateById(@PathVariable Integer id, @RequestBody AutorDto autorDto){
        return autorService.updateById(id, autorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        autorService.delete(id);
    }
}