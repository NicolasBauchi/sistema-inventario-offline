package com.nicolas.api.controllers;

import com.nicolas.api.dao.ListasDao;
import com.nicolas.api.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ListasControler {

    @Autowired
    private ListasDao listasDao;

    /* Subiendo las tablas a la BD: **/
    @RequestMapping(value = "subirTablas/clientes", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaCliente(@RequestBody Map<String, Object> listaClientes){
        List<String> clientes = (List<String>) listaClientes.get("datos");

        int cantClientes = clientes.size();

        for (int i = 0; i < cantClientes; i++) {
            Cliente c = new Cliente();
            c.setNombre(clientes.get(i));
            listasDao.agregarCliente(c);
        }

        return new ResponseEntity<String>("Los " + cantClientes +" Clientes fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/marcas", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaMarca(@RequestBody Map<String, Object> listaMarcas){

        List<String> marcas = (List<String>) listaMarcas.get("datos");

        int cantMarcas = marcas.size();

        for (int i = 0; i < cantMarcas; i++) {
            Marca m = new Marca();
            m.setNombre(marcas.get(i));
            listasDao.agregarMarca(m);
        }

        return new ResponseEntity<String>("Las " + cantMarcas +" Marcas fueron almacenadas correctamente", HttpStatus.OK);
    }

    @RequestMapping(value = "subirTablas/servicios", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaServicio(@RequestBody Map<String, Object> listaServicios){


        List<String> servicios = (List<String>) listaServicios.get("datos");

        int cantServicios = servicios.size();

        for (int i = 0; i < cantServicios; i++) {
            Servicios s = new Servicios();
            s.setNombre(servicios.get(i));
            listasDao.agregarServicios(s);
        }

        return new ResponseEntity<String>("Los " + cantServicios +" Servicios fueron almacenados correctamente", HttpStatus.OK);

    }
    @RequestMapping(value = "subirTablas/tipoEquipos", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaTipoEquipo(@RequestBody Map<String, Object> listaTipoEquipos){

        List<String> tipoEquipos = (List<String>) listaTipoEquipos.get("datos");

        int cantTipoEquipos = tipoEquipos.size();

        for (int i = 0; i < cantTipoEquipos; i++) {
            TipoEquipos te = new TipoEquipos();
            te.setNombre(tipoEquipos.get(i));
            listasDao.agregarTipoEquipos(te);
        }

        return new ResponseEntity<String>("Los " + cantTipoEquipos +" Tipo de equipos fueron almacenados correctamente", HttpStatus.OK);

    }

    //Mostrando las tablas:

    @RequestMapping(value = "clientes", method = RequestMethod.GET)
    public ArrayList<Cliente> getTablaCliente(){
       return listasDao.nombresClientes();
    }
    @RequestMapping(value = "marcas", method = RequestMethod.GET)
    public ArrayList<Marca> getTablaMarca(){
        return listasDao.nombresMarcas();
    }

    @RequestMapping(value = "servicios", method = RequestMethod.GET)
    public ArrayList<Servicios> getTablaServicios(){
        return listasDao.nombresServicios();

    }
    @RequestMapping(value = "tipoEquipos", method = RequestMethod.GET)
    public ArrayList<TipoEquipos> getTablaTipoEquipos(){
        return listasDao.nombresTipoEquipos();
    }

    //Vaciar Tablas:

    @RequestMapping(value = "vaciar/clientes", method = RequestMethod.DELETE)
    public void vaciarTablaClientes(){
       listasDao.truncarTablaCliente();
    }

    @RequestMapping(value = "vaciar/marcas", method = RequestMethod.DELETE)
    public void vaciarTablaMarcas(){
        listasDao.truncarTablaMarca();
    }

    @RequestMapping(value = "vaciar/servicios", method = RequestMethod.DELETE)
    public void vaciarTablaServicios(){
        listasDao.truncarTablaServicio();
    }

    @RequestMapping(value = "vaciar/tipo-equipos", method = RequestMethod.DELETE)
    public void vaciarTablaTipoEquipos(){
        listasDao.truncarTablaTipo();
    }
}
