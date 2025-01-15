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

        //Limpio tabla antes de empezar a cargar
        listasDao.truncarTablaCliente();
        //Se hace este "arreglo" para recibir 2 datos en cliente Lineas referencia: 33 y 36.
        //Se reciben 2 datos escritos en el mismo String y los separo usando referencia idCliente.

            List<String> clientes = (List<String>) listaClientes.get("datos");

            int cantClientes = clientes.size();

            for (int i = 0; i < cantClientes; i++) {
                Cliente c = new Cliente();
               // String nombre = clientes.get(i).getNombre().split("idCliente")[0];
                String nombre = clientes.get(i).split("separador")[0].trim();
                c.setNombre(nombre);

                //String idCliente = clientes.get(i).getNombre().split("idCliente")[1].trim();
                String idCliente = clientes.get(i).split("separador")[1].trim();
                c.setIdentificliente(idCliente);

                //Método para guardar Cliente en BD
                listasDao.agregarCliente(c);
            }


        return new ResponseEntity<String>("", HttpStatus.OK);

        //return new ResponseEntity<String>("Los " + cantClientes +" Clientes fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/marcas", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaMarca(@RequestBody Map<String, Object> listaMarcas){
        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaMarca();

        List<String> marcas = (List<String>) listaMarcas.get("datos");

        int cantMarcas = marcas.size();

        /**for (int i = 0; i < cantMarcas; i++) {
            Marca m = new Marca();
            m.setNombre(marcas.get(i));
            //Método para guardar Marca en BD
            listasDao.agregarMarca(m);
        }*/

        for (int i = 0; i < cantMarcas; i++) {
            Marca m = new Marca();
            String nombre = marcas.get(i).split("separador")[0].trim();
            m.setNombre(nombre);

            String idMarcas = marcas.get(i).split("separador")[1].trim();
            m.setIdeMarcas(idMarcas);

            //Método para guardar Marca en BD
            listasDao.agregarMarca(m);
        }

        return new ResponseEntity<String>("Las " + cantMarcas +" Marcas fueron almacenadas correctamente", HttpStatus.OK);
    }

    @RequestMapping(value = "subirTablas/servicios", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaServicio(@RequestBody Map<String, Object> listaServicios){

        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaServicio();

        List<String> servicios = (List<String>) listaServicios.get("datos");

        int cantServicios = servicios.size();

        /**for (int i = 0; i < cantServicios; i++) {
            Servicios s = new Servicios();
            s.setNombre(servicios.get(i));

            //Método para guardar Servicios en BD
            listasDao.agregarServicios(s);
        }*/

        for (int i = 0; i < cantServicios; i++) {
            Servicios s = new Servicios();
            String nombre = servicios.get(i).split("separador")[0].trim();
            s.setNombre(nombre);

            String idServicios = servicios.get(i).split("separador")[1].trim();
            s.setIdeServicios(idServicios);

            //Método para guardar Servicio en BD
            listasDao.agregarServicios(s);
        }

        return new ResponseEntity<String>("Los " + cantServicios +" Servicios fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/tipoEquipos", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaTipoEquipo(@RequestBody Map<String, Object> listaTipoEquipos){

        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaTipo();

        List<String> tipoEquipos = (List<String>) listaTipoEquipos.get("datos");

        int cantTipoEquipos = tipoEquipos.size();

        /**for (int i = 0; i < cantTipoEquipos; i++) {
            TipoEquipos te = new TipoEquipos();
            te.setNombre(tipoEquipos.get(i));

            //Método para guardar tipo de equipos en BD
            listasDao.agregarTipoEquipos(te);
        }*/

        for (int i = 0; i < cantTipoEquipos; i++) {
            TipoEquipos te = new TipoEquipos();
            String nombre = tipoEquipos.get(i).split("separador")[0].trim();
            te.setNombre(nombre);

            String idTipoequipos = tipoEquipos.get(i).split("separador")[1].trim();
            te.setIdeTipos(idTipoequipos);

            //Método para guardar Tipo de equipos en BD
            listasDao.agregarTipoEquipos(te);
        }

        return new ResponseEntity<String>("Los " + cantTipoEquipos +" Tipo de equipos fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/modelos", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaModelo(@RequestBody Map<String, Object> listaModelos){

        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaModelos();

        List<String> modelos = (List<String>) listaModelos.get("datos");

        int cantModelos = modelos.size();

        for (int i = 0; i < cantModelos; i++) {
            Modelo m = new Modelo();
            String nombre = modelos.get(i).split("separador")[0].trim();
            m.setNombre(nombre);

            String idModelos = modelos.get(i).split("separador")[1].trim();
            m.setIdeModelos(idModelos);

            //Método para guardar modelos en BD
            listasDao.agregarModelos(m);
        }

        return new ResponseEntity<String>("Los " + cantModelos +" Modelos fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/estados", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaEstado(@RequestBody Map<String, Object> listaEstados){

        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaEstados();

        List<String> estados = (List<String>) listaEstados.get("datos");

        int cantEstados = estados.size();

        for (int i = 0; i < cantEstados; i++) {
            Estado e = new Estado();
            String nombre = estados.get(i).split("separador")[0].trim();
            e.setNombre(nombre);

            String idEstados = estados.get(i).split("separador")[1].trim();
            e.setIdeEstado(idEstados);

            //Método para guardar modelos en BD
            listasDao.agregarEstado(e);
        }

        return new ResponseEntity<String>("Los " + cantEstados +" Estados fueron almacenados correctamente", HttpStatus.OK);

    }

    @RequestMapping(value = "subirTablas/propiedades", method = RequestMethod.POST)
    public ResponseEntity<String> subirTablaPropiedad(@RequestBody Map<String, Object> listaPropiedades){

        //Limpio tabla antes de empezar a cargar.
        listasDao.truncarTablaPropiedad();

        List<String> propiedades = (List<String>) listaPropiedades.get("datos");

        int cantPropiedades = propiedades.size();

        for (int i = 0; i < cantPropiedades; i++) {
            Propiedad p = new Propiedad();
            String nombre = propiedades.get(i).split("separador")[0].trim();
            p.setNombre(nombre);

            String idPropiedades = propiedades.get(i).split("separador")[1].trim();
            p.setIdePropiedad(idPropiedades);

            //Método para guardar modelos en BD
            listasDao.agregarPropiedad(p);
        }

        return new ResponseEntity<String>("Los " + cantPropiedades +" Propiedades fueron almacenados correctamente", HttpStatus.OK);

    }

    // <------------------------- Mostrando las tablas: --------------------------->

    @RequestMapping(value = "clientes", method = RequestMethod.GET)
    public List<Cliente> getTablaCliente(){
       return listasDao.nombresClientes();
    }
    @RequestMapping(value = "marcas", method = RequestMethod.GET)
    public List<Marca> getTablaMarca(){
        return listasDao.nombresMarcas();
    }
    @RequestMapping(value = "servicios", method = RequestMethod.GET)
    public List<Servicios> getTablaServicios(){
        return listasDao.nombresServicios();
    }
    @RequestMapping(value = "tipoEquipos", method = RequestMethod.GET)
    public List<TipoEquipos> getTablaTipoEquipos(){
        return listasDao.nombresTipoEquipos();
    }
    @RequestMapping(value = "modelos", method = RequestMethod.GET)
    public List<Modelo> getTablaModelos(){
        return listasDao.nombresModelos();
    }
    @RequestMapping(value = "estados", method = RequestMethod.GET)
    public List<Estado> getTablaEstados(){return listasDao.nombresEstados();}
    @RequestMapping(value = "propiedades", method = RequestMethod.GET)
    public List<Propiedad> getTablaPropiedades(){
        return listasDao.nombresPropiedades();
    }

    //Vaciar Tablas:

    @RequestMapping(value = "vaciar/clientes", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaClientes(){
       listasDao.truncarTablaCliente();

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/marcas", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaMarcas(){
        listasDao.truncarTablaMarca();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/servicios", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaServicios(){
        listasDao.truncarTablaServicio();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/tipo-equipos", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaTipoEquipos(){
        listasDao.truncarTablaTipo();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/modelos", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaModelos(){
        listasDao.truncarTablaModelos();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/estados", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaEstados(){
        listasDao.truncarTablaEstados();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "vaciar/propiedades", method = RequestMethod.DELETE)
    public ResponseEntity<String> vaciarTablaPropiedades(){
        listasDao.truncarTablaPropiedad();
        return new ResponseEntity<String>("", HttpStatus.OK);
    }


}
