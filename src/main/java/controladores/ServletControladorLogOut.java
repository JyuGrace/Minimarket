
package controladores;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ServletControladorLogOut")
public class ServletControladorLogOut extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession sesion = request.getSession(false); // Obtiene la sesión, pero no crea una nueva si no existe
        if (sesion != null) {
            sesion.removeAttribute("usuario");
            sesion.invalidate(); // Invalida la sesión actual
            System.out.println("Loging out");
        }
        response.sendRedirect("login.jsp");
    }
}
