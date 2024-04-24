/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import Persistencia.Zona;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.servicio;
import negocio.servicioIMP;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author FRANZ ROMERO
 */
public class Ingresar extends org.apache.struts.action.Action {

    servicio serv = new servicioIMP();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().setAttribute("msg", "detalle");
        
        /*PARA OBTENER DATOS DNI*/
        IngresoF x =(IngresoF)form;
        System.out.println("El dni ingresado es: "+x.getNuevDNI());
        
        
        /*PARA LISTAR ZONAS*/
        List<Zona> listZona = serv.listarZonas();        
        request.getSession().setAttribute("lst_zonas", listZona);
        

        return mapping.findForward("DatosCompra");
        /*NO OLVIDAR IMPORTAR LA LIBRERIA DERBY*/
    }
}
