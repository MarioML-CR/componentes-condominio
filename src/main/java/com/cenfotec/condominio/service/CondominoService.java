package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Condomino;

import java.util.Optional;

public interface CondominoService {
    Optional<Condomino> saveCondomino(Condomino condomino);
    Optional<Condomino> findCondominoById(long id);
    Optional<Condomino> updateCondomino(Condomino condomino);
    Optional<Condomino> updateEstadoCondomino(Condomino condomino);
}
