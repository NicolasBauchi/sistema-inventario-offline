package com.nicolas.api.dao;

import com.nicolas.api.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ListasDaoImpl implements ListasDao{

    @PersistenceContext
    private EntityManager entityManager;

    //Tabla clientes

    @Override
    public List<Cliente> nombresClientes() {
        //Esto es lenguaje SQL puro.
        // solo un array de arrays.
        String queryN = "SELECT * FROM clientes ORDER BY nombre_cliente";
        return (List<Cliente>) entityManager.createNativeQuery(queryN).getResultList();


        //String query = "FROM Cliente";
        //return (ArrayList<Cliente>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Cliente getCliente(int id) {
        String query = "FROM Cliente WHERE id='" + id+"'";
        return (Cliente) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        entityManager.merge(cliente);
    }


    @Override
    @Transactional
    public void truncarTablaCliente() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE clientes";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }


    //Tabla marcas

    @Override
    public List<Marca> nombresMarcas() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM marcas ORDER BY nombre_marcas";
        return (List<Marca>) entityManager.createNativeQuery(queryN).getResultList();


        //String query = "FROM Marca";
        //return (ArrayList<Marca>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Marca getMarca(int id) {
        String query = "FROM Marca WHERE id='" + id+"'";
        return (Marca) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarMarca(Marca marca) {
        entityManager.merge(marca);
    }

    @Override
    @Transactional
    public void truncarTablaMarca() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE marcas";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }


    //Tabla servicios

    @Override
    public List<Servicios> nombresServicios() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM servicios ORDER BY nombre_servicios";
        return (List<Servicios>) entityManager.createNativeQuery(queryN).getResultList();

       // String query = "FROM Servicios";
       // return (ArrayList<Servicios>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Servicios getServicios(int id) {
        String query = "FROM Servicios WHERE id='" + id+"'";
        return (Servicios) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarServicios(Servicios servicios) {
        entityManager.merge(servicios);
    }

    @Override
    @Transactional
    public void truncarTablaServicio() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE servicios";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }


    //Tabla tipo equipos

    @Override
    public List<TipoEquipos> nombresTipoEquipos() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM tipoequipos ORDER BY nombre_tipos";
        return (List<TipoEquipos>) entityManager.createNativeQuery(queryN).getResultList();

        //String query = "FROM TipoEquipos";
        //return (ArrayList<TipoEquipos>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public TipoEquipos getTipoEquipos(int id) {
        String query = "FROM TipoEquipos WHERE id='" + id + "'";
        return (TipoEquipos) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarTipoEquipos(TipoEquipos tipoEquipos) {
        entityManager.persist(tipoEquipos);
    }

    @Override
    @Transactional
    public void truncarTablaTipo() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE tipoequipos";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }

    //Tabla MODELOS

    @Override
    public List<Modelo> nombresModelos() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM modelos ORDER BY nombre_modelos";
        return (List<Modelo>) entityManager.createNativeQuery(queryN).getResultList();
    }

    @Override
    public Modelo getModelos(int id) {
        String query = "FROM modelos WHERE id='" + id + "'";
        return (Modelo) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarModelos(Modelo modelos) {
        entityManager.merge(modelos);
    }

    @Override
    public void truncarTablaModelos() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE modelos";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }

    //Tabla ESTADOS

    @Override
    public List<Estado> nombresEstados() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM estados ORDER BY nombre_estado";
        return (List<Estado>) entityManager.createNativeQuery(queryN).getResultList();
    }

    @Override
    public Estado getEstados(int id) {
        String query = "FROM estados WHERE id='" + id + "'";
        return (Estado) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarEstado(Estado estados) {
        entityManager.merge(estados);
    }

    @Override
    public void truncarTablaEstados() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE estados";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }

    //Tabla PROPIEDADES

    @Override
    public List<Propiedad> nombresPropiedades() {
        //Esto es lenguaje SQL puro.
        String queryN = "SELECT * FROM propiedades ORDER BY nombre_propiedad";
        return (List<Propiedad>) entityManager.createNativeQuery(queryN).getResultList();
    }

    @Override
    public Propiedad getPropiedad(int id) {
        String query = "FROM propiedades WHERE id='" + id + "'";
        return (Propiedad) entityManager.createQuery(query).getResultList();
    }

    @Override
    public void agregarPropiedad(Propiedad propiedad) {
        entityManager.merge(propiedad);
    }

    @Override
    public void truncarTablaPropiedad() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE propiedades";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }


}