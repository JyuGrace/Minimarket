package controladores;

import datos.EmpleadoDaoJDBC;
import datos.ProductoDaoJDBC;
import dominio.Empleado;
import dominio.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ServletControladorLogIn")
public class ServletControladorLogIn extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
      //recibo valores del formulario
      String usuario = request.getParameter("usuario");
      String password = request.getParameter("password");
      
      //validamos con la base de datos 
      boolean validar = new EmpleadoDaoJDBC().validarUsuario(usuario, password);
      System.out.println("validar credenciales = " + validar);
      //buscamos el nombre del empleado
      Empleado empleado = new EmpleadoDaoJDBC().encontrarByUserPass(usuario, password);
      // se verifican las credenciales del usuario
        if (validar) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", empleado);
            session.setAttribute("userType", empleado.getTipoEmpleado());
            
            //cargamos los  productos que estan bajos o  con cero stock
            List<Producto> productos = new ProductoDaoJDBC().listarProductosLowStock();
            session.setAttribute("productosLowStock", productos);
            
            response.sendRedirect("app.jsp");
            System.out.println("Loging in ");
        } else {
            // Credenciales incorrectas, redirigir a la página de login con mensaje de error
            request.setAttribute("error", "Credenciales Incorrectas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        }
    }
}
