/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ClientDao;
import dao.ContDao;
import dao.exceptions.MyException;
import entitiesDB.ClientDB;
import entitiesDB.ContDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.Client;
import model.Cont;
import utils.ConvertDate;

/**
 *
 * @author Israel Dago
 */
public class BancaService {
    private Context ctx;
    private UserTransaction utx;
    private EntityManager em; 
    private ClientDao clientDao;
    private ContDao contDao;

    private BancaService() {
        try {
            ctx = new InitialContext();
            utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            clientDao = new ClientDao(em);
            contDao = new ContDao(em);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final class SingletonHolder{
        private static final BancaService SINGLETON = new BancaService();
    }
    
    public static BancaService getInstance(){return SingletonHolder.SINGLETON;}
    
    // Client Methods
    public void adaugaClient(Client client){
        try {
            utx.begin();
            ClientDB clientDB = new ClientDB(0, client.getNume(), client.getPrenume(), client.getCnp());
            clientDao.adaugaClient(clientDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        } 
    } 
    
    public void removeClient(Integer clientID){
        try {
            utx.begin();
            clientDao.removeClient(clientID);
            utx.commit();           
        } catch (Exception ex) {
            try {
                utx.rollback();                
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        } 
    }
    
    public void editClient(Client client){
        try {
            utx.begin();
            ClientDB clientDB = clientDao.findClient(client.getId());
            clientDB.setNume(client.getNume());
            clientDB.setPrenume(client.getPrenume());
            clientDB.setCnp(client.getCnp());
            clientDao.editClient(clientDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        }         
    }    
    
    public List<Client> findAllClienti(){
         return clientDao.findAllClienti()
                 .stream()
                 .map(c -> new Client(c.getId(), c.getNume(), c.getPrenume(), c.getCnp()))
                 .collect(Collectors.toList());
    }
        
    //Conturi Methods
    
    public void adaugaCont(String iban, String descriere){
        try {
            utx.begin();
            ContDB contDB = new ContDB();
            contDB.setIban(iban);
            contDB.setDescriere(descriere);
            contDB.setDateCreation(ConvertDate.toString(new Date()));
            contDB.setActive(false);
            contDao.adaugaCont(contDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        } 
    } 
    
    public void removeCont(Integer contID){
        try {
            utx.begin();
            contDao.removeCont(contID);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        } 
    }
    
    public void editCont(Cont cont){
        try {
            utx.begin();
            ContDB contDB = contDao.findCont(cont.getId());
            contDB.setIban(cont.getIban());
            contDB.setDescriere(cont.getDescriere());
            contDB.setSold(cont.getSold());
            contDB.setDateCreation(cont.getDataCreation());
            contDB.setActive(cont.isActive());
            contDB.setClient(clientDao.findClient(cont.getClient().getId()));
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        }         
    }    
    
    public void attributeContToClient(Integer clientID, Integer contID){
        try {
            utx.begin();            
            ContDB contDB = contDao.findCont(contID);            
            if(contDB!=null){
                contDB.setClient(clientDao.findClient(clientID));
                contDB.setActive(true);
                contDao.editCont(contDB);
                utx.commit();                
            }else throw new RuntimeException("Cont introuvable in DB");
        } catch ( HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        }         
    }  
    
    public Cont findCont(Integer id){
        ContDB rez = contDao.findCont(id);
        if(rez!=null)
        return new Cont(rez.getId(), rez.getIban(), rez.getDescriere(), rez.getSold(), rez.getDateCreation(), rez.getActive());
        else return null;
    }
    public List<Cont> findAllConturi(){        
      return contDao.findAllConturi()
                 .stream()
                 .map(c -> new Cont(c.getId(), c.getIban(), c.getDescriere(), c.getSold(), c.getDateCreation(), c.getActive()))
                 .collect(Collectors.toList());
        
    }
    
    
   
    
    public List<Cont> findActiveConturi(){
        return contDao.findAllConturi()
                 .stream().filter(c ->c.getActive()==true)
                 .map(c -> new Cont(c.getId(), c.getIban(), c.getDescriere(), c.getSold(), c.getDateCreation(), c.getActive(), new Client(c.getClient().getId(), c.getClient().getNume(), c.getClient().getPrenume(), c.getClient().getCnp())))
                 .collect(Collectors.toList());
    }
    
    
    //transactions Methods
    
    public void depunereNumar(Double suma, Integer contCreditatID){
          try {
            utx.begin();            
            ContDB contDB = contDao.findCont(contCreditatID);
            contDB.setSold(contDB.getSold()+suma);
            contDao.editCont(contDB);         
            utx.commit();
            System.out.println("s a ajuns la depunere");
        } catch (Exception ex) {
            try {
                // utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        }      
    }
    
    public void retragereNumar(Double suma, Integer contDebitatID) throws MyException{
          try {
            utx.begin();            
            ContDB contDB = contDao.findCont(contDebitatID);            
            if(contDB.getSold()>=suma){
                contDB.setSold((contDB.getSold()- suma));                 
                contDao.editCont(contDB);               
                utx.commit();                
            }else throw new MyException("Insufficient funds for this transaction"); 
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            } 
        }      
    }
    
    public void transferNumar(Double suma,Integer contDebitatID, Integer contCreditatID) throws MyException{
           retragereNumar(suma, contDebitatID);
            depunereNumar(suma, contCreditatID); 
    }
    
    
        
}
