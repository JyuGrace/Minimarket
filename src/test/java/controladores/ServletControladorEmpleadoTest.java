package controladores;

import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServletControladorEmpleadoTest {

    @InjectMocks
    private ServletControladorEmpleados empleadoServlet; // Clase a probar

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
public void testInsertarEmpleado() throws ServletException, IOException {
    // Simulamos los parámetros enviados por el formulario para un empleado
    when(request.getParameter("accion")).thenReturn("insertar");
    when(request.getParameter("nombre")).thenReturn("EmpleadoTest");
    when(request.getParameter("apellido")).thenReturn("ApellidoTest");
    when(request.getParameter("cedula")).thenReturn("6565612789");
    when(request.getParameter("usuario")).thenReturn("empleado123");
    when(request.getParameter("password")).thenReturn("password123");
    when(request.getParameter("email")).thenReturn("empleado@test.com");
    when(request.getParameter("telefono")).thenReturn("987654441");
    when(request.getParameter("direccion")).thenReturn("Calle Falsa 123");
    when(request.getParameter("tipoEmpleado")).thenReturn("1"); // Tipo de empleado

    // Simulamos el request dispatcher
    when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

    // Ejecutamos el método doPost del servlet
    empleadoServlet.doPost(request, response);

    // Verificamos que los parámetros fueron obtenidos correctamente del request
    verify(request).getParameter("nombre");
    verify(request).getParameter("apellido");
    verify(request).getParameter("cedula");
    verify(request).getParameter("usuario");
    verify(request).getParameter("password");
    verify(request).getParameter("email");
    verify(request).getParameter("telefono");
    verify(request).getParameter("direccion");
    verify(request).getParameter("tipoEmpleado");

    // Verificamos que el dispatcher redirige correctamente a la página app.jsp
    verify(dispatcher).forward(request, response);
}


    @Test
    public void testActualizarEmpleado() throws ServletException, IOException {
        // Simulamos los parámetros enviados para actualizar un empleado
        when(request.getParameter("accion")).thenReturn("actualizar");
        when(request.getParameter("idEmpleado")).thenReturn("19"); // ID del empleado a actualizar
        when(request.getParameter("nombre")).thenReturn("EmpleadoActualizado");
        when(request.getParameter("apellido")).thenReturn("ApellidoActualizado");
        when(request.getParameter("cedula")).thenReturn("9322221");
        when(request.getParameter("usuario")).thenReturn("empleadoactualizado");
        when(request.getParameter("password")).thenReturn("newpassword123");
        when(request.getParameter("email")).thenReturn("actualizado@test.com");
        when(request.getParameter("telefono")).thenReturn("123456789");
        when(request.getParameter("direccion")).thenReturn("Nueva Calle 456");
        when(request.getParameter("fechaRegistro")).thenReturn("2024-09-23"); // Fecha de registro
        when(request.getParameter("tipoEmpleado")).thenReturn("2"); // Tipo de empleado

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet
        empleadoServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idEmpleado");
        verify(request).getParameter("nombre");
        verify(request).getParameter("apellido");
        verify(request).getParameter("cedula");
        verify(request).getParameter("usuario");
        verify(request).getParameter("password");
        verify(request).getParameter("email");
        verify(request).getParameter("telefono");
        verify(request).getParameter("direccion");
        verify(request).getParameter("fechaRegistro");
        verify(request).getParameter("tipoEmpleado");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testEliminarEmpleado() throws ServletException, IOException {
        // Simulamos los parámetros enviados para eliminar un empleado
        when(request.getParameter("accion")).thenReturn("eliminar");
        when(request.getParameter("idEmpleado")).thenReturn("15"); // ID del empleado a eliminar

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doGet del servlet
        empleadoServlet.doGet(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idEmpleado");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }
}
