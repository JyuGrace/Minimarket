<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <div id="container_table">
        <form action="${pageContext.request.contextPath}/ServletControladorProveedores" id="form_consulta_proveedor" method="get">
            <h1 id="titulo_form">Consultar Proveedor</h1>
            <div id="grupo_form_consulta">
                <label class="consulta_form_label" for="Nombre">Nombre</label>
                <!-- parametros ocultos -->
                <input type="hidden" name="page" value="consultarProveedor">
                <input type="hidden" id="accionConsultar" name="accion">
                <input type="text" id="nombre" name="nombre" class="formulario__input_consulta" placeholder="Ingrese el nombre"/>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarOne')">Buscar</button>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarAll')">Buscar Todos</button>
            </div>
        </form>


        <!-- Lista de proveedores -->
        <table id="proveedores">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Nombre Empresa</th>
                    <th>Telefono</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>

                <c:choose>
                    <c:when test="${not empty proveedores}">
                        <c:forEach var="proveedores" items="${proveedores}" >
                            <tr>
                                <td>${proveedores.nombre} ${proveedores.apellido}</td>
                                <td>${proveedores.nombreEmpresa}</td>
                                <td>${proveedores.telefono}</td>
                                <td>${proveedores.email}</td>
                                <td>
                                    <c:if test="${userType == 1}">
                                        <a href="${pageContext.request.contextPath}/ServletControladorProveedores?page=consultarProveedor&accion=editar&idProveedor=${proveedores.idProveedor}" onclick="">Editar</a>
                                        |
                                    </c:if>
                                    <a href="${pageContext.request.contextPath}/ServletControladorProveedores?page=consultarProveedor&accion=seleccionarEliminar&idProveedor=${proveedores.idProveedor}" onclick="">Eliminar</a>

                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty proveedores}">
                        <tr>
                            <td colspan="6"><h3>No se encontraron Proveedores</h3></td>
                        </tr>
                    </c:when>
                </c:choose>
            </tbody>
        </table>
    </div>



    <c:if test="${modal == 'editar_proveedor'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('editar_proveedor', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${modal == 'eliminar_proveedor'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('eliminar_proveedor', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${mensaje == 'Proveedor actualizado con Exito!'}">
        <script>
            alert("Proveedor actualizado con exito");
        </script>
    </c:if>
    <c:if test="${mensaje == 'Proveedor eliminado con Exito!'}">
        <script>
            alert("Proveedor eliminado con exito");
        </script>
    </c:if>


    <!-- MODALES -->
    <div class="modal" id="editar_proveedor">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ServletControladorProveedores?page=consultarProveedor&accion=actualizar&idProveedor=${proveedorEditar.idProveedor}" class="formulario" id="formulario" method="post">
                <h1 id="titulo_form">Editar Proveedor</h1>
                <!-- Grupo: Nombre -->
                <div class="formulario__grupo" id="grupo__nombre">
                    <input type="hidden" name="idProveedor" value="${proveedorEditar.idProveedor}"/>
                    <label for="nombre" class="formulario__label">Nombres</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="nombre"
                            id="nombre"
                            value="${proveedorEditar.nombre}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El nombre tiene que ser de 4 a 16 digitos.
                    </p>
                </div>

                <!-- Grupo: Apellido -->
                <div class="formulario__grupo" id="grupo__apellido">
                    <label for="apellido" class="formulario__label">Apellidos</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="apellido"
                            id="apellido"
                            value="${proveedorEditar.apellido}"

                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El apellido tiene que ser de 4 a 16 digitos.
                    </p>
                </div>

                <!-- Grupo: nombre empresa -->
                <div class="formulario__grupo" id="grupo__nombre_empresa">
                    <label for="nombre_empresa" class="formulario__label">Nombre Empresa</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="nombre_empresa"
                            id="nombre_empresa"
                            value="${proveedorEditar.nombreEmpresa}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        La cedula solo puede contener numeros (maximo 10).
                    </p>
                </div>

                <!-- Grupo: Correo Electronico -->
                <div class="formulario__grupo" id="grupo__email">
                    <label for="email" class="formulario__label">Correo Electronico</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="email"
                            class="formulario__input"
                            name="email"
                            id="email"
                            value="${proveedorEditar.email}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El correo solo puede contener letras, numeros, puntos, guiones y
                        guion bajo.
                    </p>
                </div>

                <!-- Grupo: Telefono -->
                <div class="formulario__grupo" id="grupo__telefono">
                    <label for="telefono" class="formulario__label">Telefono</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="telefono"
                            id="telefono"
                            value="${proveedorEditar.telefono}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El telefono solo puede contener numeros y el maximo son 10 dï¿½gitos.
                    </p>
                </div>

                <div class="formulario__mensaje" id="formulario__mensaje">
                    <p>
                        <i class="fas fa-exclamation-triangle"></i> <b>Error:</b> Por favor
                        rellena el formulario correctamente.
                    </p>
                </div>

                <div id="container_botones_consulta">
                    <div class="formulario__grupo formulario__grupo-btn-enviar">
                        <button type="submit" class="formulario__btn">Actualizar</button>
                        <p class="formulario__mensaje-exito" id="formulario__mensaje-exito">
                            Formulario enviado exitosamente!
                        </p>
                    </div>
                    <div class="formulario__grupo formulario__grupo-btn-enviar">
                        <button type="button" id="btn_cerrarModalEditarCliente" class="formulario__btn" onclick="cerrarModal('editar_proveedor', 'container_table')">Cerrar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="modal" id="eliminar_proveedor">
        <div class="modal-content">
            <h2>Eliminar Proveedor</h2>
            <p>¿Estas seguro de que desea eliminar al proveedor: <span style="font-weight: bold; color: red">${proveedorSeleccionarEliminar.nombre} ${proveedorSeleccionarEliminar.apellido}?</span></p>
            <form action="${pageContext.request.contextPath}/ServletControladorProveedores?" method="get" class="formulario" id="formulario">
                <input type="hidden" name="page" value="consultarProveedor" />
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idProveedor" value="${proveedorSeleccionarEliminar.idProveedor}" />
                <button type="submit" onclick="">Eliminar</button>
                <button type="button" id="btn_cerrarModalEliminarCliente" onclick="cerrarModal('eliminar_proveedor', 'container_table')">Cerrar</button>
            </form>

        </div>
    </div>
</main>
