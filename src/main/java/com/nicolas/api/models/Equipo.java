package com.nicolas.api.models;

import javax.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "propiedad")
    private String propiedad;
    @Column(name = "serie")
    private String serie;
    @Column(name = "tipo")
    private String tipo_equipo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "ubicacion")
    private String ubicacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo_equipo() {
        return tipo_equipo;
    }

    public void setTipo_equipo(String tipo_equipo) {
        this.tipo_equipo = tipo_equipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //Data para imprimir:
    public String generarTicket(){
        String nroSerie = this.getSerie();
        String data = "";


        data = "\u0010CT~~CD,~CC^~CT~\n" +
                "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR2,2~SD15^JUS^LRN^CI0^XZ\n" +
                "^XA\n" +
                "^MMT\n" +
                "^PW480\n" +
                "^LL0360\n" +
                "^LS0\n" +
                "^FT22,356^BQN,2,8\n" +
                "^FDMA,http://66.97.36.141/dashboard/views/trace/" + nroSerie + "^FS \n" +
                "^FT350,357^A0B,28,28^FH\\^FD" + this.getCliente() + "^FS\n" +
                "^FT459,357^A0B,28,28^FH\\^FD" + nroSerie + "^FS\n" +
                "^FT377,357^A0B,28,28^FH\\^FD" + this.getTipo_equipo() + "^FS\n" +
                "^FT432,357^A0B,28,28^FH\\^FD" + this.getModelo() + "^FS\n" +
                "^FT405,357^A0B,28,28^FH\\^FD" + this.getMarca() + "^FS\n" +
                "^PQ1,0,1,Y^XZ";

        return data;
    }
}