/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Usuario;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IUsuarioDAO {
    List<Usuario> GetAll(Usuario usuario);

    int Add(Usuario usuario);

    Usuario GetById(int idusuarioeditable);

    void Update(Usuario usuario);
    
    void ChangeStatus(int idUsuario,boolean status);

}
