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
       // String ENLACE = "http://66.97.36.141/biotrust-historial/#/login"; VIEJO-ANTERIOR
        String URL = "https://biotrust-sgem.com/manage-device-history-os";
        String nroSerie = this.getSerie();
        String data = "";
        String TIPO = this.getTipo_equipo();
        String MARCA = this.getMarca();
        String MODELO = this.getModelo();
        String IDCLIENTE = this.getClienteide();
        String UBICACION = this.getUbicacion();

        String TIPOIDE = this.getTipoequipoide();
        String MARCAIDE = this.getMarcaide();
        String MODELOIDE = this.getModeloide();

        //String ENLACE_AUX = ENLACE + "/" + IDCLIENTE + "/" + TIPO + "/" + MARCA + "/" + MODELO + "/" + nroSerie;

        String ENLACEFINAL = URL + "/?idCliente=" + IDCLIENTE + "&idTipo=" +
                TIPOIDE + "&idFabricante=" + MARCAIDE + "&idModelo=" + MODELOIDE + "&serie=" + nroSerie;

        //String ENLACEFINAL = ENLACE_AUX.replace(" ", "%20");
        //URL NUEVA:
        //https://biotrust-sgem.com/manage-device-history-os/?idCliente=%27idcliente%27&idTipo=%27
        // idtipo%E2%80%99&idFabricante=%27idfabricange%27&idModelo=%27idmodelo%E2%80%99&serie=%27serie%27

       // String IMAGENBTS =
        //        "^FO380,20^GFA,1270,1270,10,,T03F8,T0FFE,P01E00IF,P01F01IF,P01F03F9F8,P01F03E0F8,P01F03E078,P01F03E07C,:::::::::::P01F03C07C,P01F07C07C,Q0IFC07C,Q0IF807C,Q07FF807C,Q03FF007C,R0FC0078,,Q0F,:::::::::Q0LFC,::::Q0F,L0CJ0F,K03CJ0F,K078J0F,J08L0F,I01M0F,I02I01F00F,K0307F00F,K0F87F80F,J01F87F00FI038,I0E3F07E00B001FF,I0E3EN03FF8,001C1CK03FCIFC,003CM07KFC,003N0JFC7C,N0800JF07C,M03E00F8FF07C,M07E00F07E03C,M0FF00F03E03C,003J0FE00F03E07C,0078I07E00F03E07C,00FJ07C00F03E03C,00FN0F03E03C,:00EN0F03E03C,Q0F03E03C,::M03800F01E03C,00407807E00FJ07C,00E0FC0FE00FJ07C,01E0FC0FE00LFC,::01E0F807C00LFC,01E070038007KF8,008,,::::20C07007801F,21E0F80FC03F8,31E0FC0FE03F8,21E0FC0FE03FC,00E0FC0FE03FC,00C07C0FE03F8,J03807E03F8,M03800E,,::004V08006U018007038N0F8001800707C03C00F01F8,00307C07E03F01F8,J03E07E03F81F8,J01C07E03F81F8,M03E01F01E,N0800C,,:V01,K08P07,J01EN0C06,K0F03C00E01E,K0703F03F03C,M03F03F018,N0E01E,,U01,,S03,N0E01C07,N0701C,,::::::^FS";

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
                "^FT300,357^A0B,26,26^FH\\^FD" + this.getCliente() + "^FS\n" +
                "^FT327,357^A0B,26,26^FH\\^FD" + nroSerie + "^FS\n" +
                "^FT355,357^A0B,26,26^FH\\^FD" + TIPO + "^FS\n" +
                "^FT382,357^A0B,26,26^FH\\^FD" + MARCA + "^FS\n" +
                "^FT409,357^A0B,26,26^FH\\^FD" + MODELO + "^FS\n" +
                "^FT436,357^A0B,26,26^FH\\^FD" + UBICACION + "^FS\n" +

                IMAGENBST + "^FS\n" +
                "^PQ1,0,1,Y^XZ";

        return data;
    }
}