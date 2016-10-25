/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.stream.Collectors;
import service.BancaService;

/**
 *
 * @author Israel Dago
 */
public class Cont implements java.io.Serializable{
    private Integer id;
    private String iban, descriere;
    private Double sold;
    private String dataCreation;
    private boolean active;
    private Client client;   
    public Cont() {
    }

    public Cont(Integer id, String iban, String descriere, Double sold, String dataCreation, boolean active, Client client) {
        this.id = id;
        this.iban = iban;
        this.descriere = descriere;
        this.sold = sold;
        this.dataCreation = dataCreation;
        this.active = active;
        this.client = client;
    }

    public Cont(Integer id, String iban, String descriere, Double sold, String dataCreation, boolean active) {
        this.id = id;
        this.iban = iban;
        this.descriere = descriere;
        this.sold = sold;
        this.dataCreation = dataCreation;
        this.active = active;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public String getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(String dataCreation) {
        this.dataCreation = dataCreation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Cont> getAllConturi(){
        return BancaService.getInstance().findAllConturi();
    }
    
    public List<Cont> getInactiveConturi(){
        return BancaService.getInstance().findAllConturi()
                .stream().filter(c ->c.isActive()==false)
                .collect(Collectors.toList());
    }
    
    public List<Cont> getActiveConturi(){
        return BancaService.getInstance().findActiveConturi();
    }
    
   

    
    
    
}
