package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "propiedades")
public class Propiedad {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nombre_propiedad")
    String nombre;

    @Column(name = "ide_propiedad")
    String idePropiedad;

    public String getIdePropiedad() {
        return idePropiedad;
    }

    public void setIdePropiedad(String idePropiedad) {
        this.idePropiedad = idePropiedad;
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
