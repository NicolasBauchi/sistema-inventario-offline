package com.nicolas.api.controllers;

import com.nicolas.api.dao.EquipoDao;
import com.nicolas.api.models.Equipo;
import com.nicolas.api.service.ImprimirEtiqueta;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
public class EquipoControler {

    @Autowired
    private EquipoDao equipoDao;

    @Autowired
    private ImprimirEtiqueta ticket;

    @RequestMapping(value = "equipos", method = RequestMethod.GET)
    public ArrayList<Equipo> getEquipos(){
        return equipoDao.getEquipos();
    }

    //Agregar 1 equipo
    @RequestMapping(value = "ingresar-equipo" , method = RequestMethod.POST)
    public ResponseEntity<String> agregarEquipo(@RequestBody Equipo equipo){
        //con RequestBody: convierte el json que recibe en un objeto Equipo automaticamente.
        equipoDao.agregar(equipo);

        //Acá tiene que imprimir:

        //ticket.imprimir(equipo);
        ticket.imprimirTicket(equipo);


        return new ResponseEntity<String>("El equipo fue almacenado correctamente", HttpStatus.OK);
    }

    //Eliminar 1 equipo
    @RequestMapping(value = "equipos/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminar(@PathVariable int id){
        equipoDao.eliminar(id);
        return new ResponseEntity<String>("El equipo fue eliminado correctamente", HttpStatus.OK);
    }

    //Editar 1 equipo
    @RequestMapping(value = "equipos/{id}", method = RequestMethod.PUT)
    public void editarEquipo(@PathVariable Equipo equipo){
        equipoDao.editarEquipo(equipo);
    }

    // Vaciar / truncate tabla equipos
    @RequestMapping(value = "vaciar/equipos", method = RequestMethod.DELETE)
    public void vaciarTablaEquipos(){
        equipoDao.vaciarEquipos();
    }

    @RequestMapping(value = "descargarListado", method = RequestMethod.POST)
    public ResponseEntity<Resource> imprimirInfo() throws IOException {

        ArrayList<Equipo> losEquipos = equipoDao.getEquipos();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withDelimiter(';'));

            long milisegundos = System.currentTimeMillis();


            for (Equipo equipo : losEquipos) {
                String tipo = equipo.getTipo_equipo();
                String marca = equipo.getMarca();
                String serie = equipo.getSerie();
                String modelo = equipo.getModelo();
                String tms = tipo +" "+ marca +" "+ serie;
                String servicio = equipo.getServicio();
                String cliente = equipo.getCliente();
                String ubicacion = equipo.getUbicacion();
                String propiedad = equipo.getPropiedad();

                csvPrinter.printRecord(milisegundos, tms,tipo,marca,serie,
                        modelo, servicio,"","","","", cliente, ubicacion, "", propiedad, "");
            }


        csvPrinter.flush();

        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

        //Nombre de archivo a descargar:
        String institucion = "";
        String nombreArchivo ="";
        String fecha = "";
        Calendar c = Calendar.getInstance();
        fecha = Integer.toString(c.get(Calendar.DATE)) + "-" +
                Integer.toString( (c.get(Calendar.MONTH) + 1) ) + "-" +
                Integer.toString(c.get(Calendar.YEAR));

        if (losEquipos.size() > 0){
            institucion = losEquipos.get(0).getCliente();
            nombreArchivo = institucion + "-" + fecha + ".csv";

        }else{
            nombreArchivo = "inventario.csv";
        }



        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()));

        return ResponseEntity.ok().headers(headers).body(resource);


    }

}