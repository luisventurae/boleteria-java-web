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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FRANZ ROMERO
 */
@Entity
@Table(name = "BOLETO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b")
    , @NamedQuery(name = "Boleto.findByCodboleto", query = "SELECT b FROM Boleto b WHERE b.codboleto = :codboleto")
    , @NamedQuery(name = "Boleto.findByNombres", query = "SELECT b FROM Boleto b WHERE b.nombres = :nombres")})
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODBOLETO")
    private String codboleto;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @JoinColumn(name = "DNI", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Cliente dni;
    @JoinColumn(name = "CODZONA", referencedColumnName = "CODZONA")
    @ManyToOne(optional = false)
    private Zona codzona;

    public Boleto() {
    }

    public Boleto(String codboleto) {
        this.codboleto = codboleto;
    }

    public Boleto(String codboleto, String nombres) {
        this.codboleto = codboleto;
        this.nombres = nombres;
    }

    public String getCodboleto() {
        return codboleto;
    }

    public void setCodboleto(String codboleto) {
        this.codboleto = codboleto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Cliente getDni() {
        return dni;
    }

    public void setDni(Cliente dni) {
        this.dni = dni;
    }

    public Zona getCodzona() {
        return codzona;
    }

    public void setCodzona(Zona codzona) {
        this.codzona = codzona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codboleto != null ? codboleto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.codboleto == null && other.codboleto != null) || (this.codboleto != null && !this.codboleto.equals(other.codboleto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Boleto[ codboleto=" + codboleto + " ]";
    }
    
}
