/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import negocio.servicio;
/**
 *
 * @author FRANZ ROMERO
 */
public class Grabar extends org.apache.struts.action.Action {

    private servicio ser;

    public void setSer(servicio ser) {
        this.ser = ser;
    }
    
    
    private static final String SUCCESS = "success";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrabarF x=(GrabarF)form;
        request.getSession().setAttribute("msg", ser.grabar(x.getDni(),x.getNom(),x.getDir()));
        return mapping.findForward("Mensaje");
    }
}
