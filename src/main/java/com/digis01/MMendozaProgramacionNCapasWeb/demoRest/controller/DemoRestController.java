/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.demoRest.controller;

import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.NumerosOperacion;
import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.Resultado;
import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.service.DemoServiceImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/demoApi")
public class DemoRestController {
    @PostMapping("/suma")
    public Resultado suma(@RequestBody NumerosOperacion numeros){
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Suma(numeros); 
    }
}
