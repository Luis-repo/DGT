<%-- 
    Document   : menuPrincipal
    Created on : 31-mar-2020, 17:48:46
    Author     : Luis Muñoz
--%>

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
        
        <h1>Estamos con los ficheros</h1>
        <%=f11%>
        <h3><%=f22%></h3>
         <h3><%=mes%></h3>
         <h1>fichero de analisis <%=f11%> sobre fichero <%=f22%> del mes <%=mes%></h1>
          <h1>Inicialmente se genera un fichero con datos Asignados y otro de datos no asignados</h1>
    </body>
</html>
