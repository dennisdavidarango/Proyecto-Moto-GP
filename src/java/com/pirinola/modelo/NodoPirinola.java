/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pirinola.modelo;

import com.listaenlazada.modelo.Corredores;
import java.io.Serializable;

/**
 *
 * @author JULIMEL
 */
public class NodoPirinola implements Serializable {
    
    private Jugador dato;
    private NodoPirinola siguiente;
    private NodoPirinola anterior;

    public NodoPirinola(Jugador dato) {
        this.dato = dato;
    }

    public Jugador getDato() {
        return dato;
    }

    public void setDato(Jugador dato) {
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
