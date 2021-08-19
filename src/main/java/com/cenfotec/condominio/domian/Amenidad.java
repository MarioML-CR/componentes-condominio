package com.cenfotec.condominio.domian;

import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
public class Amenidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAmenidad;
    private String nombre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Condominio condominio;

    public long getIdAmenidad() {
        return idAmenidad;
    }

    public void setIdAmenidad(long idAmenidad) {
        this.idAmenidad = idAmenidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
}
