<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CO"></fmt:setLocale>

    <main>
        <div id="container_table">
            <form action="${pageContext.request.contextPath}/ServletControladorProductos" id="form_consulta_producto" method="get">
            <h1 id="titulo_form">Consultar Productos</h1>
            <div id="grupo_form_consulta">
                <label for="nombre">Nombre</label>
                <!-- parametros ocultos -->
                <input type="hidden" name="page" value="consultarProducto">
                <input type="hidden" id="accionConsultar" name="accion" >
                <input type="text" id="nombreBuscar" name="nombre" class="formulario__input_consulta" placeholder="nombre del producto">
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarOne')" >Buscar</button>
                <button type="submit" class="formulario__btn_consulta" onclick="consultar('consultarAll')" >Buscar Todos</button>
            </div>
        </form>
        <table id="productos">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Categoria</th>
                    <th>Stock</th>
                    <th>Precio Unitario</th>
                    <th>Descripcion</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty productos}">
                        <c:forEach var="productos" items="${productos}">
                            <tr>
                                <td>${productos.nombre}</td>
                                <td>${productos.categoriaNombre}</td>
                                <td>${productos.stock}</td>
                                <td><fmt:formatNumber value="${productos.precio}" type="currency"/></td>
                                <td>${productos.descripcion}</td>
                                <td>
                                    <c:if test="${userType == 1}">
                                        <a href="${pageContext.request.contextPath}/ServletControladorProductos?page=consultarProducto&accion=editar&idProducto=${productos.idProducto}" onclick="">Editar</a>
                                        |
                                    </c:if>
                                    <a href="${pageContext.request.contextPath}/ServletControladorProductos?page=consultarProducto&accion=seleccionarEliminar&idProducto=${productos.idProducto}" onclick="">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${empty productos}">
                    <td colspan="6"><h3>No se encontraron Productos</h3></td>
                </c:when>
            </c:choose>

            </tbody>
        </table>
    </div>


    <!-- modal -->
    <c:if test="${modal == 'editar_producto'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('editar_producto', 'container_table');
            });
        </script>
    </c:if>
    <c:if test="${modal == 'eliminar_producto'}">
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                abrirModal('eliminar_producto', 'container_table');
            });
        </script>
    </c:if>
    <!-- mensaje -->
    <c:if test="${mensaje == 'Producto actualizado con exito!'}">
        <script>
            alert("Producto actualizado con exito");
        </script>
    </c:if>
    <c:if test="${mensaje == 'Producto eliminado con Exito!'}">
        <script>
            alert("Producto eliminado con exito");
        </script>
    </c:if>


    <!-- MODALES -->
    <div class="modal" id="editar_producto">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ServletControladorProductos?page=consultarProducto&accion=actualizar&idProducto=${productoEditar.idProducto}" class="formulario" id="formulario" method="post">
                <h1 id="titulo_form">Editar Producto</h1>

                <!-- Grupo: Nombre -->
                <div class="formulario__grupo" id="grupo__nombre">

                    <!-- Grupo: idProveedor (sin este campo el metodo actualizar falla) -->
                    <input type="hidden" name="idProveedor" value="${productoEditar.idProveedor}"/>

                    <label class="consulta_form_label" for="nombre" class="formulario__label">Nombre</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="text"
                            class="formulario__input"
                            name="nombre"
                            id="nombre"
                            value="${productoEditar.nombre}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">
                        El nombre tiene que ser de 4 a 16 digitos.
                    </p>
                </div>

                <!-- Grupo: Categoria -->
                <div class="formulario__grupo" id="grupo__categoria">
                    <label for="categoria" class="formulario__label">Categoria</label>
                    <div class="formulario__grupo-input">
                        <select class="formulario__input" name="categoria" id="producto_categoria" required="true">
                            <option value="" >--Seleccionar--</option>
                            <option value="1" ${productoEditar.idCategoria == 1 ? 'selected' : ''}>Frutas</option>
                            <option value="2" ${productoEditar.idCategoria == 2 ? 'selected' : ''}>Verduras</option>
                            <option value="3" ${productoEditar.idCategoria == 3 ? 'selected' : ''}>Carnes</option>
                            <option value="4" ${productoEditar.idCategoria == 4 ? 'selected' : ''}>Lácteos</option>
                            <option value="5" ${productoEditar.idCategoria == 5 ? 'selected' : ''}>Panadería</option>
                            <option value="6" ${productoEditar.idCategoria == 6 ? 'selected' : ''}>Bebidas</option>
                            <option value="7" ${productoEditar.idCategoria == 7 ? 'selected' : ''}>Productos de limpieza</option>
                            <option value="8" ${productoEditar.idCategoria == 8 ? 'selected' : ''}>Productos de cuidado personal</option>
                            <option value="9" ${productoEditar.idCategoria == 9 ? 'selected' : ''}>Miscelánea</option>
                            <option value="10" ${productoEditar.idCategoria == 10 ? 'selected' : ''}>Legumbres</option>
                            <option value="11" ${productoEditar.idCategoria == 11 ? 'selected' : ''}>Cereales</option>
                        </select>
                    </div>
                    <p class="formulario__input-error">
                        Seleccione una categoria.
                    </p>
                </div>
                <!-- Grupo: stock -->
                <div class="formulario__grupo" id="grupo__stock">
                    <label for="stock" class="formulario__label">Stock</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="number"
                            class="formulario__input"
                            name="stock"
                            id="stock"
                            value="${productoEditar.stock}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">Indique el numero de existencias a agregar.</p>
                </div>

                <!-- Grupo: precio -->
                <div class="formulario__grupo" id="grupo__precio">
                    <label for="precio" class="formulario__label">Precio Unitario</label>
                    <div class="formulario__grupo-input">
                        <input
                            type="number"
                            class="formulario__input"
                            name="precio"
                            id="precio"
                            value="${productoEditar.precio}"
                            />
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">Indique el precio del producto</p>
                </div>

                <!-- Grupo: descripcion -->
                <div class="formulario__grupo" id="grupo__correo">
                    <label for="email" class="formulario__label">Descripcion del Producto</label>
                    <div class="formulario__grupo-input">
                        <textarea class="formulario__input" name="descripcion" id="descripcion" cols="30" rows="10">${productoEditar.descripcion}</textarea>
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                    </div>
                    <p class="formulario__input-error">Ingrese la descripcion del producto.</p>
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
                        <button type="button" id="btn_cerrarModalEditarProducto" class="formulario__btn" onclick="cerrarModal('editar_producto', 'container_table')">Cerrar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="modal" id="eliminar_producto">
        <div class="modal-content">
            <h2>Eliminar Producto</h2>
            <p>¿Estas seguro de que desea eliminar el producto: <span style="font-weight: bold; color: red">${productoSeleccionarEliminar.nombre}</span></p>
            <form action="${pageContext.request.contextPath}/ServletControladorProductos" method="get" class="formulario">
                <input type="hidden" name="page" value="consultarProducto" />
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idProducto" value="${productoSeleccionarEliminar.idProducto}" />
                <button type="submit" onclick="">Eliminar</button>
                <button type="button" id="btn_cerrarModalEliminarProducto" onclick="cerrarModal('eliminar_producto', 'container_table')">Cerrar</button>
            </form>

        </div>
    </div>



</main>
