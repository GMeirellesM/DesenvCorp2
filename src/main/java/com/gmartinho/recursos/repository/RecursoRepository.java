package com.gmartinho.recursos.repository;

import com.gmartinho.recursos.model.RecursoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecursoRepository extends CrudRepository<RecursoModel, Long> {

    @Query("SELECT r FROM RecursoModel r INNER JOIN r.autores a WHERE a.id = ?1")
    List<RecursoModel> findByAutor(Long idAutor);

    @Query("SELECT r FROM RecursoModel r INNER JOIN r.colecao c WHERE c.id = ?1")
    List<RecursoModel> findByColecao(Long idColecao);

}