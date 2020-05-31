/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pirinola.modelo;

/**
 *
 * @author JULIMEL
 */
public class DatoPirinola {
    
    private String texto;
    private int cantidad;

    public DatoPirinola(String texto, int cantidad) {
        this.texto = texto;
        this.cantidad = cantidad;
    }

    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
