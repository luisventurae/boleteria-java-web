/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FRANZ ROMERO
 */
@Entity
@Table(name = "FECHA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fecha.findAll", query = "SELECT f FROM Fecha f")
    , @NamedQuery(name = "Fecha.findByCodfecha", query = "SELECT f FROM Fecha f WHERE f.codfecha = :codfecha")
    , @NamedQuery(name = "Fecha.findByFechainicio", query = "SELECT f FROM Fecha f WHERE f.fechainicio = :fechainicio")
    , @NamedQuery(name = "Fecha.findByFechafin", query = "SELECT f FROM Fecha f WHERE f.fechafin = :fechafin")})
public class Fecha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFECHA")
    private String codfecha;
    @Basic(optional = false)
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;

    public Fecha() {
    }

    public Fecha(String codfecha) {
        this.codfecha = codfecha;
    }

    public Fecha(String codfecha, Date fechainicio, Date fechafin) {
        this.codfecha = codfecha;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public String getCodfecha() {
        return codfecha;
    }

    public void setCodfecha(String codfecha) {
        this.codfecha = codfecha;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfecha != null ? codfecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fecha)) {
            return false;
        }
        Fecha other = (Fecha) object;
        if ((this.codfecha == null && other.codfecha != null) || (this.codfecha != null && !this.codfecha.equals(other.codfecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Fecha[ codfecha=" + codfecha + " ]";
    }
    
}
