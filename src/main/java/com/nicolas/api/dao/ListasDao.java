package com.nicolas.api.dao;

import com.nicolas.api.models.*;

import java.util.List;

public interface ListasDao {

    //Tabla Clientes
    public List<Cliente> nombresClientes(); //listado
    public Cliente getCliente(int id);
    public void agregarCliente(Cliente cliente);
    public void truncarTablaCliente() ;


    //Tabla Marcas
    public List<Marca> nombresMarcas(); //listado
    public Marca getMarca(int id);
    public void agregarMarca(Marca marca);
    public void truncarTablaMarca() ;


    //Tabla Servicios
    public List<Servicios> nombresServicios(); //listado
    public Servicios getServicios(int id);
    public void agregarServicios(Servicios servicios);
    public void truncarTablaServicio() ;


    //Tabla Tipos de equipos
    public List<TipoEquipos> nombresTipoEquipos(); //listado
    public TipoEquipos getTipoEquipos(int id);
    public void agregarTipoEquipos(TipoEquipos tipoEquipos);
    public void truncarTablaTipo() ;


    //Tabla Modelos
    public List<Modelo> nombresModelos(); //listado
    public Modelo getModelos(int id);
    public void agregarModelos(Modelo modelos);
    public void truncarTablaModelos() ;

    //Tabla Estado
    public List<Estado> nombresEstados(); //listado
    public Estado getEstados(int id);
    public void agregarEstado(Estado estados);
    public void truncarTablaEstados() ;

    //Tabla Propiedad
    public List<Propiedad> nombresPropiedades(); //listado
    public Propiedad getPropiedad(int id);
    public void agregarPropiedad(Propiedad propiedad);
    public void truncarTablaPropiedad() ;



}
