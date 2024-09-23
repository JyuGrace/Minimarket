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

public class ServletControladorClienteTest {

    @InjectMocks
    private ServletControladorClientes clienteServlet; // Clase a probar

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
    public void testInsertarCliente() throws ServletException, IOException {
        // Simulamos los parámetros enviados por el formulario para un cliente
        when(request.getParameter("accion")).thenReturn("insertar");
        when(request.getParameter("nombre")).thenReturn("ClienteTest");
        when(request.getParameter("apellido")).thenReturn("ApellidoTest");
        when(request.getParameter("cedula")).thenReturn("123456789");
        when(request.getParameter("email")).thenReturn("cliente@test.com");
        when(request.getParameter("telefono")).thenReturn("987654321");
        when(request.getParameter("direccion")).thenReturn("Calle Falsa 123");

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet
        clienteServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("nombre");
        verify(request).getParameter("apellido");
        verify(request).getParameter("cedula");
        verify(request).getParameter("email");
        verify(request).getParameter("telefono");
        verify(request).getParameter("direccion");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testActualizarCliente() throws ServletException, IOException {
        // Simulamos los parámetros enviados para actualizar un cliente
        when(request.getParameter("accion")).thenReturn("actualizar");
        when(request.getParameter("idCliente")).thenReturn("29"); // ID del cliente a actualizar
        when(request.getParameter("nombre")).thenReturn("ClienteActualizado");
        when(request.getParameter("apellido")).thenReturn("ApellidoActualizado");
        when(request.getParameter("cedula")).thenReturn("987654321");
        when(request.getParameter("email")).thenReturn("actualizado@test.com");
        when(request.getParameter("telefono")).thenReturn("123456789");
        when(request.getParameter("direccion")).thenReturn("Nueva Calle 456");

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet
        clienteServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idCliente");
        verify(request).getParameter("nombre");
        verify(request).getParameter("apellido");
        verify(request).getParameter("cedula");
        verify(request).getParameter("email");
        verify(request).getParameter("telefono");
        verify(request).getParameter("direccion");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testEliminarCliente() throws ServletException, IOException {
        // Simulamos los parámetros enviados para eliminar un cliente
        when(request.getParameter("accion")).thenReturn("eliminar");
        when(request.getParameter("idCliente")).thenReturn("28"); // ID del cliente a eliminar

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doGet del servlet
        clienteServlet.doGet(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idCliente");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }
}
