package com.gmartinho.recursos.service;

import com.gmartinho.recursos.model.CursoModel;
import com.gmartinho.recursos.model.RecursoModel;
import com.gmartinho.recursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private RecursoService recursoService;

    public CursoModel saveCurso(CursoModel curso) {

        CursoModel newCurso = repository.save(curso);

        curso.getRecursos().forEach(recurso -> {

            ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

            if (recursoExistente.getBody() instanceof RecursoModel) {

                ((RecursoModel) recursoExistente.getBody()).setColecao(curso);

                recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

            }

        });

        return newCurso;
    }

    public List<CursoModel> getCursos() {
        return (List<CursoModel>) repository.findAll();
    }

    public ResponseEntity<?> getCurso(Long id) {
        CursoModel cursoExistente = repository
                .findById(id)
                .orElse(null);

        if (cursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"curso does not exist\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cursoExistente, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCurso(CursoModel curso) {
        CursoModel cursoExistente = repository
                .findById(curso.getId())
                .orElse(null);

        if (cursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"curso does not exist\"}", HttpStatus.NOT_FOUND);

        if (curso.getTitulo() != null)
            cursoExistente.setTitulo(curso.getTitulo());

        if (curso.getDescricao() != null)
            cursoExistente.setDescricao(curso.getDescricao());

        if (curso.getImagem() != null)
            cursoExistente.setImagem(curso.getImagem());

        if (curso.getRecursos() != null) {

            cursoExistente.getRecursos().forEach(recurso -> {

                ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

                if (recursoExistente.getBody() instanceof RecursoModel) {

                    ((RecursoModel) recursoExistente.getBody()).setColecao(null);

                    recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

                }

            });

            ArrayList<RecursoModel> newRecursos = new ArrayList<RecursoModel>();

            curso.getRecursos().forEach(recurso -> {

                ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

                if (recursoExistente.getBody() instanceof RecursoModel) {

                    ((RecursoModel) recursoExistente.getBody()).setColecao(curso);

                    recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

                    newRecursos.add((RecursoModel) recursoExistente.getBody());

                }

            });

            cursoExistente.setRecursos(newRecursos);

        }

        if (curso.getDataRegistro() != null)
            cursoExistente.setDataRegistro(curso.getDataRegistro());

        return new ResponseEntity<>(repository.save(cursoExistente), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteCurso(Long id) {

        CursoModel cursoExistente = repository
                .findById(id)
                .orElse(null);

        if (cursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"curso does not exist\"}", HttpStatus.NOT_FOUND);

        cursoExistente.getRecursos().forEach(recurso -> {

            ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

            if (recursoExistente.getBody() instanceof RecursoModel) {

                ((RecursoModel) recursoExistente.getBody()).setColecao(null);

                recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"removed curso\"}", HttpStatus.OK);
    }


}