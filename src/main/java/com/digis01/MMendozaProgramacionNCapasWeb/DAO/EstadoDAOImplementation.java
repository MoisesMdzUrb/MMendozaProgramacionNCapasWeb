/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class EstadoDAOImplementation implements IEstadoDAO {

    private EntityManager entityManager;

    @Autowired
    public EstadoDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Estado> GetAll() {
        TypedQuery<Estado> query = entityManager.createQuery("FROM Estado", Estado.class);
        List<Estado> direcciones = query.getResultList();

        return direcciones;
    }

    @Override
    public List<Estado> GetByIdPais(int IdPais) {
        TypedQuery<Estado> query = entityManager.createQuery("FROM Estado WHERE pais.idpais = :id", Estado.class);
        query.setParameter("id", IdPais);

        List<Estado> direcciones = query.getResultList();

        return direcciones;
    }

}
