/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dennis David Arango
 */
public class ListaSE implements Serializable{
    private Nodo cabeza;

    public ListaSE() {
   
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    
    public void adicionarNodo(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(infante);
        }
        else
        {
            //Lamo a mi ayudante
            Nodo temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new Nodo(infante));
        }
        
    }
    
    public void adicionarNodoAlInicio(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(infante);
        }
        else
        {
            Nodo temp= new Nodo(infante);
            temp.setSiguiente(cabeza);
            cabeza= temp;
        }
    }
    
    public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            Nodo temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String obtenerListadoInfantes()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
        return listarInfantes("");
    }
    
    public String listarInfantes(String listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay infantes";
    }
    
    
     public List obtenerListaInfantes()
    {
        List<Infante> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarInfantes(listado);
        return listado;
    }
    
    public void listarInfantes(List listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();
                
            }            
        }
        
    }
    
      public void invertirLista()
    {
      ListaSE listaTemporal = new ListaSE();
      if(cabeza != null)
      {
          Nodo ayudante = cabeza;
          while(ayudante !=null)
          {
              listaTemporal.adicionarNodoAlInicio(ayudante.getDato());
              ayudante.getSiguiente();
          }
      cabeza = listaTemporal.getCabeza();
      }
    }
    
      public int contarNiñas()
      {
          int contNiñas = 0;
          Nodo temp = cabeza;
          if(temp.getSiguiente()!=null)    
          {
              while(temp.getDato().getGenero().equals("F"))
              {
                  contNiñas +=1;
              }
          }
          return contNiñas;
      }
       public int contarNiños()
      {
          int contNiños = 0;
          Nodo temp = cabeza;
          if(temp.getSiguiente()!=null)    
          {
              while(temp.getDato().getGenero().equals("M"))
              {
                  contNiños +=1;
              }
          }
          return contNiños;
      
      }
      
    
}
