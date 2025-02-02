package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "marcas")
public class Marca {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nombre_marcas")
    String nombre;

    @Column(name = "ide_marcas")
    String ideMarcas;

    public String getIdeMarcas() {
        return ideMarcas;
    }

    public void setIdeMarcas(String ideMarcas) {
        this.ideMarcas = ideMarcas;
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
