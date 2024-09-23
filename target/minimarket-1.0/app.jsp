<%@ page import="javax.servlet.http.HttpSession" %>
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="recursos/css/styles3.css" />
        <title>Beto&Slendy</title>
    </head>
    <body id="fondo_index">

        <!-- Nav Bar -->
        <jsp:include page="paginas/navigation.jsp"/>

        <!-- Desplegar contenido de la pagina -->

        <jsp:include page="<%= request.getParameter("page") != null ? "paginas/" + request.getParameter("page") + ".jsp" : "paginas/mensajeBienvenida.jsp"%>"/>


        <footer></footer>
        
        
        <script src="${pageContext.request.contextPath}/recursos/js/script.js"></script>
        <script src="recursos/js/funciones.js"></script>
        <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
    </body>
</html>
