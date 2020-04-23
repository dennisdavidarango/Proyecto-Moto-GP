/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;

/**
 *
 * @author carloaiza
 */
public class Nodo implements Serializable{
    private Corredor dato;
    private Nodo siguiente;

    public Nodo(Corredor dato) {
        this.dato = dato;
    }

    public Corredor getDato() {
        return dato;
    }

    public void setDato(Corredor dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    
}