package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Condominio;

import java.util.List;
import java.util.Optional;

public interface CondominioService {
    Optional<Condominio> saveCondo(Condominio condominio);
    List<Condominio> getAllCondos();
    Optional<Condominio> findCondoById(long id);
    Optional<Condominio> updateCondo(Condominio condominio);
    boolean deleteCondo(long id);
}
