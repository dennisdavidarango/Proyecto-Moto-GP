/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

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
    private ListaSE listaCorredor;
    private Corredor corredor;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private Nodo ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoCorredor;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short corredorSeleccionado;
    
    private Corredor corredorDiagrama;
    
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
        
        listaCorredor = new ListaSE();        
        //LLenado de la bds
        listaCorredor.adicionarNodo(new Corredor("Dennis ",(short) 1, (byte)31, true, 
        controlLocalidades.getCiudades().get(0).getNombre(),(short) 2));
        listaCorredor.adicionarNodo(new Corredor("David ",(short) 2, (byte)22, true,
        controlLocalidades.getCiudades().get(3).getNombre(),(short) 3 ));
        listaCorredor.adicionarNodo(new Corredor("Carlos ",(short) 3, (byte)20,true,
        controlLocalidades.getCiudades().get(1).getNombre(),(short) 4));
        listaCorredor.adicionarNodoAlInicio(new Corredor("Lucrecia ",(short) 4, (byte)18,false,
        controlLocalidades.getCiudades().get(2).getNombre(),(short) 1));
        ayudante = listaCorredor.getCabeza();
        corredor = ayudante.getDato();     
        //Me llena el objeto List para la tabla
        listadoCorredor = listaCorredor.obtenerListaCorredores();
        pintarLista();
   }

    public Corredor getCorredorDiagrama() {
        return corredorDiagrama;
    }

    public void setCorredorDiagrama(Corredor corredorDiagrama) {
        this.corredorDiagrama = corredorDiagrama;
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
        return listadoCorredor;
    }

    public void setListadoCorredor(List listadoCorredor) {
        this.listadoCorredor = listadoCorredor;
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
        return listaCorredor;
    }

    public void setListaCorredor(ListaSE listaCorredor) {
        this.listaCorredor = listaCorredor;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }
    
    
    
    public void guardarCorredor()
    {
        //obtiene el consecutivo
        corredor.setCodigo((short)(listaCorredor.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaCorredor.adicionarNodoAlInicio(corredor);
        }
        else
        {
            listaCorredor.adicionarNodo(corredor);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoCorredor = listaCorredor.obtenerListaCorredores();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El corredor se ha guardado exitosamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        corredor = new Corredor();
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
        if(listaCorredor.getCabeza()!=null)
        {
            ayudante = listaCorredor.getCabeza();
            corredor = ayudante.getDato();
            
        }
        else
        {
            corredor = new Corredor();
        }
        listadoCorredor = listaCorredor.obtenerListaCorredores();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaCorredor.getCabeza()!=null)
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
        listaCorredor.invertirLista();
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
        if (listaCorredor.getCabeza() != null) {
            //llamo a mi ayudante
            Nodo temp = listaCorredor.getCabeza();
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
        try {
            listaCorredor.eliminarCorredor(corredorSeleccionado);
            irPrimero();
        } catch (CorredorExcepcion ex) {
           JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void obtenerCorredorDiagrama()
    {
        try {
            corredorDiagrama = listaCorredor.obtenerCorredor(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
       public void obtenerPosicionDiagrama()
    {
        try {
            corredorDiagrama = listaCorredor.obtenerPosicion(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el corredor y guardar los datos en una variable temporal
            Corredor infTemporal = listaCorredor.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredor.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al final
            listaCorredor.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void insertarCorredor(){
        try
        {
           //Buscar el corredor y guardar los datos en una variable temporal
         Corredor infTemporal = listaCorredor.obtenerCorredor(corredorSeleccionado);
           // Eliminar el nodo
         listaCorredor.eliminarCorredor(corredorSeleccionado);  
           // Adicionarlo en la siguiente posición
         listaCorredor.adicionarNodoEnPosicion(infTemporal);
         pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    public void enviarAlInicio()
    {
        try {
            ///Buscar el corredor y guardar los datos en una variable temporal
            Corredor infTemporal = listaCorredor.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredor.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al inicio
            listaCorredor.adicionarNodoAlInicio(infTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
}
