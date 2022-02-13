package com.gmartinho.recursos.controller;

import com.gmartinho.recursos.model.AutorModel;
import com.gmartinho.recursos.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("/autor")
    public AutorModel create(@RequestBody AutorModel autor) {
        return service.saveAutor(autor);
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getAutor(id);
    }

    @GetMapping("/autor")
    public List<AutorModel> readAll() {
        return service.getAutores();
    }

    @GetMapping("/autor/sobrenome/{sobrenome}")
    public List<AutorModel> readBySobrenome(@PathVariable("sobrenome") String sobrenome) {
        return service.getAutoresBySobrenome(sobrenome);
    }

    @PutMapping("/autor")
    public ResponseEntity<?> update(@RequestBody AutorModel autor) {
        return service.updateAutor(autor);
    }

    @DeleteMapping("/autor/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deleteAutor(id);
    }

}