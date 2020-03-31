/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class MenuPrincipal extends HttpServlet {

       // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getParameter("f1"));
        System.out.println(request.getParameter("f2"));
        String ruta1= request.getParameter("f1");
        String ruta2= request.getParameter("f2");
        
        File fichero1 = new File(ruta1);
        File fichero2 = new File(ruta2);
        if (fichero1.exists()){
            
            if (fichero2.exists()){
                
            }else{
                System.out.println("fichero2 no existe");
            }
        }else {
             System.out.println("fichero1 no existe");
        }
        // sigue si los dos ficheros existen
        
        request.setAttribute("f1", fichero1);
        request.setAttribute("f2", fichero2);
        RequestDispatcher rd=request.getRequestDispatcher("/jsp/menuPrincipal.jsp");  
        rd.forward(request, response);  
        
        
       
    }

    

}
