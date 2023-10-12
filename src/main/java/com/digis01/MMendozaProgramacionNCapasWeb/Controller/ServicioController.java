/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.Controller;

import com.digis01.MMendozaProgramacionNCapasWeb.DAO.ServicioDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Servicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/servicio")
public class ServicioController {

    private ServicioDAOImplementation servicioDAOImplementation;

    @Autowired
    public ServicioController(ServicioDAOImplementation servicioDAOImplementation) {
        this.servicioDAOImplementation = servicioDAOImplementation;
    }

    @GetMapping("/listado")
    private String ListadoServicio(Model model) {
        List<Servicio> servicios = servicioDAOImplementation.GetAll();
        model.addAttribute("servicios", servicios);
        return "listadoservicios";
    }

    @GetMapping("/form/{idservicio}")
    public String Form(@PathVariable int idservicio, Model model) {
        if (idservicio == 0) {
            model.addAttribute("servicio", new Servicio());
            return "formServicioEditable";
        } else {
            Servicio servicio = servicioDAOImplementation.GetById(idservicio);
            model.addAttribute("servicio", servicio);
            return "formServicioEditable";
        }
    }

    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute("servicio") Servicio servicio, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formServicioEditable";
        }
        int idservicio = servicioDAOImplementation.Add(servicio);
        if (idservicio > 0) {
            return "redirect:/servicio/listado";
        } else {
            model.addAttribute("error", "no se agrego el servicio");
            return "formServicioEditable";
        }
    }

    @GetMapping("/delete/{idservicio}")
    public String Delete(@PathVariable("idservicio") int idservicio, Model model) {
        servicioDAOImplementation.Delete(idservicio);
        return "redirect:/servicio/listado";
    }
}
