<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>struts 1</title>
       
        <html:base/>
    </head>
    <body style="background-color: white">
        
        <div class="container">
            <form action="enviar.do" name="enviar">
                <input class="form-control" type="text" name="x" maxlength="1"/>
                 <input class="form-control" type="number" name="n" maxlength="1"/>
                <input class="btn btn-primary" type="submit" value="enviar"/>
            </form>
        </div>
        
    </body>
</html:html>
