/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.demoRest.service;

import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.NumerosOperacion;
import com.digis01.MMendozaProgramacionNCapasWeb.demoRest.entity.Resultado;

/**
 *
 * @author digis
 */
public class DemoServiceImplementation implements IDemoServicio {

    @Override
    public String Saludo(String nombre) {
        return "Hola, " + nombre;
    }

    @Override
    public Resultado Suma(NumerosOperacion numerosOperacion) {
        return new Resultado(numerosOperacion.getNumero1() + numerosOperacion.getNumero2());
    }

}
