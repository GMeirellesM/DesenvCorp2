package com.gmartinho.recursos.service;

import com.gmartinho.recursos.model.EventoModel;
import com.gmartinho.recursos.model.RecursoModel;
import com.gmartinho.recursos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private RecursoService recursoService;

    public EventoModel saveEvento(EventoModel evento) {

        EventoModel newEvento = repository.save(evento);

        evento.getRecursos().forEach(recurso -> {

            ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

            if (recursoExistente.getBody() instanceof RecursoModel) {

                ((RecursoModel) recursoExistente.getBody()).setColecao(evento);

                recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

            }

        });

        return newEvento;
    }

    public List<EventoModel> getEventos() {
        return (List<EventoModel>) repository.findAll();
    }

    public ResponseEntity<?> getEvento(Long id) {
        EventoModel eventoExistente = repository
                .findById(id)
                .orElse(null);

        if (eventoExistente == null)
            return new ResponseEntity<>("{\"message\":\"evento does not exist\"}", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(eventoExistente, HttpStatus.OK);
    }

    public List<EventoModel> getEventosByIntervaloData(Date dataInicio, Date dataFim) {
        return repository.findByIntervaloData(dataInicio, dataFim);
    }

    public ResponseEntity<?> updateEvento(EventoModel evento) {
        EventoModel eventoExistente = repository
                .findById(evento.getId())
                .orElse(null);

        if (eventoExistente == null)
            return new ResponseEntity<>("{\"message\":\"evento does not exist\"}", HttpStatus.NOT_FOUND);

        if (evento.getTitulo() != null)
            eventoExistente.setTitulo(evento.getTitulo());

        if (evento.getDescricao() != null)
            eventoExistente.setDescricao(evento.getDescricao());

        if (evento.getImagem() != null)
            eventoExistente.setImagem(evento.getImagem());

        if (evento.getRecursos() != null) {

            eventoExistente.getRecursos().forEach(recurso -> {

                ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

                if (recursoExistente.getBody() instanceof RecursoModel) {

                    ((RecursoModel) recursoExistente.getBody()).setColecao(null);

                    recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

                }

            });

            ArrayList<RecursoModel> newRecursos = new ArrayList<RecursoModel>();

            evento.getRecursos().forEach(recurso -> {

                ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

                if (recursoExistente.getBody() instanceof RecursoModel) {

                    ((RecursoModel) recursoExistente.getBody()).setColecao(evento);

                    recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

                    newRecursos.add((RecursoModel) recursoExistente.getBody());

                }

            });

            eventoExistente.setRecursos(newRecursos);

        }

        if (evento.getDataInicio() != null)
            eventoExistente.setDataInicio(evento.getDataInicio());

        if (evento.getDataFim() != null)
            eventoExistente.setDataFim(evento.getDataFim());

        return new ResponseEntity<>(repository.save(eventoExistente), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteEvento(Long id) {

        EventoModel eventoExistente = repository
                .findById(id)
                .orElse(null);

        if (eventoExistente == null)
            return new ResponseEntity<>("{\"message\":\"evento does not exist\"}", HttpStatus.NOT_FOUND);

        eventoExistente.getRecursos().forEach(recurso -> {

            ResponseEntity<?> recursoExistente = recursoService.getRecurso(recurso.getId());

            if (recursoExistente.getBody() instanceof RecursoModel) {

                ((RecursoModel) recursoExistente.getBody()).setColecao(null);

                recursoService.updateRecurso((RecursoModel) recursoExistente.getBody());

            }

        });

        repository.deleteById(id);

        return new ResponseEntity<>("{\"message\":\"removed evento\"}", HttpStatus.OK);
    }

}