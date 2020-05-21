/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author carloaiza
 */
@Named(value = "appBean")
@ApplicationScoped
public class AppBean {
    
    private String correoTurno="carloaiza@umanizales.edu.co";
    
    private int cont=0;
    /**
     * Creates a new instance of AppBean
     */
    public AppBean() {
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getCorreoTurno() {
        return correoTurno;
    }

    public void setCorreoTurno(String correoTurno) {
        this.correoTurno = correoTurno;
    }
    
    
    
    
    public void aumentarContador(String correo)
    {
        switch(correo)
        {
            case "carloaiza@umanizales.edu.co":
                correoTurno= "consulta@umanizales.edu.co";
                break;
            default:
                correoTurno= "carloaiza@umanizales.edu.co";
        }
        
        cont++;
    }
    
    public boolean validarTurno(String correo)
    {
        if(correo.equals(correoTurno))
        {
            return true;
        }
        return false;
    }
    
    
}
