/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listaenlazada.controlador.CorredoresFacade;
import com.listaenlazada.modelo.Corredores;
import com.listase.excepciones.CorredorExcepcion;
import com.listase.modelo.Corredor;
import com.listase.modelo.ListaSE;
import com.listase.modelo.Nodo;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author carloaiza
 */
@Named(value = "sesionCorredor")
@SessionScoped
public class SesionCorredor implements Serializable {
    private ListaSE listaCorredores;
    private Corredores corredor;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private Nodo ayudante;   
    private String textoVista="Gráfico";
    
    private List<Corredores> listadoCorredores;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private int posicionCorredor;
    
    private String opcionElegida="1";
 
    private int numeroPosiciones=1;
    
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short corredorSeleccionado;
    
    private Corredores corredorDiagrama;
    
    @EJB
    private CorredoresFacade connCorredores;
    /**
     * Creates a new instance of SesionCorredor
     */
    public SesionCorredor() {        
    }
    
    @PostConstruct
    private void inicializar()
    {
        controlLocalidades = new ControladorLocalidades();
        //inicializando el combo en el primer depto
        codigoDeptoSel = controlLocalidades.getDepartamentos().get(0).getCodigo();
        
        listaCorredores = new ListaSE(); 
        
         listadoCorredores = connCorredores.findAll();
        //recorrer el listado y envio el infante a laista SE
        for(Corredores inf:listadoCorredores)
        {
            listaCorredores.adicionarNodo(inf);
        }
        
        //LLenado de la bds
       // listaCorredor.adicionarNodo(new Corredor("Dennis ",(short) 1, (byte)31, true, 
       // controlLocalidades.getCiudades().get(0).getNombre(),(short) 2));
       // listaCorredor.adicionarNodo(new Corredor("David ",(short) 2, (byte)22, true,
       // controlLocalidades.getCiudades().get(3).getNombre(),(short) 3 ));
       // listaCorredor.adicionarNodo(new Corredor("Carlos ",(short) 3, (byte)20,true,
       // controlLocalidades.getCiudades().get(1).getNombre(),(short) 4));
       // listaCorredor.adicionarNodoAlInicio(new Corredor("Lucrecia ",(short) 4, (byte)18,false,
       // controlLocalidades.getCiudades().get(2).getNombre(),(short) 1));
       // ayudante = listaCorredor.getCabeza();
       // corredor = ayudante.getDato();     
        //Me llena el objeto List para la tabla
       // listadoCorredor = listaCorredor.obtenerListaCorredores();
       // pintarLista();
       if(listaCorredores.getCabeza()!=null)
        {
            ayudante = listaCorredores.getCabeza();
            corredor = ayudante.getDato();     
        }
        else
        {
            corredor = new Corredores();
        }
        //Me llena el objeto List para la tabla
        //listadoInfantes = listaInfantes.obtenerListaInfantes();
        pintarLista();
   }

    public Corredores getCorredorDiagrama() {
        return corredorDiagrama;
    }

    public void setCorredorDiagrama(Corredores corredorDiagrama) {
        this.corredorDiagrama = corredorDiagrama;
    }
    
       public int getPosicionCorredor() {
        return posicionCorredor;
    }

    public void setPosicionCorredor(int posicionCorredor) {
        this.posicionCorredor = posicionCorredor;
    }
    
     public int getNumeroPosiciones() {
        return numeroPosiciones;
    }

    public void setNumeroPosiciones(int numeroPosiciones) {
        this.numeroPosiciones = numeroPosiciones;
    }
    
    
       public String getOpcionElegida() {
        return opcionElegida;
    }

    public void setOpcionElegida(String opcionElegida) {
        this.opcionElegida = opcionElegida;
    }
    
    public short getCorredorSeleccionado() {
        return corredorSeleccionado;
    }

    public void setCorredorSeleccionado(short corredorSeleccionado) {
        this.corredorSeleccionado = corredorSeleccionado;
    }
    
    public String getCodigoDeptoSel() {
        return codigoDeptoSel;
    }

    public void setCodigoDeptoSel(String codigoDeptoSel) {
        this.codigoDeptoSel = codigoDeptoSel;
    }

    
    
    public ControladorLocalidades getControlLocalidades() {
        return controlLocalidades;
    }

    public void setControlLocalidades(ControladorLocalidades controlLocalidades) {
        this.controlLocalidades = controlLocalidades;
    }
     
    
    
    public DiagramModel getModel() {
        return model;
    }
     
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }

    public short getCodigoEliminar() {
        return codigoEliminar;
    }

    public void setCodigoEliminar(short codigoEliminar) {
        this.codigoEliminar = codigoEliminar;
    }

    
    
    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }

    
    
    public List getListadoCorredor() {
        return listadoCorredores;
    }

    public void setListadoCorredor(List listadoCorredor) {
        this.listadoCorredores = listadoCorredor;
    }
    
    

    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

  
    
    

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }
    
    public ListaSE getListaCorredor() {
        return listaCorredores;
    }

    public void setListaCorredor(ListaSE listaCorredor) {
        this.listaCorredores = listaCorredor;
    }

    public Corredores getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredores corredor) {
        this.corredor = corredor;
    }
    
    
    
    public void guardarCorredor()
    {
        //obtiene el consecutivo
        corredor.setCodigo((short)(listaCorredores.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaCorredores.adicionarNodoAlInicio(corredor);
        }
        else
        {
            listaCorredores.adicionarNodo(corredor);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El corredor se ha guardado exitosamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        corredor = new Corredores();
    }
    
    public void irSiguiente()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            corredor = ayudante.getDato();
        }        
    }
    
    public void irPrimero()
    {
        if(listaCorredores.getCabeza()!=null)
        {
            ayudante = listaCorredores.getCabeza();
            corredor = ayudante.getDato();
            
        }
        else
        {
            corredor = new Corredores();
        }
        listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaCorredores.getCabeza()!=null)
        {            
            while(ayudante.getSiguiente()!=null)
            {
                ayudante = ayudante.getSiguiente();
            }
            corredor=ayudante.getDato();
        }
    }
    
    public void cambiarVistaCorredor()
    {
        if(textoVista.compareTo("Tabla")==0)
        {
            textoVista = "Gráfico";
        }
        else
        {
            textoVista = "Tabla";
        }
    }
    
    public void invertirLista(){
        //Invierte la lista
        listaCorredores.invertirLista();
        irPrimero();
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
            Nodo temp = listaCorredores.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo()+" "+
                        temp.getDato().getNombre(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                if(!temp.getDato().getGenero())
                {
                     ele.setStyleClass("ui-diagram-element-mujer");
                
                }
               
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
           
            //Pinta las flechas            
           /* for(int i=0; i < model.getElements().size() -1; i++)
            {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1), 
                        model.getElements().get(i+1).getEndPoints().get(0), "Next"));
            }
            */
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        corredorSeleccionado = Short.valueOf(id.replaceAll("frmCorredor:diagrama-", ""));
        System.out.println(corredorSeleccionado);
    }

    public void eliminarCorredor()
    {
        if(codigoEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                connCorredores.remove(listaCorredores.obtenerCorredor(codigoEliminar));
                listaCorredores.eliminarCorredor(codigoEliminar);
                //Eliminamos de bds
                
                irPrimero();
                JsfUtil.addSuccessMessage("Infante "+codigoEliminar +" eliminado.");
            }
            catch(CorredorExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+codigoEliminar+ " no es válido");
        }
    }
    
    public void obtenerCorredorDiagrama()
    {
        try {
            corredorDiagrama = listaCorredores.obtenerCorredor(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void obtenerPosicionCorredor()
    {
        try {
            posicionCorredor = listaCorredores.obtenerPosicionCorredor(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el corredor y guardar los datos en una variable temporal
            Corredores infTemporal = listaCorredores.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredores.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al final
            listaCorredores.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
 
    public void enviarAlInicio()
    {
        try {
            ///Buscar el corredor y guardar los datos en una variable temporal
            Corredores infTemporal = listaCorredores.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredores.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al inicio
            listaCorredores.adicionarNodoAlInicio(infTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
}
