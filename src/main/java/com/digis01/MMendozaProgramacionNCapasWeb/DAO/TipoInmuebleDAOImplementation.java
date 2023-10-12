/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.TipoInmueble;
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
public class TipoInmuebleDAOImplementation implements ITipoInmuebleDAO {

    private EntityManager entityManager;

    @Autowired
    public TipoInmuebleDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TipoInmueble> GetAll() {
        TypedQuery<TipoInmueble> query = entityManager.createQuery("From TipoInmueble", TipoInmueble.class);
        List<TipoInmueble> tipoinmuebles = query.getResultList();
        return tipoinmuebles;
    }

    @Transactional
    @Override
    public int Add(TipoInmueble tipoInmueble) {
        entityManager.persist(tipoInmueble);
        return tipoInmueble.getIdtipoinmueble();
    }

    @Override
    public TipoInmueble GetById(int idTipoInmuebleEditable) {
        TypedQuery<TipoInmueble> query = entityManager.createQuery("From TipoInmueble WHERE idtipoinmueble=:idTipoInmuebleEditable", TipoInmueble.class);
        query.setParameter("idTipoInmuebleEditable", idTipoInmuebleEditable);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void update(TipoInmueble tipoInmueble) {
        entityManager.merge(tipoInmueble);
    }

    @Transactional
    public void Delete(int idtipoinmueble) {
        TipoInmueble tipoInmueble = entityManager.find(TipoInmueble.class, idtipoinmueble);
        if (tipoInmueble != null) {
            entityManager.remove(tipoInmueble);
        }
    }
}
