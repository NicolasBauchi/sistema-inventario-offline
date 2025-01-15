package com.nicolas.api.dao;

import com.nicolas.api.models.Equipo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class EquipoDaoImpl implements EquipoDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Equipo> getEquipos() {

       /** String queryN = "SELECT * FROM equipos";
        return (List<Equipo>) entityManager.createNativeQuery(queryN).getResultList();*/

        TypedQuery<Equipo> query = entityManager.createQuery("SELECT e FROM Equipo e", Equipo.class);
         return query.getResultList();

       /**String query = "FROM Equipo";
        return (List) this.entityManager.createQuery(query).getResultList();*/
    }

    @Override
    public Equipo getEquipo(int id) {
        String query = "FROM Equipo WHERE id='" + id+"'";
        return (Equipo) this.entityManager.createQuery(query).getResultList();
    }

    //Editar equipo
    @Override
    public void editarEquipo(Equipo equipo) {
        //no se . buscar como editar
    }

    @Override
    public void eliminar(int id) {
        Equipo equipo = (Equipo) this.entityManager.find(Equipo.class, id);
        this.entityManager.remove(equipo);
    }

    @Override
    public void agregar(Equipo equipo) {
        this.entityManager.persist(equipo);
    }

    @Override
    @Transactional
    public void vaciarEquipos() {
        //Esto es lenguaje SQL puro.
        String queryN = "TRUNCATE TABLE equipos";
        this.entityManager.createNativeQuery(queryN).executeUpdate();
    }




}
