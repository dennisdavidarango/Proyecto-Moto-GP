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
import com.listase.modelo.ListaDE;
import com.listase.modelo.Nodo;
import com.listase.modelo.NodoDE;
import com.pirinola.modelo.DatoPirinola;
import com.pirinola.modelo.DatoPirinolaExcepcion;
import com.pirinola.modelo.ListaCircularPirinola;
import com.pirinola.modelo.NodoPirinola;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
/**
 *
 * @author carloaiza
 */
@Named(value = "appBean")
@ApplicationScoped
public class AppBean {
    
    private String correoTurno="prueba@prueba.com";
    private int cont=0;
    private ListaCircularPirinola listaCorredores;
     private List<Corredores> listadoCorredores;
    @EJB
    private CorredoresFacade connCorredor;
    
    private DefaultDiagramModel model;
    
    private Corredores corredorSeleccionado;
    
    private ListaCircularDE listaCircularCorredores;
    
    private NodoDE ayudante1;
    
    private NodoPirinola ayudante;
    
    private boolean verInicio=true;
    
    private NodoPirinola cabeza;
    /**
     * Creates a new instance of AppBean
     */
    public AppBean() {
    }

    @PostConstruct
        public void inicializar()
        {
            model = new DefaultDiagramModel();
            model.setMaxConnections(-1);
            model.setConnectionsDetachable(false);
            
            Element elementA = new Element("Toma todo", "10em", "6em");
            elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
            Element elementB = new Element("Toma uno", "20em", "18em");
            elementB.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementC = new Element("Toma dos", "30em", "18em");
            elementC.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
            Element elementD = new Element("Pon uno", "40em", "18em");
            elementD.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementE = new Element("Pon dos", "50em", "18em");
            elementE.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
            Element elementF = new Element("Todos ponen", "60em", "30em");
            elementF.addEndPoint(new DotEndPoint(EndPointAnchor.ASSIGN));
        
            model.addElement(elementA);
            model.addElement(elementB);
            model.addElement(elementC);
            model.addElement(elementD);
            model.addElement(elementE);
            model.addElement(elementF);
        
            model.connect(new Connection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));        
            model.connect(new Connection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
            model.connect(new Connection(elementB.getEndPoints().get(0), elementD.getEndPoints().get(0)));
            model.connect(new Connection(elementC.getEndPoints().get(0), elementE.getEndPoints().get(0)));
            model.connect(new Connection(elementF.getEndPoints().get(0), elementE.getEndPoints().get(0)));        
            model.connect(new Connection(elementF.getEndPoints().get(0), elementD.getEndPoints().get(0)));
       
            listadoCorredores = connCorredor.findAll();
            listaCircularCorredores = new ListaCircularDE();
            //recorrer el listado y envio el infante a laista SE
            for(Corredores inf:listadoCorredores)
            {
                listaCircularCorredores.adicionarNodo(inf);
            }
        
            ayudante1 = listaCircularCorredores.getCabeza();
            corredorSeleccionado = ayudante1.getDato();       
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
    
     public DiagramModel getModel() {
        return model;
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
   
    
      public void pasarTingo(){
          
        if(!verInicio)
         {
              ayudante1 = ayudante1.getSiguiente();
              corredorSeleccionado = ayudante1.getDato();
          }
         }
      
      
      
      
       public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#3399ff',lineWidth:2}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaCorredores.getCabeza() != null) {
            //llamo a mi ayudante
            NodoPirinola temp = listaCorredores.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getTexto()+" "+
                        temp.getDato().getCantidad(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getTexto()));
                //adiciona un conector al cuadrito
                if(!temp.getDato().getVerdad())
                {
                     ele.setStyleClass("ui-diagram-element-boolean");
                }
               
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
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
        
        Corredores inf1 = new Corredores();
        Element element = new Element(inf1.getCodigo()+" "+
                                  inf1.getNombre());
        //False fue por que va a parar
        if(!verInicio)
        {
            //Eliminaría el niño . Valido lo seleccionado
            for(Corredores inf: listadoCorredores)
            {
                Element ele = new Element(inf.getCodigo()+" "+
                        inf.getNombre());
                if(inf.getCodigo() == corredorSeleccionado.getCodigo())
             
                    listadoCorredores.remove(inf);
                    break;
             }
            if(inf1.getGenero())
                {
                     element.setStyleClass("ui-diagram-element-boolean");
                
                }
        }    
            
            if(listadoCorredores.size()==1)
            {
                JsfUtil.addSuccessMessage("Ha ganado "+listadoCorredores.get(0));
            }
            
            verInicio = !verInicio;
        } 
    
      public void controlarCicloPirinola(){
          
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
    
    
    
      
public void adicionarNodo(DatoPirinola dato) {
        if (cabeza == null) {
            cabeza = new NodoPirinola(dato);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            
        } else {
            //Lamo a mi ayudante
           NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(dato);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
        }
    }
     
    public void adicionarNodoAlInicio(DatoPirinola dato) {
        if (cabeza == null) {
             cabeza = new NodoPirinola(dato);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(dato);
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
    
    
     public String listarInfantes(String listado) throws DatoPirinolaExcepcion
     {
        if (cabeza != null) {
            NodoPirinola temp = cabeza;
            do
            {
                listado += temp.getDato() + "\n";
                temp = temp.getSiguiente();
            }while(temp != cabeza);

            return listado;
        }
        throw new DatoPirinolaExcepcion(("No existen Corredores en la lista"));
    }
    
    //Eliminar NOdo

}

  
    

