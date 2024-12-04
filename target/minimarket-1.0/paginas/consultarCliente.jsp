<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <div id="container_table">
        <form action="${pageContext.request.contextPath}/ServletControladorClientes" id="form_consulta_cliente" method="get">
            <h1 id="titulo_form">Consultar Cliente</h1>
            <div id="grupo_form_consulta">
                <label class="consulta_form_label" for="cedula">Cedula</label>
                <!-- parametros ocultos -->
                <input type="hidden" name="page" value="consultarCliente">
                <input type="hidden" id="accionConsultar" name="accion">
                <input type="text" id="cedula1" name="cedula" class="formulario__input_consulta" placeholder="Ingrese la cedula"/>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarOne')">Buscar</button>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarAll')">Buscar Todos</button>
            </div>
        </form>


        <!-- Lista de clientes -->
        <table id="clientes">
            <thead>
                <tr>
                    <th>Cedula</th>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>

                <c:choose>
                    <c:when test="${not empty clientes}">
                        <c:forEach var="clientes" items="${clientes}" >
                            <tr>
                                <td>${clientes.cedula}</td>
                                <td>${clientes.nombre} ${clientes.apellido}</td>
                                <td>${clientes.direccion}</td>
                                <td>${clientes.telefono}</td>
                                <td>${clientes.email}</td>
                                <td>
                                    <c:if test="${userType == 1}">
                                        <a href="${pageContext.request.contextPath}/ServletControladorClientes?page=consultarCliente&accion=editar&idCliente=${clientes.idCliente}" onclick="validate()">Editar</a>
                                        |
                                    </c:if>

                                    <a href="${pageContext.request.contextPath}/ServletControladorClientes?page=consultarCliente&accion=seleccionarEliminar&idCliente=${clientes.idCliente}" onclick="">Eliminar</a>

                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty clientes}">
                        <tr>
                            <td colspan="6"><h3>No se encontraron Clientes</h3></td>
                        </tr>
                    </c:when>
                </c:choose>
            </tbody>
        </table>
    </div>



    <c:if test="${modal == 'editar_cliente'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('editar_cliente', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${modal == 'eliminar_cliente'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('eliminar_cliente', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${mensaje == 'Cliente actualizado con Exito!'}">
        <script>
            alert("Cliente actualizado con exito");
        </script>
    </c:if>
    <c:if test="${mensaje == 'Cliente eliminado con Exito!'}">
        <script>
            alert("Cliente eliminado con exito");
        </script>
    </c:if>









    <!-- MODALES -->
    <div class="modal" id="editar_cliente">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ServletControladorClientes?page=consultarCliente&accion=actualizar&idCliente=${clienteEditar.idCliente}" class="formulario" id="formulario" method="post">
                <h1 id="titulo_form">Editar Cliente</h1>
                <!-- Grupo: Nombre -->
                <div class="formulario__grupo" id="grupo__nombre">
                    <input type="hidden" name="idCliente" value="${clienteEditar.idCliente}"/>
                    <label for="nombre" class="formulario__label">Nombres</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="nombre"
                            id="nombre"
                            value="${clienteEditar.nombre}"
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
                            value="${clienteEditar.apellido}"

                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El apellido tiene que ser de 4 a 16 digitos.
                    </p>
                </div>

                <!-- Grupo: cedula -->
                <div class="formulario__grupo" id="grupo__cedula">
                    <label for="cedula" class="formulario__label">C.C:</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="cedula"
                            id="cedula"
                            value="${clienteEditar.cedula}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        La cedula solo puede contener numeros (maximo 10).
                    </p>
                </div>

                <!-- Grupo: Correo Electronico -->
                <div class="formulario__grupo" id="grupo__email">
                    <label for="email" class="formulario__label">Email</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="email"
                            class="formulario__input"
                            name="email"
                            id="email"
                            value="${clienteEditar.email}"
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
                            value="${clienteEditar.telefono}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El telefono solo puede contener numeros y el maximo son 10 digitos.
                    </p>
                </div>

                <!-- Grupo: Direccion -->
                <div class="formulario__grupo" id="grupo__direccion">
                    <label for="direccion" class="formulario__label">Direccion</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="direccion"
                            id="direccion"
                            value="${clienteEditar.direccion}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        La direccion puede contener numeros, letras, puntos, comas, barras
                        laterales y numerales, maximo # de caracteres, 100.
                    </p>
                </div>

                <!-- 
                      Grupo: Compras 
                    <div class="formulario__grupo" id="grupo__compras">
                      <label for="compras" class="formulario__label"
                        >Nï¿½mero de Compras</label
                      >
                      <div class="formulario__grupo-input">
                        <input
                          type="text"
                          class="formulario__input"
                          name="compras"
                          id="compras"
                          placeholder="12"
                        />
                      </div>
                    </div>
                -->
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
                        <button type="button" id="btn_cerrarModalEditarCliente" class="formulario__btn" onclick="cerrarModal('editar_cliente', 'container_table')">Cerrar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="modal" id="eliminar_cliente">
        <div class="modal-content">
            <h2>Eliminar Cliente</h2>
            <p>¿Estas seguro de que desea eliminar al cliente: <span style="font-weight: bold; color: red">${clienteSeleccionarEliminar.nombre} ${clienteSeleccionarEliminar.apellido}?</span></p>
            <form action="${pageContext.request.contextPath}/ServletControladorClientes?" method="get" class="formulario" id="formulario">
                <input type="hidden" name="page" value="consultarCliente" />
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idCliente" value="${clienteSeleccionarEliminar.idCliente}" />
                <button type="submit" onclick="">Eliminar</button>
                <button type="button" id="btn_cerrarModalEliminarCliente" onclick="cerrarModal('eliminar_cliente', 'container_table')">Cerrar</button>
            </form>

        </div>
    </div>
</main>
