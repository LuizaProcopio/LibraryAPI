package com.luiza.primeiroprojetocomspring.controller;

import com.luiza.primeiroprojetocomspring.dto.EmprestimoDto;
import com.luiza.primeiroprojetocomspring.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmprestimoDto> findAll(){
        return emprestimoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoDto findById(@PathVariable Integer id){
        return emprestimoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmprestimo(@RequestBody EmprestimoDto emprestimoDto){
        emprestimoService.save(emprestimoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmprestimoDto updateById(@PathVariable Integer id, @RequestBody EmprestimoDto emprestimoDto){
        return emprestimoService.updateById(id, emprestimoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        emprestimoService.delete(id);
    }
}
