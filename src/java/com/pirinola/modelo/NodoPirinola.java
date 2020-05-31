/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pirinola.modelo;

import com.listaenlazada.modelo.Corredores;

/**
 *
 * @author JULIMEL
 */
public class NodoPirinola {
    
    private DatoPirinola dato;
    private NodoPirinola siguiente;
    private NodoPirinola anterior;

    public NodoPirinola(DatoPirinola dato, NodoPirinola siguiente, NodoPirinola anterior) {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    NodoPirinola(Corredores corredor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
