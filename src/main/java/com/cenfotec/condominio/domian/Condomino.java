package com.cenfotec.condominio.domian;

import javax.persistence.*;

@Entity
public class Condomino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCondomino;
    private String nombre;
    private String telefono;
    private String correo;
    private String estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Condominio condominio;

    public long getIdCondomino() {
        return idCondomino;
    }

    public void setIdCondomino(long idCondomino) {
        this.idCondomino = idCondomino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
}
