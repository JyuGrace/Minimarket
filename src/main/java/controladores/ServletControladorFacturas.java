package controladores;

import datos.ClienteDaoJDBC;
import datos.DetalleFacturaDaoJDBC;
import datos.EmpleadoDaoJDBC;
import datos.FacturaDaoJDBC;
import datos.ProductoDaoJDBC;
import dominio.Cliente;
import dominio.ConsultasFacturas;
import dominio.DetalleFactura;
import dominio.Empleado;
import dominio.Factura;
import dominio.ImprimirFactura;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/ServletControladorFacturas")
public class ServletControladorFacturas extends HttpServlet{

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String accion = request.getParameter("accion");
    if(accion != null){
        switch (accion) {
            case "consultarOne":
                this.consultarFacturaOne(request, response);
                break;
            case "consultarAll":
                this.consultarFacturaAll(request, response);
                break;
            case "verFactura":
                this.verFactura(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }
}

private void consultarFacturaOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //eliminamos los posibles mensajes guarados en al sesion
    HttpSession sesion = request.getSession(false);
    sesion.removeAttribute("mensaje");
    sesion.removeAttribute("consultarFacturas");
    request.removeAttribute("mensaje");
    //recibimos y creamos la fecha
    java.sql.Date fechaSQL = obtenerFechaSQL(request, response);
    //traemos la lista de facturas desde la fecha indicada    
    List<Factura> facturas = new FacturaDaoJDBC().encontrarFacturaOnes(fechaSQL);
    //creamos lista que almacena los objetos de tipo ConsultasFacturas que recibira el jsp ConsultarFacturas
    List<ConsultasFacturas> consultasFacturas = new ArrayList<>();
    //traemos los hashmaps
    Map<Integer, String> clientesCedulaMap = mapCedulaCliente(); 
    Map<Integer, Empleado> empleadosMap = mapEmpleados();
    
    for(Factura elementosFactura: facturas){
        int idFactura = elementosFactura.getIdFactura();
        java.sql.Date fecha = elementosFactura.getFecha();
        String nombreEmpleado = empleadosMap.get(elementosFactura.getIdEmpleado()).getNombre();
        String apellidoEmpleado = empleadosMap.get(elementosFactura.getIdEmpleado()).getApellido();
        String cedulaCliente = clientesCedulaMap.get(elementosFactura.getIdCliente());
        Float total = elementosFactura.getTotal();
        ConsultasFacturas consultaFactura = new ConsultasFacturas(idFactura, fecha, nombreEmpleado, apellidoEmpleado, cedulaCliente, total);
        consultasFacturas.add(consultaFactura);
    }
    
    sesion.setAttribute("consultarFacturas", consultasFacturas);
    request.getRequestDispatcher("app.jsp").forward(request, response);
}

private void consultarFacturaAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //eliminamos los posibles mensajes guarados en al sesion
    HttpSession sesion = request.getSession(false);
    sesion.removeAttribute("mensaje");
    sesion.removeAttribute("consultarFacturas");
    request.removeAttribute("mensaje");
    //traemos la lista de facturas desde la fecha indicada    
    List<Factura> facturas = new FacturaDaoJDBC().encontrarFacturaAll();
    //creamos lista que almacena los objetos de tipo ConsultasFacturas que recibira el jsp ConsultarFacturas
    List<ConsultasFacturas> consultasFacturas = new ArrayList<>();
    //traemos los hashmaps
    Map<Integer, String> clientesCedulaMap = mapCedulaCliente(); 
    Map<Integer, Empleado> empleadosMap = mapEmpleados();
    
    for(Factura elementosFactura: facturas){
        int idFactura = elementosFactura.getIdFactura();
        java.sql.Date fecha = elementosFactura.getFecha();
        String nombreEmpleado = empleadosMap.get(elementosFactura.getIdEmpleado()).getNombre();
        String apellidoEmpleado = empleadosMap.get(elementosFactura.getIdEmpleado()).getApellido();
        String cedulaCliente = clientesCedulaMap.get(elementosFactura.getIdCliente());
        Float total = elementosFactura.getTotal();
        ConsultasFacturas consultaFactura = new ConsultasFacturas(idFactura, fecha, nombreEmpleado, apellidoEmpleado, cedulaCliente, total);
        consultasFacturas.add(consultaFactura);
    }
    
    sesion.setAttribute("consultarFacturas", consultasFacturas);
    request.getRequestDispatcher("app.jsp").forward(request, response);
}

private void verFactura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //obtenemos la sesion
    HttpSession sesion = request.getSession(false);
    //limpiamos variables de la sesion
    this.removeAttributes(request, response);
    //recibimos el idFactura
    int idFactura = Integer.parseInt(request.getParameter("idFactura"));
    System.out.println("idFactura = " + idFactura);
    Factura factura = new Factura(idFactura);
    Factura facturaVenta = new FacturaDaoJDBC().encontrarById(factura);
    Cliente clienteVenta = new ClienteDaoJDBC().encontrarById(new Cliente(facturaVenta.getIdCliente()));
    List<DetalleFactura> detalleFacturas = new DetalleFacturaDaoJDBC().listarByIdFactura(idFactura);
    //extraemos la fecha y el total de la factura
    java.sql.Date fechaVenta = facturaVenta.getFecha();
    Float totalVenta = facturaVenta.getTotal();
    //declaramaos el iva
    Float iva = 0f;
    //creamos la lista imprimirFactura
    List<ImprimirFactura> imprimirFacturas = new ArrayList<>();
    for(DetalleFactura elementos : detalleFacturas){
        String nombreProducto = new ProductoDaoJDBC().encontrarNombreProductoById(elementos.getIdProducto());
        Float precio = elementos.getPrecio();
        int cantidad = elementos.getCantidad();
        Float subtotal = elementos.getSubtotal();
        iva = elementos.getIva();
        ImprimirFactura imprimirFactura = new ImprimirFactura(nombreProducto, cantidad, precio, subtotal);
        imprimirFacturas.add(imprimirFactura);
    }
    sesion.setAttribute("totalVenta", totalVenta);
    sesion.setAttribute("ivaValor", iva*totalVenta);
    sesion.setAttribute("fechaFactura", fechaVenta);
    sesion.setAttribute("facturaId", idFactura);
    sesion.setAttribute("imprimirFacturas", imprimirFacturas);
    sesion.setAttribute("clienteVenta", clienteVenta);
    request.getRequestDispatcher("/paginas/factura.jsp").forward(request, response);
}

private java.sql.Date obtenerFechaSQL(HttpServletRequest request, HttpServletResponse response){
    //recibimos el parametro fecha
    String fechaSubmitt = request.getParameter("fecha");
    //formateamos la fecha
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    java.sql.Date fechaSQL = null;
    try {
        java.util.Date  fechaUtil = formatter.parse(fechaSubmitt);
        fechaSQL = new java.sql.Date(fechaUtil.getTime());
        System.out.println("fechaSQL = " + fechaSQL);
    } catch (ParseException ex) {
        ex.printStackTrace(System.out);
        System.out.println("Error parseando fecha de factura en modulo consultar Factura");
    }
    return fechaSQL;
}

private Map<Integer, String> mapCedulaCliente() {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        // Crear un mapa de categorías para rápida búsqueda por id
        Map<Integer, String> clientesMap = new HashMap<>();
        for (Cliente cliente : clientes) {
            clientesMap.put(cliente.getIdCliente(), cliente.getCedula());
        }
        return clientesMap;
    }

private Map<Integer, Empleado> mapEmpleados() {
        List<Empleado> empleados = new EmpleadoDaoJDBC().listar();
        // Crear un mapa de categorías para rápida búsqueda por id
        Map<Integer, Empleado> empleadosMap = new HashMap<>();
        for (Empleado empleado : empleados) {
            Empleado empleadoNombreApellido = new Empleado(empleado.getNombre(), empleado.getApellido());
            empleadosMap.put(empleado.getIdEmpleado(), empleadoNombreApellido);
        }
        return empleadosMap;
    }

private void removeAttributes(HttpServletRequest request, HttpServletResponse response){
    HttpSession sesion = request.getSession(false);
    sesion.removeAttribute("mensaje");
    request.removeAttribute("mensaje");
    
    sesion.removeAttribute("totalVenta");
    sesion.removeAttribute("ivaValor");
    sesion.removeAttribute("fechaFactura");
    sesion.removeAttribute("facturaId");
    sesion.removeAttribute("imprimirFacturas");
    sesion.removeAttribute("clienteVenta");
    
}

}
