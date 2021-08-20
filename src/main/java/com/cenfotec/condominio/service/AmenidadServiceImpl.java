package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Amenidad;
import com.cenfotec.condominio.repositories.AmenidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmenidadServiceImpl implements AmenidadService{
    @Autowired
    AmenidadRepository amenidadRepository;

    @Override
    public Optional<Amenidad> saveAmenidad(Amenidad amenidad) {
        return Optional.of(amenidadRepository.save(amenidad));
    }

}
