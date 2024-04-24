/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author FRANZ ROMERO
 */
public class IngresoF extends org.apache.struts.action.ActionForm {
    
    private String nuevDNI;

    public String getNuevDNI() {
        return nuevDNI;
    }

    public void setNuevDNI(String nuevDNI) {
        this.nuevDNI = nuevDNI;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getNuevDNI() == null || getNuevDNI().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
