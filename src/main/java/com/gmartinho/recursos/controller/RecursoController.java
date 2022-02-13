package com.gmartinho.recursos.controller;

import com.gmartinho.recursos.model.AutorModel;
import com.gmartinho.recursos.model.RecursoModel;
import com.gmartinho.recursos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RecursoController {

    @Autowired
    private RecursoService service;

    @PostMapping("/recurso")
    public RecursoModel create(@RequestBody RecursoModel recurso) {
        return service.saveRecurso(recurso);
    }

    @GetMapping("/recurso")
    public List<RecursoModel> readAll() {
        return service.getRecursos();
    }

    @GetMapping("/recurso/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getRecurso(id);
    }

    @GetMapping("/recurso/autor/{id}")
    public ResponseEntity<?> readByAutor(@PathVariable("id") Long id) {
        return service.getRecursoByAutor(id);
    }

    @GetMapping("/recurso/collection/{id}")
    public ResponseEntity<?> readByColecao(@PathVariable("id") Long id) {
        return service.getRecursoByColecao(id);
    }

    @PutMapping("/recurso")
    public ResponseEntity<?> update(@RequestBody RecursoModel recurso) {
        return service.updateRecurso(recurso);
    }

    @DeleteMapping("/recurso/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deleteRecurso(id);
    }

}