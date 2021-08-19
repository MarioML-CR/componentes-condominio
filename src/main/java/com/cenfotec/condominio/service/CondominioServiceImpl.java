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
        condominio.setEstado("activo");
        return Optional.of(condominioRepository.save(condominio));
    }

    @Override
    public List<Condominio> getAllCondos() {
        return condominioRepository.findAll();
    }

//    @Override
//    public List<Condominio> getAllCondosByEstado(String estado) {
//        List<Condominio> condominios = condominioRepository.getAllCondosByEstado();
//        return getAllCondosByEstado("estado");
//    }

    @Override
    public Optional<Condominio> findCondoById(long id) {
        return condominioRepository.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public Optional<Condominio> updateEstadoCondo(long id) {
        Optional<Condominio> record = condominioRepository.findById(id);
        if (record.isPresent()) {
            Condominio data = record.get();
            if (record.get().getEstado() == "activo") {
                data.setEstado("inactivo");
            } else {
                data.setEstado("activo");
            }
            return Optional.of(condominioRepository.save(data));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Condominio> updateCondoAcivo(Condominio condominio) {
        Optional<Condominio> record = condominioRepository.findById(condominio.getId());
        if (record.isPresent() & record.get().getEstado() == "activo") {
            Condominio data = record.get();
            if (condominio.getNombre() != null) data.setNombre(condominio.getNombre());
            if (condominio.getCedJuridica() != null) data.setCedJuridica(condominio.getCedJuridica());
            if (condominio.getDireccion() != null) data.setDireccion(condominio.getDireccion());
            if (condominio.getRepresentante() != null) data.setRepresentante(condominio.getRepresentante());
            if (condominio.getCantidad() > 0) data.setCantidad(condominio.getCantidad());
            if (condominio.getEstado() != null) data.setEstado(condominio.getEstado());
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
