/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.Controller;

import com.digis01.MMendozaProgramacionNCapasWeb.DAO.TipoInmuebleDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.TipoInmueble;
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
@RequestMapping("/tipoinmueble")
public class TipoInmuebleController {

    private TipoInmuebleDAOImplementation tipoInmuebleDAOImplementation;

    @Autowired
    public TipoInmuebleController(TipoInmuebleDAOImplementation tipoInmuebleDAOImplementation) {
        this.tipoInmuebleDAOImplementation = tipoInmuebleDAOImplementation;
    }

    @GetMapping("/listado")
    private String ListadoTipoInmuebles(Model model) {
        List<TipoInmueble> tipoinmuebles = tipoInmuebleDAOImplementation.GetAll();
        model.addAttribute("tipoinmuebles", tipoinmuebles);
        return "listadoTipoInmueble";
    }

    @GetMapping("/form/{idtipoinmueble}")
    public String Form(@PathVariable int idtipoinmueble, Model model) {
        if (idtipoinmueble == 0) {
            model.addAttribute("tipoinmueble", new TipoInmueble());
            return "formTipoInmuebleEditable";
        } else {
            TipoInmueble tipoInmueble = tipoInmuebleDAOImplementation.GetById(idtipoinmueble);
            model.addAttribute("tipoinmueble", tipoInmueble);
            return "formTipoInmuebleEditable";
        }
    }

    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute("tipoinmueble") TipoInmueble tipoInmueble, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formTipoInmuebleEditable";
        }
        int idtipoinmueble = tipoInmuebleDAOImplementation.Add(tipoInmueble);
        if (idtipoinmueble > 0) {
            return "redirect:/tipoinmueble/listado";
        } else {
            model.addAttribute("error", "no se agrego el inmueble");
            return "formTipoInmuebleEditable";
        }
    }

    @GetMapping("/delete/{idtipoinmueble}")
    public String Delete(@PathVariable("idtipoinmueble") int idtipoinmueble, Model model) {
        tipoInmuebleDAOImplementation.Delete(idtipoinmueble);
        return "redirect:/tipoinmueble/listado";
    }
}
