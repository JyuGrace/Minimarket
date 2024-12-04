<%@page import="dominio.Producto"%>
<%@page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


        <!-- Ícono de FontAwesome en la esquina inferior izquierda -->
        <div id="icono" class="icono-bajo-stock" onclick="toggleDiv()">
            <i class="fa-solid fa-store-slash"></i> <!-- Ícono de productos de FontAwesome -->
        </div>

        <!-- Div que contiene la lista de productos, inicialmente oculto -->
        <div id="listaProductos" class="lista-productos" style="display: none;">
            <ul id="productosSinStock">
                <!-- Usamos JSTL para obtener la lista de productos desde la sesión -->
                <c:choose>
                    <c:when test="${not empty sessionScope.productosLowStock}">
                        <c:forEach var="producto" items="${sessionScope.productosLowStock}">
                            <li><span style="font-weight: bold">${producto.nombre}:</span> ${producto.stock} uds.</li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li>No hay productos con bajo Stock.</li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>


        <footer></footer>


        <script src="${pageContext.request.contextPath}/recursos/js/script.js"></script>
        <script src="recursos/js/funciones.js"></script>
        <script src="https://kit.fontawesome.com/bdf75dec3e.js" crossorigin="anonymous"></script>

    </body>
</html>
