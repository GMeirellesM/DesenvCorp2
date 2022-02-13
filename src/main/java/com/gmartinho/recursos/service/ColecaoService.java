package com.gmartinho.recursos.service;

import com.gmartinho.recursos.model.ColecaoModel;
import com.gmartinho.recursos.model.RecursoModel;
import com.gmartinho.recursos.repository.ColecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ColecaoService {

    @Autowired
    private ColecaoRepository repository;

    public ResponseEntity<?> getColecao(Long id) {
        ColecaoModel colecao = repository
                .findById(id)
                .orElse(null);

        if (colecao == null)
            return new ResponseEntity<>("{\"message\":\"collection does not exist\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(colecao, HttpStatus.OK);
    }
}
