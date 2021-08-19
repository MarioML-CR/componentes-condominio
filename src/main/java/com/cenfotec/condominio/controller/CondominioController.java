package com.cenfotec.condominio.controller;

import com.cenfotec.condominio.domian.Amenidad;
import com.cenfotec.condominio.domian.Condominio;
import com.cenfotec.condominio.repositories.AmenidadRepository;
import com.cenfotec.condominio.repositories.CondominioRepository;
import com.cenfotec.condominio.service.AmenidadService;
import com.cenfotec.condominio.service.CondominioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/condominios"})
public class CondominioController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CondominioService condominioService;
    @Autowired
    private CondominioRepository condominioRepository;
    @Autowired
    AmenidadService amenidadService;
    @Autowired
    private AmenidadRepository amenidadRepository;

    @PostMapping
    public Condominio createCondo(@RequestBody Condominio condominio) {
        logger.info("Creando un condominio", condominioService.saveCondo(condominio).get());
        return condominioService.saveCondo(condominio).get();
    }

    @GetMapping
    public List getAllCondos() {
        logger.info("generando lista de condominios", condominioService.getAllCondos());
        return condominioService.getAllCondos();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Condominio> getCondoById(@PathVariable long id) {
        logger.info("Buscando un condominio por id", condominioService.findCondoById(id));
        Optional<Condominio> result = condominioService.findCondoById(id);
        /*
        if (result.isPresent()) return ResponseEntity.ok().body(result.get());
        return ResponseEntity.notFound().build();
        Función equivalente
        */
        return result.map(condominio -> ResponseEntity.ok().body(condominio)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activo")
    public ResponseEntity<List<Condominio>> getCondosActivos(String activo) {
        logger.info("Listando lod condominios activos", condominioRepository.findCondoByEstado(activo));
        activo = "activo";
        List<Condominio> condominios = condominioRepository.findCondoByEstado(activo);
        return new ResponseEntity<>(condominios, HttpStatus.OK);
    }

    @GetMapping("/inactivo")
    public ResponseEntity<List<Condominio>> getCondosInactivos(String inactivo) {
        logger.info("Listando lod condominios inactivos", condominioRepository.findCondoByEstado(inactivo));
        inactivo = "inactivo";
        List<Condominio> condominios = condominioRepository.findCondoByEstado(inactivo);
        return new ResponseEntity<>(condominios, HttpStatus.OK);
    }
    @PutMapping("/estado")
    public ResponseEntity<Condominio> cambiarEstadoCondo(@RequestBody Condominio condominio) {
        logger.info("Cambio de estado de condominio");
        Optional<Condominio> condo = condominioService.findCondoById(condominio.getId());
        Optional<Condominio> result = condominioService.updateEstadoCondo(condo.get().getId());
        if (condo.isPresent()) return ResponseEntity.ok().body(result.get());
        return ResponseEntity.notFound().build();
    }
    @PutMapping
    public ResponseEntity<Condominio> updateCondoActivo(@RequestBody Condominio condominio) {
        logger.info("Actualizando un condominio");
        Optional<Condominio> condo = condominioService.findCondoById(condominio.getId());
        if (condo.get().getEstado().equals("activo")) {
            Optional<Condominio> result = condominioService.updateCondoAcivo(condominio);
            return ResponseEntity.ok().body(result.get());
        }
        return ResponseEntity.notFound().build();
        /*
        if (result.isPresent()) return ResponseEntity.ok().body(result.get());
        return ResponseEntity.notFound().build();
        Función equivalente
         */
//        return result.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCondo(@PathVariable("id") long id) {
        logger.info("Eliminando un condominio", condominioService.deleteCondo(id));
        if (condominioService.deleteCondo(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/amenidad/{id}", method = RequestMethod.POST)
    public ResponseEntity<Amenidad> guardarAmenidad(@RequestBody Amenidad amenidad, @PathVariable long id) {
        logger.info("Se carga una amenidad");
        Optional<Condominio> condominio = condominioService.findCondoById(id);
        if (condominio.isPresent() & condominio.get().getEstado().equals("activo")) {
            amenidad.setCondominio(condominio.get());
            Optional<Amenidad> result = amenidadService.saveAmenidad(amenidad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/amenidades/{id}")
    public ResponseEntity<List<Amenidad>> getAmenidadesByIdCondo(@PathVariable long id) {
        logger.info("Buscando amenidades por condominio");
        List<Amenidad> result = amenidadRepository.findAmenidadByIdCondo(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
