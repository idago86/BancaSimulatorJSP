/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.exceptions.MyException;
import java.util.List;
import model.Admin;
import model.Client;
import model.Cont;
import service.AdminService;
import service.BancaService;
import utils.Cryptare;

/**
 *
 * @author Israel Dago
 */
public class FrontCrontroller {
  
    
    private FrontCrontroller() {
    }
    
    private static final class SingletonHolder{
        private static final FrontCrontroller SINGLETON = new FrontCrontroller();
    }
    
    public static FrontCrontroller getInstance(){return SingletonHolder.SINGLETON;}
    
    public boolean register(String username, String parola){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setParola(Cryptare.hidePassword(parola));
        return AdminService.getInstance().register(admin);
    }
    
    
    public Admin login(String username, String parola){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setParola(Cryptare.hidePassword(parola));
        return AdminService.getInstance().login(admin);
    }
    
    public void adaugaClient(String nume, String prenume, String cnp){
        Client client = new Client(0, nume, prenume, cnp);
        BancaService.getInstance().adaugaClient(client);
    }
    
    public List<Client> getAllClients(){
        return BancaService.getInstance().findAllClienti();
    }
    
    public void adaugaCont(String iban, String desciere){
        BancaService.getInstance().adaugaCont(iban, desciere);
    }
    
    public List<Cont> getAllConturi(){
        return BancaService.getInstance().findAllConturi();
    }
    
     public void attributeContToClient(String clientId, String contId){
         Integer clientID = Integer.parseInt(clientId);
         Integer contID = Integer.parseInt(contId);
         BancaService.getInstance().attributeContToClient(clientID, contID);                 
     }
    
    public void depunereNumar(String suma, String contCreditatID){
        Double sumaDeDepus = Double.parseDouble(suma);
        Integer contID = Integer.parseInt(contCreditatID);
        BancaService.getInstance().depunereNumar(sumaDeDepus, contID);
    }
    
     public String retragereNumar(String suma, String contDebitatID){
        Double sumaDeRetras = Double.parseDouble(suma);
        Integer contID = Integer.parseInt(contDebitatID);        
        try {  
            BancaService.getInstance().retragereNumar(sumaDeRetras, contID);
            return null;
        } catch (MyException ex) {
            return ex.getMessage();
        }
     } 
     
     public String transferNumar(String suma,String contDebitatId, String contCreditatId){
        Double sumaDeTransferat = Double.parseDouble(suma);
        Integer contDebitatID = Integer.parseInt(contDebitatId);   
        Integer contCreditatID = Integer.parseInt(contCreditatId);   
          
        try {  
            BancaService.getInstance().transferNumar(sumaDeTransferat, contDebitatID, contCreditatID);
            return null; 
        } catch (MyException ex) { 
            return ex.getMessage();
        }
     }
     
     public Cont findCont(String id){
         Integer contID = Integer.parseInt(id);
         return BancaService.getInstance().findCont(contID);
         
     }
     
     public String findContActive(String id){
         Integer contID = Integer.parseInt(id);
         Cont rez = BancaService.getInstance().findCont(contID);
         if(rez!=null){
             if(rez.isActive()){ 
         return "true";
        }
         }         
         return "false";
     }
     
     
     
     public void editCont(String ContID, String ibanul, String descrierea){
         Cont c = findCont(ContID);
         c.setIban(ibanul);
         c.setDescriere(descrierea);
         BancaService.getInstance().editCont(c);
     }
     
     
     public void deleteCont(String ContID){
         Integer id = Integer.parseInt(ContID);
         BancaService.getInstance().removeCont(id);
     }
     
     
     public void deleteClient(String ClientId){
         Integer id = Integer.parseInt(ClientId);
         BancaService.getInstance().removeClient(id);
     }
    
}
