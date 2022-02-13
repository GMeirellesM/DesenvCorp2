package com.gmartinho.recursos.service;

import com.gmartinho.recursos.model.AutorModel;
import com.gmartinho.recursos.model.RecursoModel;
import com.gmartinho.recursos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository repository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ColecaoService colecaoService;

    public RecursoModel saveRecurso(@RequestBody RecursoModel recurso) {

        RecursoModel newRecurso = repository.save(recurso);

        recurso.getAutores().forEach(autor -> {

            ResponseEntity<?> autorExistente = autorService.getAutor(autor.getId());

            if (autorExistente.getBody() instanceof AutorModel) {

                ((AutorModel) autorExistente.getBody()).getRecursos().add(recurso);

                autorService.updateAutor((AutorModel) autorExistente.getBody());

            }

        });

        return newRecurso;
    }

    public List<RecursoModel> getRecursos() {
        return (List<RecursoModel>) repository.findAll();
    }

    public ResponseEntity<?> getRecurso(Long id) {
        RecursoModel recursoExistente = repository
                .findById(id)
                .orElse(null);

        if (recursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"recurso does not exist\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(recursoExistente, HttpStatus.OK);
    }

    public ResponseEntity<?> getRecursoByAutor(Long id) {
        ResponseEntity<?> autorExistente = autorService.getAutor(id);

        if (autorExistente.getStatusCodeValue() == 404)
            return new ResponseEntity<>("{\"message\":\"autor does not exist\"}", HttpStatus.NOT_FOUND);

        List<RecursoModel> recursos = repository.findByAutor(id);

        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    public ResponseEntity<?> getRecursoByColecao(Long id) {

        ResponseEntity<?> eventoExistente = colecaoService.getColecao(id);

        if (eventoExistente.getStatusCodeValue() == 404) {

            ResponseEntity<?> cursoExistente = colecaoService.getColecao(id);

            if (cursoExistente.getStatusCodeValue() == 404)
                return new ResponseEntity<>("{\"message\":\"colecao does not exist\"}", HttpStatus.NOT_FOUND);

        }

        List<RecursoModel> recursos = repository.findByColecao(id);

        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    public ResponseEntity<?> updateRecurso(RecursoModel recurso) {
        RecursoModel recursoExistente = repository
                .findById(recurso.getId())
                .orElse(null);

        if (recursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"recurso does not exist\"}", HttpStatus.NOT_FOUND);

        if (recurso.getTitulo() != null)
            recursoExistente.setTitulo(recurso.getTitulo());

        if (recurso.getDescricao() != null)
            recursoExistente.setDescricao(recurso.getDescricao());

        if (recurso.getLink() != null)
            recursoExistente.setLink(recurso.getLink());

        if (recurso.getImagem() != null)
            recursoExistente.setImagem(recurso.getImagem());

        if (recurso.getDataCriacao() != null)
            recursoExistente.setDataCriacao(recurso.getDataCriacao());

        if (recurso.getDataRegistro() != null)
            recursoExistente.setDataRegistro(recurso.getDataRegistro());

        if (recurso.getPalavrasChave() != null)
            recursoExistente.setPalavrasChave(recurso.getPalavrasChave());

        if (recurso.getAutores() != null) {

            recursoExistente.getAutores().forEach(autor -> {

                ResponseEntity<?> autorExistente = autorService.getAutor(autor.getId());

                if (autorExistente.getBody() instanceof AutorModel) {

                    ((AutorModel) autorExistente.getBody()).getRecursos().removeIf(rec ->
                            rec.getId() == recursoExistente.getId()
                    );

                }

            });

            ArrayList<AutorModel> newAutores = new ArrayList<AutorModel>();

            recurso.getAutores().forEach(autor -> {

                ResponseEntity<?> autorExistente = autorService.getAutor(autor.getId());

                if (autorExistente.getBody() instanceof AutorModel) {

                    ((AutorModel) autorExistente.getBody()).getRecursos().add(recurso);

                    autorService.updateAutor((AutorModel) autorExistente.getBody());

                    newAutores.add((AutorModel) autorExistente.getBody());

                }

            });

            recursoExistente.setAutores(newAutores);

        }

        if (recurso.getColecao() != null)
            recursoExistente.setColecao(recurso.getColecao());

        return new ResponseEntity<>(repository.save(recursoExistente), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteRecurso(Long id) {

        RecursoModel recursoExistente = repository
                .findById(id)
                .orElse(null);

        if (recursoExistente == null)
            return new ResponseEntity<>("{\"message\":\"recurso does not exist\"}", HttpStatus.NOT_FOUND);

        recursoExistente.getAutores().forEach(autor -> {

            ResponseEntity<?> autorExistente = autorService.getAutor(autor.getId());

            if (autorExistente.getBody() instanceof AutorModel) {

                ((AutorModel) autorExistente.getBody()).getRecursos().removeIf(r ->
                        r.getId() == recursoExistente.getId()
                );

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"removed recurso\"}", HttpStatus.OK);
    }

}