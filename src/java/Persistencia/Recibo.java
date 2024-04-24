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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RECIBO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r")
    , @NamedQuery(name = "Recibo.findByCodrecibo", query = "SELECT r FROM Recibo r WHERE r.codrecibo = :codrecibo")
    , @NamedQuery(name = "Recibo.findByCodpago", query = "SELECT r FROM Recibo r WHERE r.codpago = :codpago")
    , @NamedQuery(name = "Recibo.findByFecha", query = "SELECT r FROM Recibo r WHERE r.fecha = :fecha")})
public class Recibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODRECIBO")
    private String codrecibo;
    @Basic(optional = false)
    @Column(name = "CODPAGO")
    private String codpago;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "DNI", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Cliente dni;

    public Recibo() {
    }

    public Recibo(String codrecibo) {
        this.codrecibo = codrecibo;
    }

    public Recibo(String codrecibo, String codpago, Date fecha) {
        this.codrecibo = codrecibo;
        this.codpago = codpago;
        this.fecha = fecha;
    }

    public String getCodrecibo() {
        return codrecibo;
    }

    public void setCodrecibo(String codrecibo) {
        this.codrecibo = codrecibo;
    }

    public String getCodpago() {
        return codpago;
    }

    public void setCodpago(String codpago) {
        this.codpago = codpago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getDni() {
        return dni;
    }

    public void setDni(Cliente dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codrecibo != null ? codrecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ((this.codrecibo == null && other.codrecibo != null) || (this.codrecibo != null && !this.codrecibo.equals(other.codrecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Recibo[ codrecibo=" + codrecibo + " ]";
    }
    
}
