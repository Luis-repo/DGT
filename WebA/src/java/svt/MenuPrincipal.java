/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svt;

import conexion.ConectarBBDD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;


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
        System.out.println(request.getParameter("fileName1"));
        System.out.println(request.getParameter("fileName2"));
        String ruta1= request.getParameter("fileName1");
        String ruta2= request.getParameter("fileName2");
        
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
        LeerExcel(ruta1,1);
        System.out.println("*****************");
        LeerExcel(ruta2,2);
        System.out.println("*****************");
        request.setAttribute("f1", fichero1);
        request.setAttribute("f2", fichero2);
        RequestDispatcher rd=request.getRequestDispatcher("/jsp/menuPrincipal.jsp");  
        rd.forward(request, response);  
        
        
       
    }
    
    public static void LeerExcel(String ruta,int numFichero){
            try{
                String sqlQuery1="",sqlQueryT="";
                
                Statement st=null;
                ResultSet rs=null;
                Connection conex = ConectarBBDD.conectaMariaDB();
                if (numFichero == 1){
                     sqlQuery1 = "insert into tabla_origen values(";
                     st= conex.createStatement();
                     
                }else{
                     // String sqlQuery2 = "select * from tabla_origen";
                     // PreparedStatement st= conex.prepareStatement(sqlQuery2);
                }
                
                
		String rutaArchivo = ruta;
		String hoja = "Hoja1";
 
		
                       
                       FileInputStream file = new FileInputStream(new File(rutaArchivo));
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
                        boolean cabecera = true;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
                                String contenido="";
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
                                        if (cabecera==false){
                                            contenido = contenido  + "'"+ cell.toString() + "'" + ",";
                                            System.out.print(contenido);
                                        }
					
				}
				System.out.println();
                                if (cabecera==false){
                                    
                                    contenido = contenido.substring(0,contenido.length()-1);
                                    if (numFichero == 1){
                                         sqlQueryT = sqlQuery1 + contenido + (");");
                                         rs = st.executeQuery(sqlQueryT);
                                    }else{
                                          sqlQueryT = "";
                                    }                           
                                    
                                }else{
                                    cabecera=false;
                                }
                                
			}
                        if (rs!=null) rs.close();
                        if (st!=null) st.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
    }

    


