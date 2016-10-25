/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiesDB.ClientDB;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Israel Dago
 */
public class ClientDao implements Serializable {
    private EntityManager em = null;
    
    public ClientDao( EntityManager em) {        
        this.em = em;
    }
    
    public void adaugaClient(ClientDB client){
        em.persist(client);
    }
    
    public void editClient(ClientDB client){
        em.merge(findClient(client.getId()));
    }
    
    public void removeClient(Integer id){
        em.remove(findClient(id));
    }
    
    public ClientDB findClient(Integer id){
        return em.find(ClientDB.class, id);
    }
    
    public List<ClientDB> findAllClienti(){
        Query q = em.createNamedQuery("ClientDB.findAll", ClientDB.class);
        return q.getResultList().isEmpty()?null:q.getResultList();
    }

    
    
}
