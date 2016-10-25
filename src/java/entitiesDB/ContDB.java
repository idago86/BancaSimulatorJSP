/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Israel Dago
 */
@Entity
@Table(name = "conturi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContDB.findAll", query = "SELECT c FROM ContDB c")
    , @NamedQuery(name = "ContDB.findById", query = "SELECT c FROM ContDB c WHERE c.id = :id")
    , @NamedQuery(name = "ContDB.findByIban", query = "SELECT c FROM ContDB c WHERE c.iban = :iban")
    , @NamedQuery(name = "ContDB.findByDescriere", query = "SELECT c FROM ContDB c WHERE c.descriere = :descriere")
    , @NamedQuery(name = "ContDB.findBySold", query = "SELECT c FROM ContDB c WHERE c.sold = :sold")
    , @NamedQuery(name = "ContDB.findByDateCreation", query = "SELECT c FROM ContDB c WHERE c.dateCreation = :dateCreation")
    , @NamedQuery(name = "ContDB.findByActive", query = "SELECT c FROM ContDB c WHERE c.active = :active")})
public class ContDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "iban")
    private String iban;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descriere")
    private String descriere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sold")
    private double sold;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dateCreation")
    private String dateCreation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne
    private ClientDB client;

    public ContDB() {
    }

    public ContDB(Integer id) {
        this.id = id;
    }

    public ContDB(Integer id, String iban, String descriere, double sold, String dateCreation, boolean active) {
        this.id = id;
        this.iban = iban;
        this.descriere = descriere;
        this.sold = sold;
        this.dateCreation = dateCreation;
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

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ClientDB getClient() {
        return client;
    }

    public void setClient(ClientDB client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContDB)) {
            return false;
        }
        ContDB other = (ContDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesDB.ContDB[ id=" + id + " ]";
    }
    
}
