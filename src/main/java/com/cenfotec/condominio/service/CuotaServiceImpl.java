package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Cuota;
import com.cenfotec.condominio.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CuotaServiceImpl implements CuotaService{
    @Autowired
    CuotaRepository cuotaRepository;


    @Override
    public Optional<Cuota> saveCuota(Cuota cuota) {
        cuota.setFecha(new Date());
        return Optional.of(cuotaRepository.save(cuota));
    }
}
