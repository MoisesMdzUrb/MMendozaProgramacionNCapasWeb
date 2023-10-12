/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.demoRest.service;

import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.NumerosOperacion;
import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.Resultado;

/**
 *
 * @author digis
 */
public interface IDemoServicio {
    
    String Saludo(String nombre);
    Resultado Suma(NumerosOperacion numerosOperacion);
}
