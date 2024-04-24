<%-- 
    Document   : Tarjeta
    Created on : 12/05/2018, 08:13:22 PM
    Author     : Daniel Mauricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>FPF</title>
		
	</head>
        <link rel="stylesheet" href="main.css" />
        <body>

            <section id="banner">
                <div class="inner">
                    <h1>Partido Amistoso</h1>
                    <p>Peru vs Escocia 8:00 pm</p>
                    <h2>Nombre de Tarjeta <input type="dni" name="nomtar" ></h2>
                    <h2>Numero de Tarjeta <input type="dni" name="numtar" ></h2>
                    <h2>Fecha de Vencimeinto <input type="dni" name="ven" ></h2>
                    <br>
                    
                    <html:form action="/Tarjeta">
                        <h2>  <html:submit value="CONTINUAR" property="tar"/></h2> 
                        </html:form>           
                    
                    
                </div>
                
                     
                
                    
        <video autoplay loop muted playsinline src="Galeria/videoplayback.mp4"></video>
			  
            </section>
		
			

			

		

	</body>
</html>
