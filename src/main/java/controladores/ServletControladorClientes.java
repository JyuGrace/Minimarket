package controladores;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorClientes")
public class ServletControladorClientes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "consultarAll":
                    this.consultarClienteAll(request, response);
                    break;
                case "consultarOne":
                    this.consultarClienteOne(request, response);
                    break;
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                case "seleccionarEliminar":
                    this.seleccionarEliminar(request, response);
                    break;    
                    
                default:
                    //String path = request.getContextPath() + "app.jsp/?page=consultarCliente";
                    //response.sendRedirect(path);
                    throw new AssertionError();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "actualizar":
                    this.actualizarCliente(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        Cliente cliente = new Cliente(nombre, apellido, cedula, email, telefono, direccion);

        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);
        
        request.getRequestDispatcher("app.jsp").forward(request, response);

    }

    private void consultarClienteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("clientes");
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        sesion.setAttribute("clientes", clientes);
        //request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void consultarClienteOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("clientes");
        String cedula = request.getParameter("cedula");
        Cliente cliente = new Cliente(cedula);
        Cliente clienteEncontrar = new ClienteDaoJDBC().encontrarByCedula(cliente);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(clienteEncontrar);

        if (clienteEncontrar.getNombre()!= null) {
            sesion.setAttribute("clientes", clientes);
        } else {
            sesion.setAttribute("clientes", null);
        }

        request.setAttribute("cedula", cedula);
        request.getRequestDispatcher("app.jsp").forward(request, response);
        System.out.println(cliente.toString());
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("modal");
        String paramIdCliente = request.getParameter("idCliente");
        int idCliente = Integer.parseInt(paramIdCliente);
        Cliente cliente = new Cliente(idCliente);
        Cliente clienteEditar = new ClienteDaoJDBC().encontrarById(cliente);
        System.out.println("clienteEditar = " + clienteEditar);
        request.setAttribute("clienteEditar", clienteEditar);
        request.setAttribute("modal", "editar_cliente");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("mensaje");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        Cliente cliente = new Cliente(idCliente, nombre, apellido, cedula, email, telefono, direccion);
        int registroActualizado = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("registroActualizado = " + registroActualizado);
        request.setAttribute("mensaje", "Cliente actualizado con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.removeAttribute("mensaje");
        int idCliente= Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        int registroEliminado = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("registroEliminado = " + registroEliminado);
        request.setAttribute("mensaje", "Cliente eliminado con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    private void seleccionarEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.removeAttribute("modal");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        Cliente seleccionarClienteEliminar = new ClienteDaoJDBC().encontrarById(cliente);
        System.out.println("seleccionarClienteEliminar = " + seleccionarClienteEliminar);
        request.setAttribute("modal", "eliminar_cliente");
        request.setAttribute("clienteSeleccionarEliminar", seleccionarClienteEliminar);
        request.getRequestDispatcher("app.jsp").forward(request,response);
    }

}
