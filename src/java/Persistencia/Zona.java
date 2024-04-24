/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FRANZ ROMERO
 */
@Entity
@Table(name = "ZONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z")
    , @NamedQuery(name = "Zona.findByCodzona", query = "SELECT z FROM Zona z WHERE z.codzona = :codzona")
    , @NamedQuery(name = "Zona.findByNomzona", query = "SELECT z FROM Zona z WHERE z.nomzona = :nomzona")
    , @NamedQuery(name = "Zona.findByCatidad", query = "SELECT z FROM Zona z WHERE z.catidad = :catidad")
    , @NamedQuery(name = "Zona.findByPrecio", query = "SELECT z FROM Zona z WHERE z.precio = :precio")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODZONA")
    private String codzona;
    @Basic(optional = false)
    @Column(name = "NOMZONA")
    private String nomzona;
    @Basic(optional = false)
    @Column(name = "CATIDAD")
    private int catidad;
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codzona")
    private List<Boleto> boletoList;

    public Zona() {
    }

    public Zona(String codzona) {
        this.codzona = codzona;
    }

    public Zona(String codzona, String nomzona, int catidad, double precio) {
        this.codzona = codzona;
        this.nomzona = nomzona;
        this.catidad = catidad;
        this.precio = precio;
    }

    public String getCodzona() {
        return codzona;
    }

    public void setCodzona(String codzona) {
        this.codzona = codzona;
    }

    public String getNomzona() {
        return nomzona;
    }

    public void setNomzona(String nomzona) {
        this.nomzona = nomzona;
    }

    public int getCatidad() {
        return catidad;
    }

    public void setCatidad(int catidad) {
        this.catidad = catidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codzona != null ? codzona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.codzona == null && other.codzona != null) || (this.codzona != null && !this.codzona.equals(other.codzona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Zona[ codzona=" + codzona + " ]";
    }
    
}
