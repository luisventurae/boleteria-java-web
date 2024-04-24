/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import Persistencia.Cliente;

/**
 *
 * @author FRANZ ROMERO
 */
public interface servicio {

    ////cliente
    public String grabar(String dni, String nom, String dir);

    public Cliente buscar(String dni);

    public String actualizar(String dni, String nom, String dir);

    public String eliminar(String dni);

    public List listarCliente();
    
    public List listarZonas();
}
