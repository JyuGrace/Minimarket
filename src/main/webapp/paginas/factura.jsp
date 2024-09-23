<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CO"></fmt:setLocale>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/style_factura.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Edu+AU+VIC+WA+NT+Hand:wght@400..700&display=swap" rel="stylesheet"/>
  </head>
  <body>
    <div id="factura">
      <div class="margin">
        <div class="headerFactura">
          <div class="logo">
              <img src="${pageContext.request.contextPath}/recursos/images/logo.jpg" />
          </div>
          <h1 id="facturaTitlle">Factura ${facturaId}</h1>
          <div id="infoFactura">
            <div class="vendedor">
              <ul>
                <li><span>Beto&Slendy</span></li>
                <li><span>Vendedor:</span> ${usuario.nombre} ${usuario.apellido}</li>
                <li>Barrio la Maria</li>
                <li>calle 53 Mz 10 Lt 10, Cartagena</li>
                <li>+57-3216580839</li>
                <li>
                  <a href="mailto:beto&slendy@gmail.com">beto&slendy@gmail.com</a>
                </li>
                <li><span>Fecha:</span> ${fechaVenta}</li>
              </ul>
            </div>
            <div class="cliente">
              <ul>
                <li><span>Cliente</span></li>
                <li><span>Nombre:</span> ${clienteVenta.nombre} ${clienteVenta.apellido}</li>
                <li><span>Cedula:</span> ${clienteVenta.cedula}</li>
                <li><span>Email: </span><a href="mailto:${clienteVenta.email}">${clienteVenta.email}</a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="productosFactura">
          <table>
            <caption>
              <h3>Lista de Productos</h3>
            </caption>
            <thead>
              <tr>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Valor</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${imprimirFacturas}">
                    <tr>
                        <td>${producto.nombreProducto}</td>
                        <td><fmt:formatNumber value="${producto.precioUnitario}" type="currency"/></td>
                        <td>${producto.cantidad}</td>
                        <td><fmt:formatNumber value="${producto.subtotal}" type="currency"/></td>
                    </tr>
                </c:forEach>
              <tr>
                <td class="montos" colspan="3"><span>SUBTOTAL:</span></td>
                <td><fmt:formatNumber value="${totalVenta - ivaValor}" type="currency"/></td>
              </tr>
              <tr>
                <td class="montos" colspan="3"><span>IVA(${100*ivaValor/totalVenta}%):</span></td>
                <td><fmt:formatNumber value="${ivaValor}" type="currency"/></td>
              </tr>

              <tr>
                <td class="montos" colspan="3"><span>TOTAL:</span></td>
                <td><fmt:formatNumber value="${totalVenta}" type="currency"/></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>

