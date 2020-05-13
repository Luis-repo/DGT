/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svt;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author admin
 */
public class EnviarBean extends org.apache.struts.action.ActionForm {
    
    private String x;
    
    private int n;

    /**
     * @return
     */
    public String getX() {
        return x;
    }

    /**
     * @param string
     */
    public void setX(String string) {
        x = string;
    }

    /**
     * @return
     */
    public int getN() {
        return n;
    }

    /**
     * @param i
     */
    public void setN(int i) {
        n = i;
    }

    /**
     *
     */
    public EnviarBean() {
        super();
        // TODO Auto-generated constructor stub
    }
}
