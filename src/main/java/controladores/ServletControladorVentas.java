package controladores;

import datos.ClienteDaoJDBC;
import datos.DetalleFacturaDaoJDBC;
import datos.FacturaDaoJDBC;
import datos.ProductoDaoJDBC;
import dominio.Cliente;
import dominio.DetalleFactura;
import dominio.Empleado;
import dominio.Factura;
import dominio.ImprimirFactura;
import dominio.Producto;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorVentas")
public class ServletControladorVentas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.accionDefault(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "buscarCliente":
                    this.buscarCliente(request, response);
                    break;
                case "registrarCliente":
                    this.registrarCliente(request, response);
                    break;
                case "vender":
                    this.finalizarVenta(request, response);
                    this.actualizarProductosConLowStock(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("mensaje");
        request.removeAttribute("mensaje");
        String cedula = request.getParameter("cedula");
        Cliente cliente = new Cliente(cedula);
        Cliente clienteBuscar = new ClienteDaoJDBC().encontrarByCedula(cliente);
        System.out.println("clienteBuscar = " + clienteBuscar);
        if (clienteBuscar.getNombre() != null) {
            sesion.setAttribute("clienteVenta", clienteBuscar);
        } else {
            sesion.setAttribute("clienteVenta", null);
            sesion.setAttribute("mensaje", "Cliente no encontrado");
        }
        
        response.sendRedirect(request.getContextPath() + "/ServletControladorVentas?page=ventas");
        //request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("mensaje");
        request.removeAttribute("mensaje");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        Cliente cliente = new Cliente(nombre, apellido, cedula, email, telefono, direccion);
        int clienteVentaRegistrado = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("clienteVentaRegistrado = " + clienteVentaRegistrado);
        Cliente ClienteFindByCedula = null;
        if (clienteVentaRegistrado > 0) {
            Cliente clienteCedula = new Cliente(cedula);
            ClienteFindByCedula = new ClienteDaoJDBC().encontrarByCedula(clienteCedula);
            sesion.setAttribute("mensaje", "Cliente registrado exitosamente");
        } else {
            sesion.setAttribute("mensaje", "Error al registrar cliente");
        }
        
        sesion.setAttribute("clienteVenta", ClienteFindByCedula);
        response.sendRedirect(request.getContextPath() + "/ServletControladorVentas?page=ventas");
        //request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = new ProductoDaoJDBC().listarStockDisponible();
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        HttpSession sesion = request.getSession(false);
        //remuevo la variable mensaje para que no dispare el alert
        sesion.removeAttribute("mensaje");
        request.removeAttribute("mensaje");
        //creamos la fecha de la factura
        java.util.Date fechaUtil = new java.util.Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = formater.format(fechaUtil);
        //agregamos atributos a la sesion
        sesion.setAttribute("productosVenta", productos);
        sesion.setAttribute("clientesVenta", clientes);
        sesion.setAttribute("fechaVenta", fechaString);
        System.out.println("Ingresando al modulo ventas");
        System.out.println("productos en ventas: " + productos);
        System.out.println("listado clientes: " + clientes);
        request.getRequestDispatcher("app.jsp").forward(request, response);
        
    }

    private void finalizarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("mensaje");
        sesion.removeAttribute("generarFactura");
        //recibimos los parametros del modulo venta
        Cliente clienteVenta = (Cliente) sesion.getAttribute("clienteVenta");
        Empleado empleadoVenta = (Empleado) sesion.getAttribute("usuario");
        java.sql.Date fechaVenta = fechaVentaSQL(request);
        String productosId[] = request.getParameterValues("idProducto");
        String precios[] = request.getParameterValues("precio_unitario");
        String subtotales[] = request.getParameterValues("subtotal");
        String cantidades[] = request.getParameterValues("cantidad");
        Float total = Float.parseFloat(request.getParameter("total"));

        //creamos instancia de objeto Factura
        int idCliente = clienteVenta.getIdCliente();
        int idEmpleado = empleadoVenta.getIdEmpleado();
        Factura factura = new Factura(fechaVenta, total, idCliente, idEmpleado);
        //insertamos factura en la DB y recuperamos el ultimo ID insertado
        int lastInsertedId = new FacturaDaoJDBC().insertar(factura);
        
        //Declaramos el iva
        Float ivaFloat = 0.19f;
        Float ivaValor = ivaFloat*total;
        
        //creamos lista para almacenar objetos de tipo imprimirFactura
        List<ImprimirFactura> imprimirFacturas = new ArrayList<>();
        
        //creamos los objetos detalle_factura y los agregamos a una lista
        int counterRegistrosInsertados = 0;
        for (int i = 0; i < productosId.length; i++) {
            int cantidad = Integer.parseInt(cantidades[i]);
            Float precioUnitario = Float.parseFloat(precios[i]);
            Float subtotal = Float.parseFloat(subtotales[i]);
            int idProducto = Integer.parseInt(productosId[i]);
            
            //consultamos los productos por ID, y poblamos los arrays de nombresProductos,cantidadesProductos,preciosProductos, subtotalesProductos
            Producto producto = new Producto(idProducto);
            Producto buscarProductoById = new ProductoDaoJDBC().encontrarById(producto);
            String nombreProducto = buscarProductoById.getNombre();
            
            //actualizamos el stock de productos despues de la venta
            int nuevoStock = buscarProductoById.getStock() - cantidad;
            System.out.println("nuevoStock = " + nuevoStock);
            buscarProductoById.setStock(nuevoStock);
            int stockActualizado = new ProductoDaoJDBC().actualizar(buscarProductoById);//se actualzia el stock en la db
            
            //creamos objeto imprimirFactura y añadimos a la lista
            ImprimirFactura imprimirFactura = new ImprimirFactura(nombreProducto, cantidad, precioUnitario, subtotal);
            imprimirFacturas.add(imprimirFactura);
            
            //creamos instancia de objeto DetalleFactura para su insercion en la DB
            DetalleFactura detalleFactura = new DetalleFactura(cantidad, precioUnitario, ivaFloat, subtotal, lastInsertedId, idProducto);
            int detalleFacturaInsertados = new DetalleFacturaDaoJDBC().insertar(detalleFactura);
            counterRegistrosInsertados += detalleFacturaInsertados;
        }
        
        //creamos variable boolean para habilitar redirect en ventas.jsp a factura.jsp
        boolean generarFactura = counterRegistrosInsertados > 0;
        //guardamos las variables en la sesion para poder llenar la factura
        sesion.setAttribute("ivaValor", ivaValor);
        sesion.setAttribute("imprimirFacturas", imprimirFacturas);
        sesion.setAttribute("fechaVenta", fechaVenta);
        sesion.setAttribute("totalVenta", total);
        sesion.setAttribute("facturaId", lastInsertedId);
        sesion.setAttribute("generarFactura", generarFactura);
        sesion.setAttribute("mensaje", "Venta finalizada con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
                
        System.out.println("totalRegistrosInsertados = " + counterRegistrosInsertados);
        System.out.println("lista imprimirFactura" + imprimirFacturas);
        
    }

    private java.sql.Date fechaVentaSQL(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        String fechaVenta = (String) sesion.getAttribute("fechaVenta");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaVentaUitl = null;
        java.sql.Date fechaVentaSql = null;
        try {
            fechaVentaUitl = formatter.parse(fechaVenta);
            fechaVentaSql = new java.sql.Date(fechaVentaUitl.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error al parsear la fecha Venta");
        }
        return fechaVentaSql;
    }
    
    private List<Producto> actualizarProductosConLowStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("productosLowStock");
        //consultamos lal ista de productos
        List<Producto> productos = new ProductoDaoJDBC().listarProductosLowStock();
        sesion.setAttribute("productosLowStock", productos);
        request.getRequestDispatcher("app.jsp").forward(request, response);
        return productos;
    }

}
