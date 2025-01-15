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
import java.util.List;

@RestController
public class EquipoControler {

    @Autowired
    private EquipoDao equipoDao;

    @Autowired
    private ImprimirEtiqueta ticket;

    @RequestMapping(value = "equipos", method = RequestMethod.GET)
    public List<Equipo> getEquipos(){
        return this.equipoDao.getEquipos();
    }

    //Agregar 1 equipo
    @RequestMapping(value = "ingresar-equipo" , method = RequestMethod.POST)
    public ResponseEntity<String> agregarEquipo(@RequestBody Equipo equipo){
        //con RequestBody: convierte el json que recibe en un objeto Equipo automaticamente.

        //Ingreso equipo en BD
        this.equipoDao.agregar(equipo);

        //Acá tiene que imprimir:
        this.ticket.imprimirTicket(equipo);


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
    public void editarEquipo(@PathVariable("id") int id, @RequestBody Equipo equipo){
        this.equipoDao.editarEquipo(equipo);
    }

    @RequestMapping(value = "vaciar/equipos", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaEquipos(){
        this.equipoDao.vaciarEquipos();
        return new ResponseEntity<String>("Se vació el listado de equipos correctamente", HttpStatus.OK);
    }

    @RequestMapping(value = "descargarListado", method = RequestMethod.GET)
    public ResponseEntity<Resource> imprimirInfo() throws IOException {

        List<Equipo> losEquipos =  equipoDao.getEquipos(); //array de array[10], array[10], array[10]



        int cantidadEquipos = losEquipos.size();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withDelimiter(';'));

            long milisegundos = System.currentTimeMillis();


            for (Equipo equipo : losEquipos) {
                /*String tipo = equipo.getTipo_equipo();
                String marca = equipo.getMarca();
                String serie = equipo.getSerie();
                String modelo = equipo.getModelo();
                String tms = tipo +" "+ marca +" "+ serie;
                String servicio = equipo.getServicio();
                String cliente = equipo.getCliente();
                String ubicacion = equipo.getUbicacion();
                String propiedad = equipo.getPropiedad();

                csvPrinter.printRecord(milisegundos, tms,tipo,marca,serie,
                        modelo, servicio,"","","","", cliente, ubicacion, "", propiedad, "");*/


                //Nuevos datos
                String idTipoEquipo = equipo.getTipoequipoide();
                String idMarca = equipo.getMarcaide();
                String idModelo = equipo.getModeloide();
                String serie = equipo.getSerie();
                String idServicio = equipo.getServicioide();
                String idCliente = equipo.getClienteide();
                String certificable = "NO"; //Por defecto NO, a pedido de Daniel.
                String certificable_e = "NO"; //Por defecto NO, a pedido de Daniel.
                String idPropiedad = equipo.getPropiedadide(); //PERTENENCIA -> daniel
                String idEstado = equipo.getEstadoide();
                String mantenible = "NO"; //Por defecto NO, a pedido de Daniel.
                String ubicacion = equipo.getUbicacion();


                //nuevo listado:
                // Id_tipo_de_equipo, id_fabricante, id_modelo, serial, id_servicio, id_cliente,
                // certificable, certificable_e, id_pertenencia, id_estado, mantenible

                csvPrinter.printRecord(idTipoEquipo, idMarca, idModelo,serie,idServicio,idCliente,
                        certificable,certificable_e,idPropiedad,idEstado, mantenible,ubicacion);
            }


        csvPrinter.flush();

        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

        //Nombre de archivo a descargar:
        String institucion = "";
        String nombreArchivo ="";


        if (cantidadEquipos > 0){
            institucion = losEquipos.get(0).getCliente();
            nombreArchivo = institucion  + ".csv"; //Sin fecha para no confundir.

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