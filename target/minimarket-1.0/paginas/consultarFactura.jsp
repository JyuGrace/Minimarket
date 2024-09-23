<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CO"></fmt:setLocale>

    <main>
        <div id="container_table">
            <form action="${pageContext.request.contextPath}/ServletControladorFacturas" id="form_consulta_producto" method="get">
            <h1 id="titulo_form">Consultar Facturas</h1>
            <div id="grupo_form_consulta">
                <label for="nombre">Fecha:</label>
                <!-- parametros ocultos -->
                <input type="hidden" name="page" value="consultarFactura">
                <input type="hidden" id="accionConsultar" name="accion" >
                
                <input type="date" id="fecha" name="fecha" class="formulario__input_consulta" >
                
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarOne')" >Buscar</button>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarAll')" >Buscar Todos</button>
            </div>
        </form>
        <table id="facturas">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Fecha</th>
                    <th>Empleado</th>
                    <th>Cliente (C.C)</th>
                    <th>Total</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty consultarFacturas}">
                        <c:forEach var="facturas" items="${consultarFacturas}">
                            <tr>
                                <td>${facturas.idFactura}</td>
                                <td>${facturas.fecha}</td>
                                <td>${facturas.nombreEmpleado} ${facturas.apellidoEmpleado}</td>
                                <td>${facturas.cedulaCliente}</td>
                                <td><fmt:formatNumber value="${facturas.total}" type="currency"/></td>
                                
                                <td>
                                    <a href="${pageContext.request.contextPath}/ServletControladorFacturas?page=consultarFactura&accion=verFactura&idFactura=${facturas.idFactura}" target="_blank">Ver</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty consultarFacturas}">
                    <td colspan="6"><h3>No se encontraron Facturas</h3></td>
                </c:when>
            </c:choose>

            </tbody>
        </table>
    </div>

   


</main>
