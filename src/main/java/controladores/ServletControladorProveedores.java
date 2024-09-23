package controladores;

import datos.ProveedorDaoJDBC;
import dominio.Proveedor;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorProveedores")
public class ServletControladorProveedores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "consultarAll":
                    this.consultarProveedorAll(request, response);
                    break;
                case "consultarOne":
                    this.consultarProveedorOne(request, response);
                    break;
                case "editar":
                    this.editarProveedor(request, response);
                    break;
                case "seleccionarEliminar":
                    this.seleccionarEliminar(request, response);
                    break;
                case "eliminar":
                    this.eliminarproveedor(request, response);
                    break;
                default:
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
                    this.insertarProveedor(request, response);
                    break;
                case "actualizar":
                    this.actualizarProveedor(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void insertarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nombre_empresa = request.getParameter("nombre_empresa");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        Proveedor proveedor = new Proveedor(nombre, apellido, nombre_empresa, email, telefono);
        int registrosInsertados = new ProveedorDaoJDBC().insertar(proveedor);
        System.out.println("registrosInsertados = " + registrosInsertados);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void consultarProveedorOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("proveedores");
        String nombre = request.getParameter("nombre");
        Proveedor proveedor = new Proveedor(nombre);
        Proveedor proveedorEncontrarByName = new ProveedorDaoJDBC().encontrarByName(proveedor);
        System.out.println("proveedorEncontrarByName = " + proveedorEncontrarByName);
        List<Proveedor> proveedores = new ArrayList<>();
        proveedores.add(proveedorEncontrarByName);
        sesion.setAttribute("proveedores", proveedores);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void consultarProveedorAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("proveedores");
        List<Proveedor> proveedores = new ProveedorDaoJDBC().listar();
        System.out.println("proveedores = " + proveedores);
        sesion.setAttribute("proveedores", proveedores);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void editarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("modal");
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Proveedor proveedor = new Proveedor(idProveedor);
        Proveedor proveedorEditar = new ProveedorDaoJDBC().encontrarById(proveedor);
        System.out.println("proveedorEditar = " + proveedorEditar);
        request.setAttribute("proveedorEditar", proveedorEditar);
        request.setAttribute("modal", "editar_proveedor");
        request.getRequestDispatcher("app.jsp").forward(request, response);

    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("mensaje");
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nombreEmpresa = request.getParameter("nombre_empresa");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        Proveedor proveedor = new Proveedor(idProveedor, nombre, apellido, nombreEmpresa, email, telefono);
        int proveedorActualizado = new ProveedorDaoJDBC().actualizar(proveedor);
        System.out.println("proveedorActualizado = " + proveedorActualizado);
        request.setAttribute("mensaje", "Proveedor actualizado con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void seleccionarEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("modal");
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Proveedor proveedor = new Proveedor(idProveedor);
        Proveedor proveedorSeleccionarEliminar = new ProveedorDaoJDBC().encontrarById(proveedor);
        System.out.println("proveedorSeleccionarEliminar = " + proveedorSeleccionarEliminar);
        request.setAttribute("modal", "eliminar_proveedor");
        request.setAttribute("proveedorSeleccionarEliminar", proveedorSeleccionarEliminar);
        request.getRequestDispatcher("app.jsp").forward(request, response);

    }

    private void eliminarproveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("mensaje");
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Proveedor proveedor = new Proveedor(idProveedor);
        int proveedoresEliminados = new ProveedorDaoJDBC().eliminar(proveedor);
        System.out.println("proveedoresEliminados = " + proveedoresEliminados);
        request.setAttribute("mensaje", "Proveedor eliminado con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
}
