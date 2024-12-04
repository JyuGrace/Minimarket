package controladores;

import controladores.ServletControladorProductos;
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

public class ServletControladorProductoTest {

    @InjectMocks
    private ServletControladorProductos productoServlet; //clase a probar (ServletProductos)

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
    public void testInsertarProducto() throws ServletException, IOException {
        // Simulamos los parámetros enviados por el formulario para un producto completo
        when(request.getParameter("accion")).thenReturn("insertar");
        when(request.getParameter("nombre")).thenReturn("ProductoTest");
        when(request.getParameter("stock")).thenReturn("50");
        when(request.getParameter("precio")).thenReturn("199.99");
        when(request.getParameter("descripcion")).thenReturn("Descripción del Producto Test");
        when(request.getParameter("categoria")).thenReturn("3");  // idCategoria de prueba

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet para simular la inserción
        productoServlet.doPost(request, response); // Usa la instancia correcta del servlet

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("nombre");
        verify(request).getParameter("stock");
        verify(request).getParameter("precio");
        verify(request).getParameter("descripcion");
        verify(request).getParameter("categoria");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }
    
     @Test
    public void testActualizarProducto() throws ServletException, IOException {
        // Simulamos los parámetros enviados para actualizar un producto
        when(request.getParameter("accion")).thenReturn("actualizar");
        when(request.getParameter("idProducto")).thenReturn("15"); // ID del producto a actualizar
        when(request.getParameter("nombre")).thenReturn("ProductoActualizado");
        when(request.getParameter("stock")).thenReturn("100");
        when(request.getParameter("precio")).thenReturn("299.99");
        when(request.getParameter("descripcion")).thenReturn("Descripción actualizada");
        when(request.getParameter("categoria")).thenReturn("3"); // ID de categoría
        when(request.getParameter("idProveedor")).thenReturn("1"); // ID de proveedor

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doPost del servlet para simular la actualización
        productoServlet.doPost(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idProducto");
        verify(request).getParameter("nombre");
        verify(request).getParameter("stock");
        verify(request).getParameter("precio");
        verify(request).getParameter("descripcion");
        verify(request).getParameter("categoria");
        verify(request).getParameter("idProveedor");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testEliminarProducto() throws ServletException, IOException {
        // Simulamos los parámetros enviados para eliminar un producto
        when(request.getParameter("accion")).thenReturn("eliminar");
        when(request.getParameter("idProducto")).thenReturn("20"); // ID del producto a eliminar

        // Simulamos el request dispatcher
        when(request.getRequestDispatcher("app.jsp")).thenReturn(dispatcher);

        // Ejecutamos el método doGet del servlet para simular la eliminación
        productoServlet.doGet(request, response);

        // Verificamos que los parámetros fueron obtenidos correctamente del request
        verify(request).getParameter("idProducto");

        // Verificamos que el dispatcher redirige correctamente a la página app.jsp
        verify(dispatcher).forward(request, response);
    }
}
