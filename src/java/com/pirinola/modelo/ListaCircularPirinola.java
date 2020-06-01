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

    public void adicionarNodo(Jugador jugador) {
        if (cabeza == null) {
            cabeza = new NodoPirinola(jugador);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            
        } else {
            //Lamo a mi ayudante
           NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(jugador);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
        }
    }
    
    public void adicionarNodoAlInicio(Jugador jugador) {
        if (cabeza == null) {
             cabeza = new NodoPirinola(jugador);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(jugador);
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
    
    public String listarJugadores(String listado) throws JugadorExcepcion
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
        throw new JugadorExcepcion(("No existen Corredores en la lista"));
    }
     public void eliminarNodo(String nombre) throws JugadorExcepcion {
        if (cabeza != null) {
            if (cabeza.getSiguiente() == cabeza) {
                if (cabeza.getDato().getNombre().equals(nombre)) {
                    cabeza = null;
                    return;
                }
            } else {
                NodoPirinola temp = cabeza;
                do {
                    if (temp.getDato().getNombre().equals(nombre)) {
                        //estamos parados en el que hay que eliminar
                        temp.getAnterior().setSiguiente(temp.getSiguiente());
                        temp.getSiguiente().setAnterior(temp.getAnterior());
                        return;
                    }
                    temp = temp.getSiguiente();
                } while (temp != cabeza);
            }
            throw new JugadorExcepcion(("El Jugador no se encuentra en la lista"));
        } else {
            throw new JugadorExcepcion(("No existen jugadors en la lista"));
        }
    }
}
