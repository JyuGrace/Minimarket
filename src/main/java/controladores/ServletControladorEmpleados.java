package controladores;

import datos.EmpleadoDaoJDBC;
import dominio.Empleado;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorEmpleados")
public class ServletControladorEmpleados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "consultarOne":
                    this.consultarEmpleadoOne(request, response);
                    break;
                case "consultarAll":
                    this.consultarEmpleadoAll(request, response);
                    break;
                case "seleccionarEditar":
                    this.seleccionarEditarEmpleado(request, response);
                    break;
                case "seleccionarEliminar":
                    this.seleccionarEliminarEmpleado(request, response);
                    break;
                case "eliminar":
                    this.eiminarEmpleado(request, response);
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
                    this.insertarEmpleado(request, response);
                    break;
                case "actualizar":
                    this.actualizarEmpleado(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        java.sql.Date fechaRegistro = obtenerFechaActual();
        int tipoEmpelado = Integer.parseInt(request.getParameter("tipoEmpleado"));
        Empleado empleado = new Empleado(nombre, apellido, cedula, usuario, password, email, telefono, direccion, fechaRegistro, tipoEmpelado);
        System.out.println("empleado = " + empleado);
        int empleadoInsertado = new EmpleadoDaoJDBC().insertar(empleado);
        System.out.println("empleadoInsertado = " + empleadoInsertado);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private java.sql.Date obtenerFechaActual() {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String currentDateUtilString = formatter.format(Calendar.getInstance().getTime());
        java.sql.Date currentDateSql = null;
        //obtenemos fecha tipo java.util.Date
        java.util.Date currentDateUtil = null;
        try {
            currentDateUtil = formatter.parse(currentDateUtilString);
            currentDateSql = new java.sql.Date(currentDateUtil.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error en la creacion de la fecha actual");
            Logger.getLogger(ServletControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentDateSql;
    }

    private void consultarEmpleadoOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("empleados");
        String nombre = request.getParameter("nombre");
        Empleado empleado = new Empleado(nombre);
        Empleado empleadoConsultarOne = new EmpleadoDaoJDBC().encontrarByName(empleado);
        if (empleadoConsultarOne.getCedula() != null) {
            System.out.println("empleadoConsultarOne = " + empleadoConsultarOne);
            List<Empleado> empleados = new ArrayList<>();
            empleados.add(empleadoConsultarOne);
            sesion.setAttribute("empleados", empleados);
        } else {
            sesion.setAttribute("empleados", null);
        }
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void consultarEmpleadoAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("empleados");
        List<Empleado> empleados = new EmpleadoDaoJDBC().listar();
        System.out.println("empleados = " + empleados);
        sesion.setAttribute("empleados", empleados);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    
    private void seleccionarEditarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("modal");
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new Empleado(idEmpleado);
        Empleado empleadoseleccionarEditar = new EmpleadoDaoJDBC().encontrarById(empleado);
        System.out.println("empleadoseleccionarEditar = " + empleadoseleccionarEditar);
        request.setAttribute("empleadoEditar", empleadoseleccionarEditar);
        request.setAttribute("modal", "editar_empleado");
        request.getRequestDispatcher("app.jsp").forward(request, response);
        
    }
    
    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("mensaje");
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        java.sql.Date fechaRegistro = java.sql.Date .valueOf(request.getParameter("fechaRegistro"));
        int tipoEmpleado = Integer.parseInt(request.getParameter("tipoEmpleado"));
        Empleado empleadoActualizar = new Empleado(idEmpleado, nombre, apellido, cedula, usuario, password, email, telefono, direccion, fechaRegistro, tipoEmpleado);
        System.out.println("empleadoActualizar = " + empleadoActualizar);
        int empleadosActualziados = new EmpleadoDaoJDBC().actualizar(empleadoActualizar);
        System.out.println("empleadosActualizados = " + empleadosActualziados);
        request.setAttribute("mensaje", "actualizado");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    
    private void seleccionarEliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("modal");
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new Empleado(idEmpleado);
        Empleado empleadoSeleccionarEliminar = new EmpleadoDaoJDBC().encontrarById(empleado);
        System.out.println("empleadoSeleccionarEliminar = " + empleadoSeleccionarEliminar);
        request.setAttribute("empleadoSeleccionarEliminar", empleadoSeleccionarEliminar);
        request.setAttribute("modal", "eliminar_empleado");
        request.getRequestDispatcher("app.jsp").forward(request, response);
        
    }
    
    private void eiminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("mensaje");
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new Empleado(idEmpleado);
        int empleadosEliminados = new EmpleadoDaoJDBC().eliminar(empleado);
        System.out.println("empleadosEliminados = " + empleadosEliminados);
        request.setAttribute("mensaje", "eliminado");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
}
