/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.DAO;

import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Inmueble;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IInmuebleDAO {
    
    List<Inmueble>GetAll();
    int Add(Inmueble inmueble);
    
}
