<%-- 
    Document   : Mensaje
    Created on : 15/05/2018, 04:03:34 PM
    Author     : FRANZ ROMERO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><center><h1>Gracias Usted ha sido registrado:</h1>
        <h1>
            <%=(String)session.getAttribute("msg") %>
                    
        </h1>
            <a href="Paciente.jsp">Comprar entradas</a>
    </center>
        
    </body>
</html>