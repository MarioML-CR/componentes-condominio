package com.cenfotec.condominio.controller;

import com.cenfotec.condominio.domian.Amenidad;
import com.cenfotec.condominio.domian.Condominio;
import com.cenfotec.condominio.domian.Condomino;
import com.cenfotec.condominio.domian.Cuota;
import com.cenfotec.condominio.repositories.AmenidadRepository;
import com.cenfotec.condominio.repositories.CondominioRepository;
import com.cenfotec.condominio.repositories.CondominoRepositoy;
import com.cenfotec.condominio.repositories.CuotaRepository;
import com.cenfotec.condominio.service.AmenidadService;
import com.cenfotec.condominio.service.CondominioService;
import com.cenfotec.condominio.service.CondominoService;
import com.cenfotec.condominio.service.CuotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private CuotaRepository cuotaRepository;
    @Autowired
    private CuotaService cuotaService;
    @Autowired
    private CondominoService condominoService;
    @Autowired
    private CondominoRepositoy condominoRepositoy;

    /*
    Carga de Condominios
     */

    /**
     * Método:              createCondo
     * Descripción:         Método que permite crear un condominio
     * @param condominio    variable de entity que representa un condominio
     * @return              Información del condominio registrado
     */
    @PostMapping
    public Condominio createCondo(@RequestBody Condominio condominio) {
        logger.info("Creando un condominio", condominioService.saveCondo(condominio).get());
        return condominioService.saveCondo(condominio).get();
    }

    /**
     * Método:              getAllCondos
     * Descripción:         Método que permite listar todos los condominios
     * @return              Lista de todos los condominios registrados
     */
    @GetMapping
    public List getAllCondos() {
        logger.info("generando lista de condominios", condominioService.getAllCondos());
        return condominioService.getAllCondos();
    }

    /**
     * Método:              getCondoById
     * Descripción:         Método que permite listar un condominio según su id
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Información del condominio si existe, o mensaje NotFound si no existe
     */
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

    /**
     * Método:              getCondosActivos
     * Descripción:         Método que permite listar los condominio activos
     * @param activo        variable de tipo string que representa el estado de un condominio inactivo
     * @return              Lista de condominios que están activos
     */
    @GetMapping("/activo")
    public ResponseEntity<List<Condominio>> getCondosActivos(String activo) {
        logger.info("Listando lod condominios activos", condominioRepository.findCondoByEstado(activo));
        activo = "activo";
        List<Condominio> condominios = condominioRepository.findCondoByEstado(activo);
        return new ResponseEntity<>(condominios, HttpStatus.OK);
    }

    /**
     * Método:              getCondosInactivos
     * Descripción:         Método que permite listar los condominio inactivos
     * @param inactivo      variable de tipo string que representa el estado de un condominio inactivo
     * @return              Lista de condominios que están inactivos
     */
    @GetMapping("/inactivo")
    public ResponseEntity<List<Condominio>> getCondosInactivos(String inactivo) {
        logger.info("Listando lod condominios inactivos", condominioRepository.findCondoByEstado(inactivo));
        inactivo = "inactivo";
        List<Condominio> condominios = condominioRepository.findCondoByEstado(inactivo);
        return new ResponseEntity<>(condominios, HttpStatus.OK);
    }

    /**
     * Método:              cambiarEstadoCondo
     * Descripción:         Método que permite cambiar el estado de condominio si está activo
     *                      lo pasa a inactivo y si está inactivo lo pasa a activo
     * @param condominio    variable de tipo objeto entity que representa un condominio
     * @return              Mensaje ok cuando el condominio exista y notFound si no existe
     */
    @PutMapping("/estado")
    public ResponseEntity<Condominio> cambiarEstadoCondo(@RequestBody Condominio condominio) {
        logger.info("Cambio de estado de condominio");
        Optional<Condominio> condo = condominioService.findCondoById(condominio.getId());
        if (condo.isPresent()) {
            Optional<Condominio> result = condominioService.updateEstadoCondo(condo.get().getId());
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método:              updateCondoActivo
     * Descripción:         Método que permite modificar los atributos de la entidad Condominio
     * @param condominio    variable de tipo objeto entity que representa un condominio
     * @return              Mensaje ok cuando el condominio exista y esté activo y notFound si no existe
     *                      o está inactivo
     */
    @PutMapping(value = "/actualizarcondo")
    public ResponseEntity<Condominio> updateCondoActivo(@RequestBody Condominio condominio) {
        logger.info("Actualizando un condominio");
        Optional<Condominio> condo = condominioService.findCondoById(condominio.getId());
        if (condo.isPresent()) {
            if (condo.get().getEstado().equals("activo")) {
                Optional<Condominio> result = condominioService.updateCondoAcivo(condominio);
                return ResponseEntity.ok().body(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método:              deleteCondo
     * Descripción:         Método que permite eliminar de la base de dato un condominio
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Mensaje ok cuando el condominio exista y notFound si no existe
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCondo(@PathVariable("id") long id) {
        logger.info("Eliminando un condominio", condominioService.deleteCondo(id));
        if (condominioService.deleteCondo(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
    /*
    Registro de amenidades
     */

    /**
     * Método:              guardarAmenidad
     * Descripción:         Método que permite insertar una amenidad a un condominio por su id
     * @param amenidad      variable de tipo Entity que representa la amenidad
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Mensaje ok cuando el condominio exista y esté activo y notFound si no existe
     *                      o está inactivo
     */
    @PostMapping(value = "/amenidad/{id}")
    public ResponseEntity<Amenidad> guardarAmenidad(@RequestBody Amenidad amenidad, @PathVariable long id) {
        logger.info("Se carga una amenidad");
        Optional<Condominio> condominio = condominioService.findCondoById(id);
        if (condominio.isPresent() && condominio.get().getEstado().equals("activo")) {
            amenidad.setCondominio(condominio.get());
            Optional<Amenidad> result = amenidadService.saveAmenidad(amenidad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método:              getAmenidadesByIdCondo
     * Descripción:         Método que permite listar las amenidades de un condominio (por su id)
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Listado de amenidades registradas
     */
    @GetMapping(value = "/amenidad/{id}")
    public ResponseEntity<List<Amenidad>> getAmenidadesByIdCondo(@PathVariable long id) {
        logger.info("Buscando amenidades por condominio");
        List<Amenidad> result = amenidadRepository.findAmenidadesdByIdCondo(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /*
    Registro de cuotas
     */

    /**
     * Método:              createCuota
     * Descripción:         Método que permite crear una cuota al condominio ingresado por su id
     * @param cuota         variable de tipo float que representa la cuota del condominio
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Mensaje de ok, o notFoud de la creación de la cuota, si el condominio
     *                      está inactivo no registra la cuota y genera el mensaje NotFound
     */
    @PostMapping(value = "/cuota/{id}")
    public ResponseEntity<Cuota> createCuota(@RequestBody Cuota cuota, @PathVariable long id) {
        logger.info("Creando una cuota");
        Optional<Condominio> condominio = condominioService.findCondoById(id);
        if (condominio.isPresent() && condominio.get().getEstado().equals("activo")) {
            cuota.setCondominio(condominio.get());
            Optional<Cuota> result = cuotaService.saveCuota(cuota);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método:              getCuotasByIdCondo
     * Descripción:         Método que permite listar las cuotas por el id del Condominio
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Lista de cuotas correspondientes al id del condominio ingresado
     */
    @GetMapping(value = "/cuota/{id}")
    public ResponseEntity<List<Cuota>> getCuotasByIdCondo(@PathVariable long id) {
        logger.info("Listando cuotas por el id del condominio");
        List<Cuota> result = cuotaRepository.getCuotasByIdCondo(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /*
    Registro de Condominos
     */

    /**
     * Método:              createCondomino
     * Descripción:         Método que permite insertar un condómino a un condominio por su id
     * @param condomino      variable de tipo Entity que representa la amenidad
     * @param id            variable de tipo long que representa el id del Condomino
     * @return              Mensaje ok cuando el condominio exista y esté activo y notFound si no existe
     *                      o está inactivo
     */
    @PostMapping(value = "/condomino/{id}")
    public ResponseEntity<Amenidad> createCondomino(@RequestBody Condomino condomino, @PathVariable long id) {
        logger.info("Se carga un condómino");
        Optional<Condominio> condominio = condominioService.findCondoById(id);
        if (condominio.isPresent() && condominio.get().getEstado().equals("activo")) {
            condomino.setCondominio(condominio.get());
            Optional<Condomino> result = condominoService.saveCondomino(condomino);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Método:              updateCondomino
     * Descripción:         Método que permite modificar los atributos de la entidad Condomino
     * @param condomino    variable de tipo objeto entity que representa un condomino
     * @return              Mensaje ok cuando el condomino existe y esté activo y notFound si no existe
     *                      o está inactivo
     */
    @PutMapping(value = "/actualizacioncondomino")
    public ResponseEntity<Condomino> updateCondomino(@RequestBody Condomino condomino) {
        logger.info("Actualizando un condomino");
        Optional<Condomino> condo = condominoService.findCondominoById(condomino.getIdCondomino());
        if (condo.isPresent()) {
            if (condo.get().getEstado().equals("condómino") &
                    condo.get().getCondominio().getEstado().equals("activo")) {
                Optional<Condomino> result = condominoService.updateCondomino(condomino);
                return ResponseEntity.ok().body(result.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Método:              updateEstadoCondomino
     * Descripción:         Método que permite modificar el estado de un Condomino
     * @param id            variable de tipo long que representa el id del condomino
     * @return              Mensaje ok cuando el condominio exista y esté activo y notFound si no existe
     *                      o está inactivo
     */
    @PutMapping(value = "/estadodelcondomino/{id}")
    public ResponseEntity<Condomino> updateEstadoDelCondominoById(@PathVariable long id) {
        logger.info("Actualizando un condomino");
        Optional<Condomino> condomino = condominoService.findCondominoById(id);
        if (condomino.isPresent() && condomino.get().getCondominio().getEstado().equals("activo")) {
            Optional<Condomino> result = condominoService.updateEstadoCondomino(condomino.get());
            return ResponseEntity.ok().body(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Método:              getAmenidadesByIdCondo
     * Descripción:         Método que permite listar las amenidades de un condominio (por su id)
     * @param id            variable de tipo long que representa el id del Condominio
     * @return              Listado de amenidades registradas
     */
    @GetMapping(value = "/condomino/{id}")
    public ResponseEntity<List<Condomino>> getCondominosByIdCondo(@PathVariable long id) {
        logger.info("Listando los condóminos de un condominio");
        List<Condomino> result = condominoRepositoy.getAllCondominosByCondo(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
