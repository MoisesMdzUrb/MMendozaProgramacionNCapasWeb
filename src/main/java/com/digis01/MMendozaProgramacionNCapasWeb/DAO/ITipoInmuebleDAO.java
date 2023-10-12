/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.TipoInmueble;
import java.util.List;

/**
 *
 * @author digis
 */
public interface ITipoInmuebleDAO {
    List<TipoInmueble>GetAll();
    
    int Add(TipoInmueble tipoInmueble);
    TipoInmueble GetById(int idTipoInmuebleEditable);
    void update(TipoInmueble tipoInmueble);
    
}
