package com.nicolas.api.dao;

import com.nicolas.api.models.Equipo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository
@Transactional
public class EquipoDaoImpl implements EquipoDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Equipo> getEquipos() {
       String query = "FROM Equipo";
       return (ArrayList<Equipo>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Equipo getEquipo(int id) {
        String query = "FROM Equipo WHERE id='" + id+"'";
        return (Equipo) entityManager.createQuery(query).getResultList();
    }

    //Editar equipo
    @Override
    public void editarEquipo(Equipo equipo) {
        //no se . buscar como editar
    }

    @Override
    public void eliminar(int id) {
        Equipo equipo = entityManager.find(Equipo.class, id);
        entityManager.remove(equipo);
    }

    @Override
    public void agregar(Equipo equipo) {
        entityManager.merge(equipo);
    }

    @Override
    @Transactional
    public void vaciarEquipos() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE equipos";
        entityManager.createNativeQuery(queryN).executeUpdate();
    }




}
