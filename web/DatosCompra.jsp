<%-- 
    Document   : DatosCompra
    Created on : 12/05/2018, 03:50:47 AM
    Author     : Daniel Mauricio
--%>

<%@page import="Persistencia.Zona"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>

    <head>
        <title>FPF</title>
        <style type="text/css">
            #contenedor{
                padding: 10px;
            }            
            #izquierda{
                float:left;
                padding: 10px;
                margin: 40px;
            }
            #derecha{
                float:left;
                padding: 10px;
                margin: 40px;
            }
            #publicidad{
                float:left;
                padding: 10px;
                margin: 40px;
            }
        </style>   

    </head>
    <link rel="stylesheet" href="main.css" />
    <body>
    <center>
        <section id="banner">
            <div class="inner">
                <br/>
                <br/>
                <br/>
                <br/>
                <h1>Partido Amistoso</h1>
                <p>Peru vs Escocia 8:00 pm</p>


                <div id="contenedor" >
                    <div id="izquierda">
                        <table>
                            <tr>
                                <td> 
                                    <%
                                        List<Zona> listZonas = (List) session.getAttribute("lst_zonas");
                                    %>
                                    <select  class="label"name="tribuna">
                                        <option value="">Seleccione tribuna preferida</option>
                                        <%
                                            for (int i = 0; i < listZonas.size(); i++) {
                                                Zona zz = listZonas.get(i);
                                                %>
                                                    <option value="<%= zz.getCodzona() %>"><%= zz.getNomzona() %> - S/.<%= zz.getPrecio() %></option>
                                                <%
                                            }
                                        %>
                                        <option value="sur">Sur 99 Soles</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select  class="label"name="tribuna1">
                                        <option value="">Seleccione Cantidad de Entradas</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option> 

                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>   
                    <div id="derecha">
                        <table>
                            <tr>
                                <td>
                                    <image src="Galeria/estadio.jpg" width="350" height="250"/>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div id="publicidad">
                        <table>
                            <tr>

                                <html:form action="/Pago">
                                    <html:submit value="CONTINUAR" property="pag"/>
                                </html:form>

                            </tr>
                        </table>
                    </div>
                </div>

            </div>
            <center><video autoplay loop muted playsinline src="Galeria/videoplayback.mp4"></video></center>


            <br> 
            <br/>
            <br/>
            <br/>


        </section>



    </center>


</body>
</html>