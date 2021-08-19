package com.cenfotec.condominio.domian;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String nombre;
    private String cedJuridica;
    private String direccion;
    private String representante;
    private int cantidad;
    private String estado;

    /*
    Se deshabilita pues genera un refenrencia circular en la consulta
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "condominio")
    private Set<Amenidad> amenidades;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "condominio")
    private Set<Cuota> cuotas;
    */

    public Condominio() {
    }

    public Condominio(Long id, String nombre, String cedJuridica, String direccion,
                      String representante, int cantidad, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedJuridica = cedJuridica;
        this.direccion = direccion;
        this.representante = representante;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedJuridica() {
        return cedJuridica;
    }

    public void setCedJuridica(String cedJuridica) {
        this.cedJuridica = cedJuridica;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    /*
    public Set<Amenidad> getAmenidades() {
        return amenidades;
    }

    public void setAmenidades(Set<Amenidad> amenidades) {
        this.amenidades = amenidades;
    }

    public Set<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(Set<Cuota> cuotas) {
        this.cuotas = cuotas;
    }
    */
}
