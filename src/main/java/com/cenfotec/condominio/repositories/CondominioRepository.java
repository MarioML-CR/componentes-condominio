package com.cenfotec.condominio.repositories;

import com.cenfotec.condominio.domian.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    @Query("select c from Condominio c where c.estado = ?1")
    List<Condominio> findCondoByEstado(String estado);

}
