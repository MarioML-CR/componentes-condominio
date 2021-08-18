package com.cenfotec.condominio.controller;

import com.cenfotec.condominio.domian.Condominio;
import com.cenfotec.condominio.service.CondominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/condominios"})
public class CondominioController {
    @Autowired
    private CondominioService condominioService;

    @PostMapping
    public Condominio createCondo(@RequestBody Condominio condominio) {
        return condominioService.saveCondo(condominio).get();
    }

    @GetMapping
    public List getAllCondos() {
        return condominioService.getAllCondos();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Condominio> getCondoById(@PathVariable long id) {
        Optional<Condominio> result = condominioService.findCondoById(id);
        /*
        if (result.isPresent()) return ResponseEntity.ok().body(result.get());
        return ResponseEntity.notFound().build();
        Función equivalente
        */
        return result.map(condominio -> ResponseEntity.ok().body(condominio)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Condominio> updateCondo(@RequestBody Condominio condominio) {
        Optional<Condominio> result = condominioService.updateCondo(condominio);
        /*
        if (result.isPresent()) return ResponseEntity.ok().body(result.get());
        return ResponseEntity.notFound().build();
        Función equivalente
         */
        return result.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCondo(@PathVariable("id") long id) {
        if (condominioService.deleteCondo(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
