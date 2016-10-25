/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesDB;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Israel Dago
 */
@Entity
@Table(name = "clienti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientDB.findAll", query = "SELECT c FROM ClientDB c")
    , @NamedQuery(name = "ClientDB.findById", query = "SELECT c FROM ClientDB c WHERE c.id = :id")
    , @NamedQuery(name = "ClientDB.findByNume", query = "SELECT c FROM ClientDB c WHERE c.nume = :nume")
    , @NamedQuery(name = "ClientDB.findByPrenume", query = "SELECT c FROM ClientDB c WHERE c.prenume = :prenume")
    , @NamedQuery(name = "ClientDB.findByCnp", query = "SELECT c FROM ClientDB c WHERE c.cnp = :cnp")})
public class ClientDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nume")
    private String nume;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "prenume")
    private String prenume;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cnp")
    private String cnp;
    @OneToMany(mappedBy = "client")
    private Collection<ContDB> contDBCollection;

    public ClientDB() {
    }

    public ClientDB(Integer id) {
        this.id = id;
    }

    public ClientDB(Integer id, String nume, String prenume, String cnp) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @XmlTransient
    public Collection<ContDB> getContDBCollection() {
        return contDBCollection;
    }

    public void setContDBCollection(Collection<ContDB> contDBCollection) {
        this.contDBCollection = contDBCollection;
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
        if (!(object instanceof ClientDB)) {
            return false;
        }
        ClientDB other = (ClientDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesDB.ClientDB[ id=" + id + " ]";
    }
    
}
