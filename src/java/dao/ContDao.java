/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiesDB.ContDB;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Israel Dago
 */
public class ContDao implements Serializable {
    private EntityManager em = null;
    
    public ContDao( EntityManager em) {        
        this.em = em;
    }
    
    public void adaugaCont(ContDB cont){
        em.persist(cont);
    }
    
    public void editCont(ContDB cont){
        em.merge(cont);
    }
    
    public void removeCont(Integer id){
        em.remove(findCont(id));
    }
    
    public ContDB findCont(Integer id){        
        return em.find(ContDB.class, id);
    }
    
    public List<ContDB> findAllConturi(){
        Query q = em.createNamedQuery("ContDB.findAll", ContDB.class);
        return q.getResultList();
    }

    
}
