package com.cenfotec.condominio.service;

import com.cenfotec.condominio.domian.Condominio;
import com.cenfotec.condominio.domian.Condomino;
import com.cenfotec.condominio.repositories.CondominioRepository;
import com.cenfotec.condominio.repositories.CondominoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@Service
public class CondominoServiceImpl implements CondominoService{
    @Autowired
    CondominoRepositoy condominoRepositoy;
    @Autowired
    CondominioRepository condominioRepository;

    @Override
    public Optional<Condomino> saveCondomino(Condomino condomino) {
        condomino.setEstado("condómino");
        return Optional.of(condominoRepositoy.save(condomino));
    }

    @Override
    public Optional<Condomino> findCondominoById(long id) {
        return condominoRepositoy.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public Optional<Condomino> updateCondomino(Condomino condomino) {
        Optional<Condomino> record = condominoRepositoy.findById(condomino.getIdCondomino());
        Condomino data = record.get();
        if (condomino.getNombre() != null) data.setNombre(condomino.getNombre());
        if (condomino.getCorreo() != null) data.setCorreo(condomino.getCorreo());
        if (condomino.getTelefono() != null) data.setTelefono(condomino.getTelefono());
        data.setEstado("condómino");
        return Optional.of(condominoRepositoy.save(data));
    }

    @Override
    public Optional<Condomino> updateEstadoCondomino(Condomino condomino) {
        if (Objects.equals(condomino.getEstado(), "condómino")) {
            condomino.setEstado("ex-condómino");
        } else {
            condomino.setEstado("condómino");
        }
        return Optional.of(condominoRepositoy.save(condomino));
    }
}
