package com.gmartinho.recursos.service;

import com.gmartinho.recursos.model.AutorModel;
import com.gmartinho.recursos.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorModel saveAutor(AutorModel autor) {
        return repository.save(autor);
    }

    public List<AutorModel> getAutores() {
        return (List<AutorModel>) repository.findAll();
    }

    public ResponseEntity<?> getAutor(Long id) {
        AutorModel autorExistente = repository
                .findById(id)
                .orElse(null);

        if (autorExistente == null)
            return new ResponseEntity<>("{\"message\":\"author does not exist\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(autorExistente, HttpStatus.OK);
    }

    public List<AutorModel> getAutoresBySobrenome(String sobrenome) {
        return repository.findBySobrenome(sobrenome);
    }

    public ResponseEntity<?> updateAutor(AutorModel autor) {
        AutorModel autorExistente = repository
                .findById(autor.getId())
                .orElse(null);

        if (autorExistente == null)
            return new ResponseEntity<>("{\"message\":\"author does not exist\"}", HttpStatus.NOT_FOUND);

        if (autor.getEmail() != null)
            autorExistente.setEmail(autor.getEmail());

        if (autor.getNome() != null)
            autorExistente.setNome(autor.getNome());

        if (autor.getSobrenome() != null)
            autorExistente.setSobrenome(autor.getSobrenome());

        if (autor.getAfiliacao() != null)
            autorExistente.setAfiliacao(autor.getAfiliacao());

        if (autor.getOrcId() != null)
            autorExistente.setOrcId(autor.getOrcId());

        return new ResponseEntity<>(repository.save(autorExistente), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAutor(Long id) {
        AutorModel autorExistente = repository
                .findById(id)
                .orElse(null);

        if (autorExistente == null)
            return new ResponseEntity<>("{\"message\":\"author does not exist\"}", HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return new ResponseEntity<>("{\"message\":\"removed author\"}", HttpStatus.OK);
    }
}
