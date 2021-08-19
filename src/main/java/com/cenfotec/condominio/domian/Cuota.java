package com.cenfotec.condominio.domian;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Cuota {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdCouta;
    private float cuota;
    private Date fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Condominio condominio;

    public long getIdCouta() {
        return IdCouta;
    }

    public void setIdCouta(long idCouta) {
        IdCouta = idCouta;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
