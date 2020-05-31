/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pirinola.modelo;

import com.listaenlazada.modelo.Corredores;
import com.listase.excepciones.CorredorExcepcion;
import java.io.Serializable;

/**
 *
 * @author JULIMEL
 */
public class ListaCircularPirinola implements Serializable{
    private NodoPirinola cabeza;

    public NodoPirinola getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoPirinola cabeza) {
        this.cabeza = cabeza;
    }

    public void adicionarNodo(Corredores corredor) {
        if (cabeza == null) {
            cabeza = new NodoPirinola(corredor);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            
        } else {
            //Lamo a mi ayudante
           NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(corredor);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
        }
    }
    
    public void adicionarNodoAlInicio(Corredores corredor) {
        if (cabeza == null) {
             cabeza = new NodoPirinola(corredor);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(corredor);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
           cabeza = cabeza.getAnterior();
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
            NodoPirinola temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=cabeza)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String listarInfantes(String listado) throws CorredorExcepcion
     {
        if (cabeza != null) {
            NodoPirinola temp = cabeza;
//            while (temp.getSiguiente() != cabeza) {
//                listado += temp.getDato() + "\n";
//                temp = temp.getSiguiente();
//            }
//            listado += temp.getDato() + "\n";
            do
            {
                listado += temp.getDato() + "\n";
                temp = temp.getSiguiente();
            }while(temp != cabeza);

            return listado;
        }
        throw new CorredorExcepcion(("No existen Corredores en la lista"));
    }
    
}
