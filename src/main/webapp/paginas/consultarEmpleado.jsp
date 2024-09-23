<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CO"></fmt:setLocale>

    <main>
        <div id="container_table">
            <form action="${pageContext.request.contextPath}/ServletControladorEmpleados" id="form_consulta_empleado" method="get">
            <h1 id="titulo_form">Consultar Empleados</h1>
            <div id="grupo_form_consulta">
                <label for="nombre"><span style="font-weight: bold">Nombre</span></label>
                <!-- parametros ocultos -->
                <input type="hidden" name="page" value="consultarEmpleado">
                <input type="hidden" id="accionConsultar" name="accion" >
                <input type="text" id="nombre" name="nombre" class="formulario__input_consulta" placeholder="nombre del empleado">
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarOne')" >Buscar</button>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarAll')" >Buscar Todos</button>
            </div>
        </form>
        <table id="empleados">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Cedula</th>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Direccion</th>
                    <th>Tipo</th>
                    <th>Fecha de Registro</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty empleados}">
                        <c:forEach var="empleados" items="${empleados}">
                            <tr>
                                <td>${empleados.nombre} ${empleados.apellido}</td>
                                <td>${empleados.cedula}</td>
                                <td>${empleados.usuario}</td>
                                <td>${empleados.password}</td>
                                <td>${empleados.email}</td>
                                <td>${empleados.telefono}</td>
                                <td>${empleados.direccion}</td>
                                <td>${empleados.tipoEmpleado}</td>
                                <td>${empleados.fechaRegistro}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ServletControladorEmpleados?page=consultarEmpleado&accion=seleccionarEditar&idEmpleado=${empleados.idEmpleado}" onclick="">Editar</a>
                                    |
                                    <a href="${pageContext.request.contextPath}/ServletControladorEmpleados?page=consultarEmpleado&accion=seleccionarEliminar&idEmpleado=${empleados.idEmpleado}" onclick="">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty empleados}">
                    <td colspan="10"><h3>No se encontraron Empleados</h3></td>
                </c:when>
            </c:choose>

            </tbody>
        </table>
    </div>


    <!-- modal -->
    <c:if test="${modal == 'editar_empleado'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('editar_empleado', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${modal == 'eliminar_empleado'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('eliminar_empleado', 'container_table');
            });
        </script>
    </c:if>
    <!-- mensaje -->
    <c:if test="${mensaje == 'actualizado'}">
        <script>
            alert("Empleado actualizado con exito");
        </script>
    </c:if>
    <c:if test="${mensaje == 'eliminado'}">
        <script>
            alert("Empleado eliminado con exito");
        </script>
    </c:if>


    <!-- MODALES -->
    <div class="modal" id="editar_empleado">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ServletControladorEmpleados?page=consultarEmpleado&accion=actualizar&idEmpleado=${empleadoEditar.idEmpleado}" class="formulario" id="formulario" method="post">
                <h1 id="titulo_form">Editar Empleado</h1>

                <!-- Grupo: Nombre -->
                <div class="formulario__grupo" id="grupo__nombre">
                    <!-- Grupo: Campo Oculto -->
                    <input type="hidden" name="fechaRegistro" value="${empleadoEditar.fechaRegistro}"/>

                    <label for="nombre" class="formulario__label">Nombre</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="nombre"
                            id="nombre"
                            value="${empleadoEditar.nombre}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El nombre tiene que ser de 4 a 16 digitos.
                    </p>
                </div>

                <!-- Grupo: apellido -->
                <div class="formulario__grupo" id="grupo__apellido">
                    <label for="apellido" class="formulario__label">Apellido</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="apellido"
                            id="apellido"
                            value="${empleadoEditar.apellido}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">El apellido tiene que ser de 4 a 16 dígitos.</p>
                </div>

                <!-- Grupo: cedula -->
                <div class="formulario__grupo" id="grupo__cedula">
                    <label for="cedula" class="formulario__label">C.C</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="cedula"
                            id="cedula"
                            value="${empleadoEditar.cedula}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">La cedula solo puede contener numeros (maximo 10).</p>
                </div>

                <!-- Grupo: usuario -->
                <div class="formulario__grupo" id="grupo__usuario">
                    <label for="usuario" class="formulario__label">Usuario</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="usuario"
                            id="usuario"
                            value="${empleadoEditar.usuario}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">El usuario tiene que ser de 4 a 16 dígitos y solo puede contener
                        numeros, letras y guion bajo.</p>
                </div>

                <!-- Grupo: password -->
                <div class="formulario__grupo" id="grupo__password">
                    <label for="usuario" class="formulario__label">Contraseña</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="password"
                            id="password"
                            value="${empleadoEditar.password}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">La contraseña tiene que ser de 4 a 12 dígitos.</p>
                </div>

                <!-- Grupo: tipoEmpleado -->
                <div class="formulario__grupo" id="grupo__tipoEmpleado">
                    <label for="tipoEmpleado" class="formulario__label">Tipo de Empleado</label>
                    <div class="formulario__grupo-input">
                        <select class="formulario__input" name="tipoEmpleado" id="producto_tipoEmpleado" required="true">
                            <option value="">--Seleccionar--</option>
                            <option value="1" ${empleadoEditar.tipoEmpleado == 1 ? 'selected' : ''}>Administrador</option>
                            <option value="2" ${empleadoEditar.tipoEmpleado == 2 ? 'selected' : ''}>Facturador</option>
                        </select>
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">Seleccione un tipo de Empleado.</p>
                </div>

                <!-- Grupo: email -->
                <div class="formulario__grupo" id="grupo__email">
                    <label for="usuario" class="formulario__label">Email</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="email"
                            id="email"
                            value="${empleadoEditar.email}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">El correo solo puede contener letras, numeros, puntos, guiones y
                        guion bajo.</p>
                </div>

                <!-- Grupo: telefono -->
                <div class="formulario__grupo" id="grupo__telefono">
                    <label for="telefono" class="formulario__label">Telefono</label>
                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="telefono" id="telefono" value="${empleadoEditar.telefono}" />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">El telefono solo puede contener numeros y el maximo son 10 dígitos.</p>
                </div>

                <!-- Grupo: direccion -->
                <div class="formulario__grupo" id="grupo__direccion">
                    <label for="direccion" class="formulario__label">Direccion</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="direccion"
                            id="direccion"
                            value="${empleadoEditar.direccion}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">La direccion puede contener numeros, letras, puntos, comas, barras
                        laterales y numerales, maximo # de caracteres, 100.</p>
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
                        <button type="button" id="btn_cerrarModalEditarEmpleado" class="formulario__btn" onclick="cerrarModal('editar_empleado', 'container_table')">Cerrar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="modal" id="eliminar_empleado">
        <div class="modal-content">
            <h2>Eliminar Empleado</h2>
            <p>¿Estas seguro de que desea eliminar el Empleado: <span style="font-weight: bold; color: red">${empleadoSeleccionarEliminar.nombre} ${empleadoSeleccionarEliminar.apellido}</span></p>
            <form action="${pageContext.request.contextPath}/ServletControladorEmpleados" method="get" class="formulario" id="formulario">
                <input type="hidden" name="page" value="consultarEmpleado" />
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idEmpleado" value="${empleadoSeleccionarEliminar.idEmpleado}" />
                <button type="submit" onclick="">Eliminar</button>
                <button type="button" id="btn_cerrarModalEliminarEmpleado" onclick="cerrarModal('eliminar_empleado', 'container_table')">Cerrar</button>
            </form>

        </div>
    </div>

</main>

