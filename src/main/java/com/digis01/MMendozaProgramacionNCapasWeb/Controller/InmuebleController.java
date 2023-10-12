/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.Controller;

import com.digis01.MMendozaProgramacionNCapasWeb.DAO.InmuebleDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Inmueble;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/inmueble")
public class InmuebleController {
    private InmuebleDAOImplementation inmuebleDAOImplementation;
    
    @Autowired
    public InmuebleController(InmuebleDAOImplementation inmuebleDAOImplementation){
        this.inmuebleDAOImplementation = inmuebleDAOImplementation;
    }
    @GetMapping("/listado")
    private String ListadoInmueble(Model model){
        List<Inmueble>inmuebles = inmuebleDAOImplementation.GetAll();
        model.addAttribute("inmuebles", inmuebles);
        return "listadoInmueble";
    }
}
