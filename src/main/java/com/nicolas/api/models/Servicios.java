package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicios {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nombre_servicios")
    String nombre;

    @Column(name = "ide_servicios")
    String ideServicios;

    public String getIdeServicios() {
        return ideServicios;
    }

    public void setIdeServicios(String ideServicios) {
        this.ideServicios = ideServicios;
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
