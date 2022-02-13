package com.gmartinho.recursos.controller;

import com.gmartinho.recursos.model.CursoModel;
import com.gmartinho.recursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping("/curso")
    public CursoModel create(@RequestBody CursoModel curso) {
        return service.saveCurso(curso);
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getCurso(id);
    }

    @GetMapping("/curso")
    public List<CursoModel> readAll() {
        return service.getCursos();
    }

    @PutMapping("/curso")
    public ResponseEntity<?> update(@RequestBody CursoModel curso) {
        return service.updateCurso(curso);
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deleteCurso(id);
    }

}