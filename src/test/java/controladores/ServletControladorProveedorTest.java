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

public class ServletControladorProveedorTest {

    @InjectMocks
    private ServletControladorProveedores proveedorServlet; // Clase a probar

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // Usamos Mockito para inicializar los mocks
    }

    @Test
    public void testInsertarProveedor() throws ServletException, IOException {
        // Simulamos los parámetros enviados por el formulario para un proveedor
        when(request.getParameter("accion")).thenReturn("insertar");
        when(request.getParameter("nombre")).thenReturn("ProveedorTest");
        when(request.getParameter("apellido")).thenReturn("ApellidoTest");
        when(request.getParameter("nombre_empresa")).thenReturn("EmpresaTest");
        when(request.getParameter("email")).thenReturn("proveedor@test.com");
        when(request.getParameter("telefono")).thenReturn("125556789");

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet
        proveedorServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("nombre");
        verify(request).getParameter("apellido");
        verify(request).getParameter("nombre_empresa");
        verify(request).getParameter("email");
        verify(request).getParameter("telefono");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testActualizarProveedor() throws ServletException, IOException {
        // Simulamos los parámetros enviados para actualizar un proveedor
        when(request.getParameter("accion")).thenReturn("actualizar");
        when(request.getParameter("idProveedor")).thenReturn("11"); // ID del proveedor a actualizar
        when(request.getParameter("nombre")).thenReturn("ProveedorActualizado");
        when(request.getParameter("apellido")).thenReturn("ApellidoActualizado");
        when(request.getParameter("nombre_empresa")).thenReturn("EmpresaActualizada");
        when(request.getParameter("email")).thenReturn("actualizado@test.com");
        when(request.getParameter("telefono")).thenReturn("987654321");

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet
        proveedorServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idProveedor");
        verify(request).getParameter("nombre");
        verify(request).getParameter("apellido");
        verify(request).getParameter("nombre_empresa");
        verify(request).getParameter("email");
        verify(request).getParameter("telefono");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testEliminarProveedor() throws ServletException, IOException {
        // Simulamos los parámetros enviados para eliminar un proveedor
        when(request.getParameter("accion")).thenReturn("eliminar");
        when(request.getParameter("idProveedor")).thenReturn("12"); // ID del proveedor a eliminar

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doGet del servlet
        proveedorServlet.doGet(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idProveedor");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }
}
