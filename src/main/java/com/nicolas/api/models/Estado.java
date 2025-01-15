package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "estados")
public class Estado {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nombre_estado")
    String nombre;

    @Column(name = "ide_estado")
    String ideEstado;

    public String getIdeEstado() {
        return ideEstado;
    }

    public void setIdeEstado(String ideEstado) {
        this.ideEstado = ideEstado;
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
