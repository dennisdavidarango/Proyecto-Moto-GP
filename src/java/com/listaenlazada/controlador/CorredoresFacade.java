/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaenlazada.controlador;

import com.listaenlazada.modelo.Corredores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JULIMEL
 */
@Stateless
public class CorredoresFacade extends AbstractFacade<Corredores> {

    @PersistenceContext(unitName = "listasprogram2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorredoresFacade() {
        super(Corredores.class);
    }
    
}
