package com.cenfotec.condominio.repositories;

import com.cenfotec.condominio.domian.Condomino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CondominoRepositoy extends JpaRepository<Condomino, Long> {
    @Query("select c from Condomino c where c.condominio.id = ?1")
    List<Condomino> getAllCondominosByCondo(long id);
}
