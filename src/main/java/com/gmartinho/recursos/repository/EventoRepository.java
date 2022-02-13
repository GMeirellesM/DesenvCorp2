package com.gmartinho.recursos.repository;

import com.gmartinho.recursos.model.EventoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface EventoRepository extends CrudRepository<EventoModel, Long> {

    @Query("SELECT e FROM EventoModel e WHERE e.dataInicio BETWEEN ?1 AND ?2 OR e.dataFim BETWEEN ?1 AND ?2")
    List<EventoModel> findByIntervaloData(Date dataInicio, Date dataFim);

}