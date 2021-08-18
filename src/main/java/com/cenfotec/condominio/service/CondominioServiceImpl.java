package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Condominio;
import com.cenfotec.condominio.repositories.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondominioServiceImpl implements CondominioService{
    @Autowired
    CondominioRepository condominioRepository;

    @Override
    public Optional<Condominio> saveCondo(Condominio condominio) {
        return Optional.of(condominioRepository.save(condominio));
    }

    @Override
    public List<Condominio> getAllCondos() {
        return condominioRepository.findAll();
    }

    @Override
    public Optional<Condominio> findCondoById(long id) {
        return condominioRepository.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public Optional<Condominio> updateCondo(Condominio condominio) {
        Optional<Condominio> record = condominioRepository.findById(condominio.getId());
        if (record.isPresent()) {
            Condominio data = record.get();
            if (condominio.getNombre() != null) data.setNombre(condominio.getNombre());
            if (condominio.getCedJuridica() != null) data.setCedJuridica(condominio.getCedJuridica());
            if (condominio.getDireccion() != null) data.setDireccion(condominio.getDireccion());
            if (condominio.getRepresentante() != null) data.setRepresentante(condominio.getRepresentante());
            if (condominio.getCantidad() > 0) data.setCantidad(condominio.getCantidad());
            if (condominio.getCuota() > 0.0) data.setCuota(condominio.getCuota());
            return Optional.of(condominioRepository.save(data));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCondo(long id) {
        Optional<Condominio> result = condominioRepository.findById(id);
        if (result.isPresent()) {
            condominioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
