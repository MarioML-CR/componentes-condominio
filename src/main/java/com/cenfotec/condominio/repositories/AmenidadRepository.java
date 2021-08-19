package com.cenfotec.condominio.repositories;

import com.cenfotec.condominio.domian.Amenidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AmenidadRepository extends JpaRepository<Amenidad, Long> {

    @Query("select a from Amenidad a where a.condominio.id = ?1")
    List<Amenidad> findAmenidadByIdCondo(long id);
}
