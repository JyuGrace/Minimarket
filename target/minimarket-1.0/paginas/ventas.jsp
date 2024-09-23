<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.gson.Gson" %>

<%
    // Convertir las listas a JSON
    Gson gson = new Gson();
    String productosJson = gson.toJson(session.getAttribute("productosVenta"));
    String clientesJson = gson.toJson(session.getAttribute("clientesVenta"));
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 

<style>
    .suggestion {
        padding: 10px;
        cursor: pointer;
        border: 1px solid #ccc;
        background: white;
        position: relative;
    }
    .suggestion:hover {
        background-color: #f0f0f0;
    }
    .suggestions-container {
        border: 1px solid #ccc;
        position: absolute;
        background: white;
        z-index: 1000;
    }
    .cantidad {
        width: 50%;
        display: block;
        margin: 0 auto;
        text-align: center;
    }
</style>

<main>
    <div id="ventasContainer">
        <h1 id="titulo_form">Venta</h1>
        <form action="${pageContext.request.contextPath}/ServletControladorVentas" method="post" id="form_consulta_cliente">
            <div class="header">
                    <!-- campos ocultos -->
                    <input type="hidden" name="page" value="ventas"/>
                    <input type="hidden" name="accion" id="accionVenta"/>
                    <table class="table_header">
                        <tbody>
                            <tr>
                                <th><label for="cedula">C.C (cliente):</label></th>
                                <td>
                                    <input type="text" name="cedula" class="formulario__input" id="cedula" value="${clienteVenta.cedula != null? clienteVenta.cedula: ''}" />
                                </td>
                                <td>
                                    <button type="button" class="formulario__btn_consulta" onclick="return clienteVenta('buscarCliente')" >Buscar</button>
                                </td>
                                <td>
                                    <button type="button" class="formulario__btn_consulta" onclick="abrirModal('registrarClienteVenta', 'ventasContainer')" >Registrar</button>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="nombreCliente">Nombre Cliente:</label></th>
                                <td>
                                    <input type="text" name="nombreCliente" id="nombreCliente" class="formulario__input" value="${clienteVenta.nombre} ${clienteVenta.apellido}" readonly/>
                                </td>

                                <th><label for="nombreEmpleado">Empleado: </label></th>
                                <td>
                                    <input type="text" name="nombreEmpleado" value="${usuario.nombre} ${usuario.apellido}" class="formulario__input" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="producto">Producto: </label></th>
                                <td>
                                    <input type="text" name="producto" placeholder="agregar producto" class="formulario__input" />
                                    <div class="suggestions-container"></div>
                                </td>
                                <td>
                                    <input type="hidden" name="precio" />
                                    <input type="hidden" name="stock" />
                                    <input type="hidden" name="id_producto" />
                                    <input type="button" value="Agregar" class="formulario__btn_consulta" />
                                </td>
                            </tr>
                            <tr>
                                <th><label for="fecha">Fecha: </label></th>
                                <td>
                                    <input type="date" value="${fechaVenta}" class="formulario__input" readonly/>
                                </td>
                                <td colspan="2">
                                    <input type="button" value="!Finalizar Venta!" class="formulario__btn_consulta" onclick="return vender('vender')"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>

            </div>
            <br />
            <div id="productos">
                <div>
                    <table>
                        <caption>
                            <h2>Lista de artículos</h2>
                        </caption>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Cantidad</th>
                                <th>Precio Unitario</th>
                                <th>Subtotal</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Aquí se agregarán las filas de productos -->
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="total_container">
                <div><h3>Total:</h3></div>
                <div><h3>$0</h3></div>
                <input type="hidden" name="total" id="totalVenta" />
            </div>
        </form>
    </div>

    <!-- MODAL REGISTRAR CLIENTE -->
    <div class="modal" id="registrarClienteVenta">
        <jsp:include page="registrarClienteVenta.jsp"/>
    </div>

    <!-- Alertas de registro de cliente -->
    <c:if test="${not empty mensaje}">
        <script>alert("${mensaje}");</script>
    </c:if>
    
    <c:if test="${not empty sessionScope.generarFactura and sessionScope.generarFactura == true}">
        <script>
             window.open("${pageContext.request.contextPath}/paginas/factura.jsp", "_blank");
        </script>
    </c:if>
    <c:remove var="generarFactura" scope="session"/>
        
        
    <!-- Script para manejo de productos y clientes -->
    <script>
        $(document).ready(function () {
            var productos = <%= productosJson%>;
            var clientes = <%= clientesJson%>;

            // Función para calcular el total de la venta
            function calcularTotal() {
                let total = 0;
                $("#productos tbody tr").each(function () {
                    let subtotal = parseFloat($(this).find(".subtotal").text());
                    total += subtotal;
                });
                $("#total_container h3").last().text("$" + total.toFixed(2));
                $("#totalVenta").val(total.toFixed(2)); // Actualizar el campo oculto
            }


            // Manejo del autocompletado para el campo de productos
            $("input[name='producto']").on("input", function () {
                let query = $(this).val().toLowerCase();
                let suggestions = $(".suggestions-container");
                suggestions.empty();

                if (query.length > 0) {
                    productos.forEach(function (producto) {
                        if (producto.nombre.toLowerCase().startsWith(query)) {
                            suggestions.append("<div class='suggestion' data-id='" + producto.idProducto + "' data-nombre='" + producto.nombre + "' data-precio='" + producto.precio + "' data-stock='" + producto.stock + "'>" + producto.nombre + "</div>");
                        }
                    });

                    // Maneja el clic en una sugerencia
                    $(".suggestion").on("click", function () {
                        let id = $(this).data("id");
                        let nombre = $(this).data("nombre");
                        let precio = $(this).data("precio");
                        let stock = $(this).data("stock");
                        console.log("Producto seleccionado: ", id, nombre, precio, stock); // Para depuración
                        $("input[name='producto']").val(nombre);
                        $("input[name='precio']").val(precio);
                        $("input[name='stock']").val(stock); // Input oculto para almacenar el stock
                        $("input[name='id_producto']").val(id); // Input oculto para almacenar el id del producto
                        suggestions.empty();
                    });
                }
            });

            // Manejo del botón Agregar
            $(".formulario__btn_consulta[value='Agregar']").on("click", function () {
                let productoId = $("input[name='id_producto']").val();
                let productoNombre = $("input[name='producto']").val();
                let precio = parseFloat($("input[name='precio']").val());
                let stock = parseInt($("input[name='stock']").val());
                if (productoNombre && !isNaN(precio) && !isNaN(stock)) {
                    let cantidad = 1;
                    let subtotal = precio * cantidad;
                    $("#productos tbody").append(
                            '<tr>' +
                            '<td>' + productoNombre + '</td>' +
                            '<td><input type="number" name="cantidad" class="cantidad" value="' + cantidad + '" min="1" max="' + stock + '"></td>' +
                            '<td class="precio">' + precio.toFixed(2) + '</td>' +
                            '<td class="subtotal">' + subtotal.toFixed(2) + '</td>' +
                            '<td><button class="remove">Eliminar</button></td>' +
                            '<input type="hidden" id="idProductoVenta" name="idProducto" value="' + productoId + '" />' +
                            '<input type="hidden" name="precio_unitario" value="' + precio.toFixed(2) + '" />' +
                            '<input type="hidden" name="subtotal" value="' + subtotal.toFixed(2) + '" />' +
                            '</tr>'
                            );
                    calcularTotal();

                    // Limpiar los inputs después de agregar el producto
                    $("input[name='producto']").val('');
                    $("input[name='precio']").val('');
                    $("input[name='stock']").val('');
                    $("input[name='id_producto']").val('');

                    // Actualizar subtotal al cambiar cantidad
                    $(".cantidad").on("input", function () {
                        let cantidad = $(this).val();
                        let precio = parseFloat($(this).closest("tr").find(".precio").text());
                        let stock = parseInt($(this).attr("max")); // Obtener el stock máximo permitido
                        if (cantidad > stock) {
                            $(this).val(stock); // Ajustar al stock máximo si se excede
                            cantidad = stock;
                        }
                        let subtotal = cantidad * precio;
                        $(this).closest("tr").find(".subtotal").text(subtotal.toFixed(2));
                        $(this).closest("tr").find("input[name='subtotal']").val(subtotal.toFixed(2)); // Actualizar el campo oculto
                        calcularTotal();
                    });

                    // Eliminar fila
                    $(".remove").on("click", function () {
                        $(this).closest("tr").remove();
                        calcularTotal();
                    });
                }
            });

        });
    </script>          

</main>
