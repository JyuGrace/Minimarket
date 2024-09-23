<nav>
    <div class="right-items">
        <a href="${pageContext.request.contextPath}/app.jsp">Beto&Slendy</a>
    </div>
    <div class="left-items">
        <ul>
            <li><a href="${pageContext.request.contextPath}/app.jsp">Home</a></li>
            <li>
                <a href="">Registros</a>
                <ul>
                    <li><a href="app.jsp?page=registrarCliente">Clientes</a></li>
                    <li><a href="app.jsp?page=registrarProducto">Productos</a></li>
                    <li><a href="app.jsp?page=registrarProveedor">Proveedores</a></li>
                    <li><a href="app.jsp?page=registrarEmpleado">Empleado</a></li>
                </ul>
            </li>
            <li>
                <a href="">Consultas</a>
                <ul class="dropdown">
                    <li><a href="app.jsp?page=consultarCliente">Clientes</a></li>
                    <li><a href="app.jsp?page=consultarProducto">Productos</a></li>
                    <li><a href="app.jsp?page=consultarProveedor">Proveedores</a></li>
                    <li><a href="app.jsp?page=consultarEmpleado">Empleados</a></li>
                    <li><a href="app.jsp?page=consultarFactura">Facturas</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/ServletControladorVentas?page=ventas">Venta</a></li>
            <li id="login"><a href="${pageContext.request.contextPath}/ServletControladorLogOut">Log Out</a></li>
        </ul>
    </div>
</nav>
