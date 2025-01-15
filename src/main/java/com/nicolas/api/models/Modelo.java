package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name= "modelos")
public class Modelo {

    @Column(name= "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name= "nombre_modelos")
    String nombre;

    @Column(name= "ide_modelos")
    String ideModelos;

    public String getIdeModelos() {
        return ideModelos;
    }

    public void setIdeModelos(String ideModelos) {
        this.ideModelos = ideModelos;
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