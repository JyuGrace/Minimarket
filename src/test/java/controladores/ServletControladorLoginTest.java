package controladores;

import datos.EmpleadoDaoJDBC;
import dominio.Empleado;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ServletControladorLoginTest {

    @InjectMocks
    private ServletControladorLogIn loginServlet; // Clase a probar

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private EmpleadoDaoJDBC empleadoDaoJDBC;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // Inicializamos los mocks
    }

    // Test para login exitoso
    @Test
    public void testLoginExitoso() throws ServletException, IOException {
        // Simulamos los parámetros del formulario
        when(request.getParameter("usuario")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");

        // Simulamos el resultado del DAO de que el usuario es válido
        when(empleadoDaoJDBC.validarUsuario("admin", "admin")).thenReturn(true);

        // Simulamos que se encuentra al empleado por el usuario y contraseña
        Empleado empleado = new Empleado(); // Configura este empleado según sea necesario
        when(empleadoDaoJDBC.encontrarByUserPass("admin", "admin")).thenReturn(empleado);

        // Simulamos que la sesión es creada
        when(request.getSession()).thenReturn(session);

        // Ejecutamos el método doPost
        loginServlet.doPost(request, response);

        // Verificamos que se haya creado una sesión con el empleado correcto
        verify(session).setAttribute("usuario", empleado);

        // Verificamos la redirección a la página app.jsp
        verify(response).sendRedirect("app.jsp");
    }

    // Test para login fallido
    @Test
    public void testLoginFallido() throws ServletException, IOException {
        // Simulamos los parámetros del formulario
        when(request.getParameter("usuario")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("wrongpassword");

        // Simulamos el resultado del DAO de que el usuario es inválido
        when(empleadoDaoJDBC.validarUsuario("admin", "wrongpassword")).thenReturn(false);

        // Ejecutamos el método doPost
        loginServlet.doPost(request, response);

        // Verificamos que el atributo de error se haya agregado
        verify(request).setAttribute("error", "Credenciales Incorrectas");

        // Verificamos la redirección a la página login.jsp
        verify(response).sendRedirect("login.jsp");
    }
}

