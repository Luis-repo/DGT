<%-- 
    Document   : menuPrincipal
    Created on : 31-mar-2020, 17:48:46
    Author     : Luis Muñoz
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="conexion.ConectarBBDD"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
    <%
          File f11 = (File) request.getAttribute("f1");
          File f22 = (File) request.getAttribute("f2");
          String mes = (String) request.getAttribute("mes");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ficheros</title>
    </head>
    <body>
        
        <h1>Analisis de Ficheros</h1>
        <h3>Origen :  <%=f11%></h3>
        <h3>Destino : <%=f22%></h3>
        <h3>Periodo : <%=mes%></h3>
        <h1>fichero de analisis <%=f11%> sobre fichero <%=f22%> del mes <%=mes%></h1>
        <h1>Inicialmente se genera un fichero con datos Asignados y otro de datos no asignados</h1>
        <%
           //  String sql = "select make,model,liters,fuel,seating_cap,gvw_rating from origen5;";
             String sql = "select * from origen5;";
             
             String queryDestino="";
             String ruta= "C:\\DGTWeb\\WebA\\errores.txt";
             Connection conex = ConectarBBDD.conectaMariaDB();
             Statement  st= conex.createStatement();
             ResultSet rs = st.executeQuery(sql);
             
             PreparedStatement stDestino = conex.prepareStatement(
                "select * from destino55 where "
                        + "make = ? and model = ? and liters=? and "
                        + "fuel=? and seating_cap=? and gvw_rating=?"
             );
             ResultSet rsDestino =null;
             File fErrores = new File(ruta);
             if (fErrores.exists()){
                 fErrores.delete();
             }else{
                 fErrores.createNewFile();
             }
             FileWriter fw=new FileWriter(fErrores);
             PrintWriter pw = new PrintWriter(fw);
             String combustible="";
             while(rs.next()){
                 stDestino.setString(1,rs.getString("make"));
                 stDestino.setString(2,rs.getString("make")+":"+rs.getString("model"));
                 stDestino.setString(3,rs.getString("liters"));
                 if(rs.getString("fuel").equalsIgnoreCase("gasoline")){
                      combustible="P";
                 }
                 stDestino.setString(4,rs.getString("fuel"));
                 stDestino.setString(5,rs.getString("seating_cap"));
                 stDestino.setString(6,rs.getString("gvw_rating"));
                 rsDestino=stDestino.executeQuery();
                 if (rsDestino.getRow()==0){
                     pw.println(rs.getString("make") + "," + rs.getString("model") + "," +
                             rs.getString("liters") + "," + combustible + "," +
                             rs.getString("seating_cap") + "," + rs.getString("gvw_rating"));
                 }else{
          %>
           <h5><%=rs.getString("make") %> || <%=rs.getString("model") %> || <%=rs.getString("liters") %></h5>
          <% 
                 }
             }
             pw.close();
             fw.close();
             rs.close();
             st.close();
             
         %>
    </body>
</html>
