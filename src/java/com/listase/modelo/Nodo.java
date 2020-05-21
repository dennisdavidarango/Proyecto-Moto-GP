/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listaenlazada.modelo.Corredores;
import static com.listaenlazada.modelo.Corredores_.genero;
import java.io.Serializable;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author JULIMEL
 */
public class Nodo implements Serializable{
    private Corredores dato;
    private Nodo siguiente;

    public Nodo(Corredores dato) {
        this.dato = dato;
    }

    public Corredores getDato() {
        return dato;
    }

    public void setDato(Corredores dato) {
        this.dato = dato;
    }
   

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
