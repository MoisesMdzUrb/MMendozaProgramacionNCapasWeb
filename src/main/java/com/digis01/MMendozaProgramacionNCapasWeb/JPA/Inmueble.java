/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author digis
 */
@Entity
@Table(name="INMUEBLE")
public class Inmueble implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idinmueble;
    @Basic
    private String nombre;
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="idtipoinmueble")
    private TipoInmueble tipoInmueble;
    @JoinColumn(name="")
    private Moneda moneda;
    
}
