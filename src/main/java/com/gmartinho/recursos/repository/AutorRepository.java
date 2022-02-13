package com.gmartinho.recursos.repository;

import com.gmartinho.recursos.model.AutorModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepository extends CrudRepository<AutorModel, Long> {

    @Query("SELECT a FROM AutorModel a WHERE a.sobrenome = ?1")
    List<AutorModel> findBySobrenome(String sobrenome);
}
