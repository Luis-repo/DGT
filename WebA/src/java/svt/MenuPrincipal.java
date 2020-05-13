/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svt;

import conexion.ConectarBBDD;
import constantes.Constantes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import java.io.IOException;
import java.util.*;
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
import org.apache.poi.ss.usermodel.CellType;
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
        ruta1 = "C:\\DGTWeb\\WebA\\" + ruta1;
        String ruta2= request.getParameter("fileName2");
        ruta2 = "C:\\DGTWeb\\WebA\\" + ruta2;
        
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
    //   LeerExcelGrande(ruta2,2);
        leerCSV(ruta2);
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
                Statement st_borra=null;
                ResultSet rs=null;
                Connection conex = ConectarBBDD.conectaMariaDB();
                st_borra= conex.createStatement();
                
                     String sqlQuery_borra = "delete from origen5;";
                     st_borra.execute(sqlQuery_borra);
                     st_borra.close();
                     sqlQuery1 = "insert into origen5 values(";
                     st= conex.createStatement();
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
                                             String quitaComillas = cell.toString().trim();
                                             String cadenaLimpia = quitaComillas.replaceAll("\'","");
                                            contenido = contenido  + "'"+ cadenaLimpia.trim() + "'" + ",";
                                            System.out.print(contenido);
                                        }
					
				}
				System.out.println();
                                if (cabecera==false){
                                  //  System.out.println("paraaa");
                                    String micontenido = contenido.substring(0,contenido.length()-1);
                                    
                                         sqlQueryT = sqlQuery1 + micontenido + (");");
                                         st.execute(sqlQueryT);                              
                                }else{
                                    cabecera=false;
                                }
                                
			}
                        if (rs!=null) rs.close();
                        if (st!=null) st.close();
		} catch (Exception e) {
			             System.out.println("ERROR -->" + e.getMessage());
		}
	}
    
    public static void LeerExcelGrande(String ruta,int numFichero){
            
            try{
                
                String sqlQuery1="",sqlQueryT="";
                Statement st=null;
                Statement st_borra=null;
                ResultSet rs=null;
                Connection conex = ConectarBBDD.conectaMariaDB();
                st_borra= conex.createStatement();
                       String sqlQuery_borra = "delete from destino55;";
                       st_borra.execute(sqlQuery_borra);
                       st_borra.close();
                       sqlQuery1 = "insert into destino55 values(";
                       st= conex.createStatement();
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
                        int numColums = 1;
                        String dat ="";
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
                                        if (cell.getCellType() == CellType.BLANK){
                                             dat = "";
                                        }else if (cell.getCellType() == CellType.NUMERIC){
                                            dat = String.valueOf(cell).trim();
                                            if (dat.contains("E")){
                                                dat=dat.substring(0, dat.indexOf("E"));
                                            }
                                        }else if (cell.getCellType() == CellType._NONE){
                                            dat = "";
                                        }else{
                                            dat = cell.getStringCellValue().trim();
                                        }
                                   
                                        if (cabecera==false){
                                            contenido = contenido  + "'"+ dat.toString().trim() + "'" + ",";
                                            System.out.println(dat);
                                        }else{
                                            numColums++;
                                        }
					
				}
                                
				System.out.println();
                                if (cabecera==false){
                                  //  System.out.println("paraaa");
                                    String micontenido = contenido.substring(0,contenido.length()-1);
                                    int faltanColumnas= 12 - (numColums - Constantes.TOTAL_CAMPOS);
                                    if (faltanColumnas>0){
                                        for (int m= 1; m <= faltanColumnas; m++) {
                                            sqlQueryT = sqlQuery1 + micontenido + "" + ",";
                                        }
                                        String contFinal = sqlQueryT.substring(0,sqlQueryT.length()-1); 
                                        sqlQueryT = contFinal + (");");
                                        st.execute(sqlQueryT);
                                        
                                    }else{
                                         sqlQueryT = sqlQuery1 + micontenido + (");");
                                         st.execute(sqlQueryT);
                                    }
                                         
                                                        
                                    
                                }else{
                                    cabecera=false;
                                }
                                
			}
                        if (rs!=null) rs.close();
                        if (st!=null) st.close();
		} catch (Exception e) {
			System.out.println("ERROR -->" + e.getMessage());
		}
	}
    
    
        public static void leerCSV(String ruta){
            BufferedReader br = null;
            String SEPARATOR=";";
             String contFinal="";
             String dato="";
      try {
           String sqlQuery1="",sqlQueryT="";
           Statement st=null;
           Statement st_borra=null;
          
          
           Connection conex = ConectarBBDD.conectaMariaDB();
           st_borra= conex.createStatement();
           String sqlQuery_borra = "delete from destino55;";
           st_borra.execute(sqlQuery_borra);
           st_borra.close();
           sqlQuery1 = "insert into destino55 values(";
           st= conex.createStatement();
         
            br =new BufferedReader(new FileReader(ruta));
            String line = br.readLine();
            int numLine = 0;
            while (null!=line) {
               String [] fields = line.split(SEPARATOR);
              // System.out.println(Arrays.toString(fields));
                numLine++;
                 if (numLine>1){
                     for (int i = 0; i < fields.length; i++) {
                         if (fields[i].contains("\'")){
                              dato= fields[i].replaceAll("\'", "");
                         }else{
                             dato= fields[i];
                         }
                       sqlQuery1 = sqlQuery1 + "'" + dato.trim() + ("'") + ",";
                    }
                    if (Constantes.TOTAL_CAMPOS_CSV > fields.length){
                        for (int i = fields.length; i < Constantes.TOTAL_CAMPOS_CSV; i++) {
                         sqlQuery1 = sqlQuery1 + "'" + "" + "'" + ",";   
                        }
                    } 
                     String micontenido = sqlQuery1.substring(0,sqlQuery1.length()-1); 
                     contFinal =  micontenido + ");";
                     System.out.println(contFinal);
                     st.execute(contFinal);
                 }
               line = br.readLine();
               sqlQuery1 = "insert into destino55 values(";
            }
         
            }catch (Exception e) {
                System.out.println(contFinal);
                System.out.println(e.getMessage());
            } 

            }
    }

    


