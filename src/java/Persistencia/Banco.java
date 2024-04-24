/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FRANZ ROMERO
 */
@Entity
@Table(name = "BANCO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")
    , @NamedQuery(name = "Banco.findByCodbanco", query = "SELECT b FROM Banco b WHERE b.codbanco = :codbanco")
    , @NamedQuery(name = "Banco.findByNombreb", query = "SELECT b FROM Banco b WHERE b.nombreb = :nombreb")})
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODBANCO")
    private String codbanco;
    @Basic(optional = false)
    @Column(name = "NOMBREB")
    private String nombreb;

    public Banco() {
    }

    public Banco(String codbanco) {
        this.codbanco = codbanco;
    }

    public Banco(String codbanco, String nombreb) {
        this.codbanco = codbanco;
        this.nombreb = nombreb;
    }

    public String getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(String codbanco) {
        this.codbanco = codbanco;
    }

    public String getNombreb() {
        return nombreb;
    }

    public void setNombreb(String nombreb) {
        this.nombreb = nombreb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codbanco != null ? codbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.codbanco == null && other.codbanco != null) || (this.codbanco != null && !this.codbanco.equals(other.codbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Banco[ codbanco=" + codbanco + " ]";
    }
    
}
