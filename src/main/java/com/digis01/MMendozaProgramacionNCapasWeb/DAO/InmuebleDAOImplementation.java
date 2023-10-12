/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Inmueble;
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
public class InmuebleDAOImplementation implements IInmuebleDAO {

    private EntityManager entityManager;

    @Autowired
    public InmuebleDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Inmueble> GetAll() {
       TypedQuery <Inmueble> query = entityManager.createQuery("FROM Inmueble",Inmueble.class);
       List<Inmueble>inmuebles = query.getResultList();
       return inmuebles;
    }
//    @Transactional
//    @Override
//    public int Add(Inmueble inmueble) {
//        entityManager.persist(inmueble);
//        return "";
//    }

    @Override
    public int Add(Inmueble inmueble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
