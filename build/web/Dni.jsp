<%-- 
    Document   : Dni
    Created on : 12/05/2018, 03:30:44 AM
    Author     : Daniel Mauricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <title>FPF</title>
        <link rel="stylesheet" href="main.css" />

    </head>
    <body>

        <section id="banner">
            <div class="inner">
                <h1>Partido Amistoso</h1>
                <p>Peru vs Escocia 8:00 pm</p>
                <center><h2> Ingrese DNI<input class="ella" type="text" name="dni" ></h2> </center>

                <html:form action="/Ingresar">
                    <h2>  <html:submit value="CONTINUAR" property="nuevDNI"/></h2> 
                </html:form>

                <br>
            </div>
            <video autoplay loop muted playsinline src="Galeria/videoplayback.mp4"></video>
        </section>
    </body>
</html>