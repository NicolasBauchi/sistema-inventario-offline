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

    @Column(name = "clienteide")
    private String clienteide;

    @Column(name = "marcaide")
    private String marcaide;

    @Column(name = "modeloide")
    private String modeloide;

    @Column(name = "tipoequipoide")
    private String tipoequipoide;

    @Column(name = "servicioide")
    private String servicioide;

    @Column(name = "estadoide")
    private String estadoide;

    @Column(name = "propiedadide")
    private String propiedadide;

    public String getPropiedadide() {
        return propiedadide;
    }

    public void setPropiedadide(String propiedadide) {
        this.propiedadide = propiedadide;
    }

    public String getEstadoide() {
        return estadoide;
    }

    public void setEstadoide(String estadoide) {
        this.estadoide = estadoide;
    }

    public String getMarcaide() {
        return marcaide;
    }

    public void setMarcaide(String marcaide) {
        this.marcaide = marcaide;
    }

    public String getModeloide() {
        return modeloide;
    }

    public void setModeloide(String modeloide) {
        this.modeloide = modeloide;
    }

    public String getTipoequipoide() {
        return tipoequipoide;
    }

    public void setTipoequipoide(String tipoequipoide) {
        this.tipoequipoide = tipoequipoide;
    }

    public String getServicioide() {
        return servicioide;
    }

    public void setServicioide(String servicioide) {
        this.servicioide = servicioide;
    }

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

    public String getClienteide() {
        return clienteide;
    }

    public void setClienteide(String clienteide) {
        this.clienteide = clienteide;
    }

    //Data para imprimir:
    public String generarTicket(){
        String URL = "https://biotrust-sgem.com/manage-device-history-os";
        String nroSerie = this.getSerie();
        String data = "";
        String TIPO = this.getTipo_equipo();
        String MARCA = this.getMarca();
        String MODELO = this.getModelo();
        String IDCLIENTE = this.getClienteide();
        String UBICACION = this.getUbicacion();
        String SERVICIO = this.getServicio();

        String TIPOIDE = this.getTipoequipoide();
        String MARCAIDE = this.getMarcaide();
        String MODELOIDE = this.getModeloide();



        String lineaCliente = "";
        int linea0 = 300;

        //Tamaño letra cliente
        int tamanio = 26;
        int intCli = this.getCliente().length();
        //Pregunto para saber si achico la letra:
        if (intCli >= 50){
            tamanio = 20;
        }

        //Pregunto si hago en doble fila
        if (intCli >= 25){
            String[] auxstr = this.getCliente().split(" ");
            String firstLine="", secondLine="";
            for (int i = 0; i < auxstr.length; i++) {

                //Si el tamaño es 26 entonces no se achico
                //hago 2 lineas con la primera 2 palabras.
                if(tamanio == 26){
                    if (i<2){
                        firstLine += auxstr[i] + " ";
                    }
                    if (i>=2){
                        secondLine += auxstr[i] + " ";
                    }
                }else {
                    if (i<3){
                        firstLine += auxstr[i] + " ";
                    }
                    if (i>=3){
                        secondLine += auxstr[i] + " ";
                    }
                }

            }
            //Aca es con doble linea:
            linea0 = 327;
            int auxlinea0 = linea0-27;
            lineaCliente=
                    "^FT" + auxlinea0 + ",357^A0B,26,"+ tamanio +"^FH\\^FD" + firstLine + "^FS\n " +
                    "^FT" + linea0 + ",357^A0B,26,"+ tamanio +"^FH\\^FD" + secondLine + "^FS\n ";


        }else{
            //Una sola linea y sin achicar:
            lineaCliente= "^FT" + linea0 + ",357^A0B,26,"+ tamanio +"^FH\\^FD" + this.getCliente() + "^FS\n";
        }

        int linea1 = linea0+27;
        int linea2 = linea1+27;
        int linea3 = linea2+27;
        int linea4 = linea3+27;
        int linea5 = linea4+27;

        //Tamaño letra serie
        int tamanio1 = 26;
        int intTam1 = this.getSerie().length();
        //Pregunto para saber si achico la letra:
        if (intTam1 >= 25){
            tamanio1 = 20;
        }
        //Tamaño letra tipo equipo
        int tamanio2 = 26;
        int intTam2 = this.getTipo_equipo().length();
        //Pregunto para saber si achico la letra:
        if (intTam2 >= 25){
            tamanio2 = 20;
        }
        //Tamaño letra marca
        int tamanio3 = 26;
        int intTam3 = this.getMarca().length();
        //Pregunto para saber si achico la letra:
        if (intTam3 >= 18){
            tamanio3 = 20;
        }
        //Tamaño letra modelo
        int tamanio4 = 26;
        int intTam4 = this.getModelo().length();
        //Pregunto para saber si achico la letra:
        if (intTam4 >= 18){
            tamanio4 = 20;
        }
        //Tamaño letra servicio
        int tamanio5 = 26;
        int intTam5 = this.getServicio().length();
        //Pregunto para saber si achico la letra:
        if (intTam5 >= 18){
            tamanio5 = 20;
        }



        String ENLACEFINAL = URL + "/?idCliente=" + IDCLIENTE + "&idTipo=" +
                TIPOIDE + "&idFabricante=" + MARCAIDE + "&idModelo=" + MODELOIDE + "&serie=" + nroSerie;

        //URL NUEVA:
        //https://biotrust-sgem.com/manage-device-history-os/?idCliente=%27idcliente%27&idTipo=%27
        // idtipo%E2%80%99&idFabricante=%27idfabricange%27&idModelo=%27idmodelo%E2%80%99&serie=%27serie%27


        String IMAGENBST =
                "^FO380,20^GFA,1300,1300,10,,:::::::::P01E,::::::::P01F,P01LF,:::P01F,P01E,:::::::P01E007F,T0FF8,Q0E01FFC,P01E03FFE,P01E03E3E,P01E07C1E,P01E07C1F,:::::::::::L0EI01F0781F,K01CJ0F8F81F,Q0IF81F,J04L07FF01F,J08001F007FE01F,K0183F801FC01F,K07C3F8,K0FC3FK01F8,I038F81EK07FC,I078FK01F0FFE,I07M07KF,I06M0JFBF,Q0IFE1F,M01E00FBFC1F,M03F01F0FC1F,M07F01F07C1F,I0EI07F01F07C1F,001EI03E01F07C1F,001EI03C01F07C1F,001CL01F07C1F,:001M01F07C1F,P01F07C0F,P01F07C1F,:K0C03E01F0081F,00383E07E01FI01F,003C3E07F01LF,007C3E07F01LF,007C3E07E01LF,00383E03E00LF,003818018007JFE,,::::041J01800C,04383C03E01F,04383E07E03F8,04383E07F03F8,00383E07F03F8,00181E07F03F8,K0C03E03F,N0800C,,::0018T04,001C08N0CI04,001C1E00800401E,I0E1F03E01F03F,I041F03F03F03F,K0F03F03F03E,M01E03E03C,N0C00C,,:V0C,K06O01C,K078L01818,K0381E01E038,K0181F03E078,N0F03E,,U02,,S06,N060080E,N0703C,,::::::::^FS";
        //String IMAGENBTS = "";


        data = "\u0010CT~~CD,~CC^~CT~\n" +
                "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR2,2~SD15^JUS^LRN^CI0^XZ\n" +
                "^XA\n" +
                "^MMT\n" +
                "^PW480\n" +
                "^LL0360\n" +
                "^LS0\n" +
                "^FT20,345^BQN,2,5\n" +
                "^FDMA," + ENLACEFINAL + "^FS \n" +
                 lineaCliente +
                "^FT"+linea1+",357^A0B,26,"+ tamanio1 +"^FH\\^FD" + nroSerie + "^FS\n" +
                "^FT"+linea2+",357^A0B,26,"+ tamanio2 +"^FH\\^FD" + TIPO + "^FS\n" +
                "^FT"+linea3+",357^A0B,26,"+ tamanio3 +"^FH\\^FD" + MARCA + "^FS\n" +
                "^FT"+linea4+",357^A0B,26,"+ tamanio4 +"^FH\\^FD" + MODELO + "^FS\n" +
                "^FT"+linea5+",357^A0B,26,"+ tamanio5 +"^FH\\^FD" + SERVICIO + "^FS\n" +

                IMAGENBST + "^FS\n" +
                "^PQ1,0,1,Y^XZ";

        return data;
    }

    /*
    * "^FT327,357^A0B,26,26^FH\\^FD" + nroSerie + "^FS\n" +
                "^FT355,357^A0B,26,26^FH\\^FD" + TIPO + "^FS\n" +
                "^FT382,357^A0B,26,26^FH\\^FD" + MARCA + "^FS\n" +
                "^FT409,357^A0B,26,26^FH\\^FD" + MODELO + "^FS\n" +
                "^FT436,357^A0B,26,26^FH\\^FD" + UBICACION + "^FS\n" +
    * */

}

/*
Dimensiones etiqueta: 60mm x 46.1mm

CODIGO EN ZPL PURO copiado en: 24/01/25
\u0010~~CD,~CC^~CT~
^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR2,2~SD15^JUS^LRN^CI0^XZ
^XA
^MMT
^PW480
^LL0360
^LS0
^FT20,345^BQN,2,5
^FDMA,https://biotrust-sgem.com/manage-device-history-os/?idCliente=idcliente&idTipo=idtipo&idFabricante=idfabricante&idModelo=idmodelo&serie=serie^FS
^FT300,357^A0B,26,26^FH\^FDcliente^FS
^FT327,357^A0B,26,26^FH\^FDnroSerie^FS
^FT355,357^A0B,26,26^FH\^FDTIPO^FS
^FT382,357^A0B,26,26^FH\^FDMARCA^FS
^FT409,357^A0B,26,26^FH\^FDMODELO ^FS
^FT436,357^A0B,26,26^FH\^FDUBICACION ^FS

^FO380,20^GFA,1300,1300,10,,:::::::::P01E,::::::::P01F,P01LF,:::P01F,P01E,:::::::P01E007F,T0FF8,Q0E01FFC,P01E03FFE,P01E03E3E,P01E07C1E,P01E07C1F,:::::::::::L0EI01F0781F,K01CJ0F8F81F,Q0IF81F,J04L07FF01F,J08001F007FE01F,K0183F801FC01F,K07C3F8,K0FC3FK01F8,I038F81EK07FC,I078FK01F0FFE,I07M07KF,I06M0JFBF,Q0IFE1F,M01E00FBFC1F,M03F01F0FC1F,M07F01F07C1F,I0EI07F01F07C1F,001EI03E01F07C1F,001EI03C01F07C1F,001CL01F07C1F,:001M01F07C1F,P01F07C0F,P01F07C1F,:K0C03E01F0081F,00383E07E01FI01F,003C3E07F01LF,007C3E07F01LF,007C3E07E01LF,00383E03E00LF,003818018007JFE,,::::041J01800C,04383C03E01F,04383E07E03F8,04383E07F03F8,00383E07F03F8,00181E07F03F8,K0C03E03F,N0800C,,::0018T04,001C08N0CI04,001C1E00800401E,I0E1F03E01F03F,I041F03F03F03F,K0F03F03F03E,M01E03E03C,N0C00C,,:V0C,K06O01C,K078L01818,K0381E01E038,K0181F03E078,N0F03E,,U02,,S06,N060080E,N0703C,,::::::::^FS
^PQ1,0,1,Y^XZ


* */
