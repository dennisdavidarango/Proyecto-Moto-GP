/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import com.listacircularde.modelo.ListaCircularDE;
import com.listaenlazada.controlador.CorredoresFacade;
import com.listaenlazada.controlador.util.JsfUtil;
import com.listaenlazada.modelo.Corredores;
import com.listase.modelo.NodoDE;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
/**
 *
 * @author carloaiza
 */
@Named(value = "appBean")
@ApplicationScoped
public class AppBean {
    
    private String correoTurno="prueba@prueba.com";
    private int cont=0;
    
     private List<Corredores> listadoCorredores;
    @EJB
    private CorredoresFacade connCorredor;
    
    private Corredores corredorSeleccionado;
    
    private ListaCircularDE listaCircularCorredores;
    
    private NodoDE ayudante;
    
    private boolean verInicio=true;
    /**
     * Creates a new instance of AppBean
     */
    public AppBean() {
    }

    @PostConstruct
        public void inicializar()
        {
            listadoCorredores = connCorredor.findAll();
            listaCircularCorredores = new ListaCircularDE();
            //recorrer el listado y envio el infante a laista SE
            for(Corredores inf:listadoCorredores)
            {
                listaCircularCorredores.adicionarNodo(inf);
            }
        
            ayudante = listaCircularCorredores.getCabeza();
            corredorSeleccionado = ayudante.getDato();       
    }
    
    
    public boolean isVerInicio() {
        return verInicio;
    }

    public void setVerInicio(boolean verInicio) {
        this.verInicio = verInicio;
    }
    
    

    public Corredores getCorredorSeleccionado() {
        return corredorSeleccionado;
    }

    public void setCorredorSeleccionado(Corredores corredorSeleccionado) {
        this.corredorSeleccionado = corredorSeleccionado;
    }

    public ListaCircularDE getListaCircularCorredores() {
        return listaCircularCorredores;
    }

    public void setListaCircularCorredores(ListaCircularDE listaCircularCorredores) {
        this.listaCircularCorredores = listaCircularCorredores;
    }
    
    
    public List<Corredores> getListadoCorredores() {
        return listadoCorredores;
    }

    public void setListadoInfantes(List<Corredores> listadoInfantes) {
        this.listadoCorredores = listadoInfantes;
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
    
    
      public void pasarTingo()
    {        
       if(!verInicio)
       {
            ayudante = ayudante.getSiguiente();
            corredorSeleccionado = ayudante.getDato();
       }
       
    }
    
    public void aumentarContador(String correo)
    {
        switch(correo)
        {
            case "Prueba@prueba.com":
                correoTurno= "consulta@umanizales.edu.co";
                break;
            default:
                correoTurno= "prueba@prueba.com";
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
    
    public void controlarCiclo()
    {
        //False fue por que va a parar
        if(!verInicio)
        {
            //Eliminaría el niño . Valido lo seleccionado
            for(Corredores inf: listadoCorredores)
            {
                if(inf.getCodigo() == corredorSeleccionado.getCodigo())
             
                    listadoCorredores.remove(inf);
                    break;
                }
            }    
            
            if(listadoCorredores.size()==1)
            {
                JsfUtil.addSuccessMessage("Ha ganado "+listadoCorredores.get(0));
            }
            
            verInicio = !verInicio;
        }    
       
    }
    

