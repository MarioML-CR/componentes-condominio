package com.cenfotec.condominio.repositories;

import com.cenfotec.condominio.domian.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    @Query("select c from Cuota c where c.condominio.id = ?1 order by c.fecha")
    List<Cuota> getCuotasByIdCondo(long id);
}
