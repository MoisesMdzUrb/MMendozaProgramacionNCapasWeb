/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Rol;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    private EntityManager entityManager;

    @Autowired
    public UsuarioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Usuario> GetAll(Usuario usuario) {
        TypedQuery<Usuario> query = entityManager.createQuery("From Usuario WHERE "
                + "LOWER(nombre) LIKE LOWER(: nombreusuario) AND + "
                + "LOWER (apellidopaterno) LIKE LOWER(: paterno) AND + "
                + "LOWER (apellidomaterno) LIKE LOWER(: materno)", Usuario.class);
        query.setParameter("nombreusuario", '%' + usuario.getNombre() + '%');
        query.setParameter("paterno", '%' + usuario.getApellidopaterno() + '%');
        query.setParameter("materno", '%' + usuario.getApellidomaterno() + '%');
        List<Usuario> usuarios = query.getResultList();

        return usuarios;
    }

    @Override
    @Transactional
    public int Add(Usuario usuario) {
        //usuario.setFechadenacimiento(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//        Rol rol = new Rol();
//        rol.setIdrol(1);
//        usuario.setRol(rol);
        entityManager.persist(usuario);
        return usuario.getIdusuario();
    }

    @Override
    public Usuario GetById(int idusuarioeditable) {

        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE idusuario =: idusuarioeditable", Usuario.class);
        query.setParameter("idusuarioeditable", idusuarioeditable);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void Update(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    @Transactional
    public void ChangeStatus(int idUsuario,boolean status) {
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);

        String statusString = (status)? "Y" : "N";
        usuario.setStatus(statusString);
        entityManager.merge(usuario);
    }

    @Transactional
    public void Delete(int idusuario) {
        Usuario usuario = entityManager.find(Usuario.class, idusuario);
        if (usuario !=null) {
            entityManager.remove(usuario);
        }
    }

}
