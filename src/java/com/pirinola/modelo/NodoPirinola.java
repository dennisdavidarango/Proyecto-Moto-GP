/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pirinola.modelo;

import java.io.Serializable;

/**
 *
 * @author JULIMEL
 */
public class NodoPirinola implements Serializable {
    
    private DatoPirinola dato;
    private NodoPirinola siguiente;
    private NodoPirinola anterior;

    public NodoPirinola(DatoPirinola dato) {
        this.dato = dato;
    }

    public DatoPirinola getDato() {
        return dato;
    }

    public void setDato(DatoPirinola dato) {
        this.dato = dato;
    }

    public NodoPirinola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPirinola siguiente) {
        this.siguiente = siguiente;
    }

    public NodoPirinola getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoPirinola anterior) {
        this.anterior = anterior;
    }
    
    
}
