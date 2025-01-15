package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tipoequipos")
public class TipoEquipos {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(name = "nombre_tipos")
    String nombre;

    @Column(name = "ide_tipos")
    String ideTipos;

    public String getIdeTipos() {
        return ideTipos;
    }

    public void setIdeTipos(String ideTipos) {
        this.ideTipos = ideTipos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
