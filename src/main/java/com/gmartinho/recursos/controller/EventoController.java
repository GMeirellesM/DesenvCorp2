package com.gmartinho.recursos.controller;

import com.gmartinho.recursos.model.EventoModel;
import com.gmartinho.recursos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EventoController {

    @Autowired
    private EventoService service;

    @PostMapping("/evento")
    public EventoModel create(@RequestBody EventoModel evento) {
        return service.saveEvento(evento);
    }

    @GetMapping("/evento/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        return service.getEvento(id);
    }

    @GetMapping("/evento")
    public List<EventoModel> readAll() {
        return service.getEventos();
    }

    @GetMapping("/evento/entre-datas")
    public List<EventoModel> readByIntervaloData(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim
    ) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return service.getEventosByIntervaloData(formatter.parse(dataInicio), formatter.parse(dataFim));
    }

    @PutMapping("/evento")
    public ResponseEntity<?> update(@RequestBody EventoModel evento) {
        return service.updateEvento(evento);
    }

    @DeleteMapping("/evento/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.deleteEvento(id);
    }

}