package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Amenidad;
import com.cenfotec.condominio.domian.Condominio;

import java.util.List;
import java.util.Optional;

public interface AmenidadService {
    Optional<Amenidad> saveAmenidad(Amenidad amenidad);
//    List<Amenidad> getAllAmenidades(Long id);
}
