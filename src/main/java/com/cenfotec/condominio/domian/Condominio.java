package com.cenfotec.condominio.domian;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
//    @Column(name = "nombre")
    private String nombre;
//    @Column(name = "cedJuridica")
    private String cedJuridica;
//    @Column(name = "direccion")
    private String direccion;
//    @Column(name = "representante")
    private String representante;
//    @Column(name = "cantidad")
    private int cantidad;
//    @Column(name = "cuota")
    private float cuota;
//    @Column(name = "estado")
    private String estado;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "condominio")
//    private Set<Amenidad> amenidades;

    public Condominio() {
    }

    public Condominio(Long id, String nombre, String cedJuridica, String direccion,
                      String representante, int cantidad, float cuota, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedJuridica = cedJuridica;
        this.direccion = direccion;
        this.representante = representante;
        this.cantidad = cantidad;
        this.cuota = cuota;
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

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public Set<Amenidad> getAmenidades() {
//        return amenidades;
//    }
//
//    public void setAmenidades(Set<Amenidad> amenidades) {
//        this.amenidades = amenidades;
//    }
}
