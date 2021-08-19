package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Cuota;

import java.util.List;
import java.util.Optional;

public interface CuotaService {
    Optional<Cuota> saveCuota(Cuota cuota);

}
