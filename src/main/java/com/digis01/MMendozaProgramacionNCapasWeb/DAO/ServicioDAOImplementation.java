/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Servicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class ServicioDAOImplementation implements IServicioDAO {

    private EntityManager entityManager;

    @Autowired
    public ServicioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Servicio> GetAll() {
        TypedQuery<Servicio> query = entityManager.createQuery("From Servicio", Servicio.class);
        List<Servicio> servicios = query.getResultList();
        return servicios;
    }

    @Transactional
    @Override
    public int Add(Servicio servicio) {
        entityManager.persist(servicio);
        return servicio.getIdservicio();
    }

    @Override
    public Servicio GetById(int idservicioeditable) {
        TypedQuery<Servicio> query = entityManager.createQuery("FROM Servicio WHERE idservicio=:idservicioeditable", Servicio.class);
        query.setParameter("idservicioeditable", idservicioeditable);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void Update(Servicio servicio) {
        entityManager.merge(servicio);
    }

    @Transactional
    public void Delete(int idservicio) {
        Servicio servicio = entityManager.find(Servicio.class, idservicio);
        if (servicio != null) {
            entityManager.remove(servicio);
        }
    }
}
