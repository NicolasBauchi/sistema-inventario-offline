package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nombre_cliente")
    String nombre;

    @Column(name = "identificliente")
    String identificliente;

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

    public String getIdentificliente() {
        return identificliente;
    }

    public void setIdentificliente(String identificliente) {
        this.identificliente = identificliente;
    }
}
