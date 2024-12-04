package controladores;

import datos.CategoriaProductoDaoJDBC;
import datos.ProductoDaoJDBC;
import dominio.CategoriaProducto;
import dominio.Producto;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorProductos")
public class ServletControladorProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "consultarAll":
                    this.consultarProductoAll(request, response);
                    break;
                case "consultarOne":
                    this.consultarProductoOne(request, response);
                    break;
                case "editar":
                    this.editarProducto(request, response);
                    break;
                case "seleccionarEliminar":
                    this.seleccionarEliminarProducto(request, response);
                    break;
                case "eliminar":
                    this.eliminarProducto(request, response);
                    this.actualizarProductosConLowStock(request, response);
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
                    this.insertarProducto(request, response);
                    this.actualizarProductosConLowStock(request, response);
                    break;
                case "actualizar":
                    this.actualizarProducto(request, response);
                    this.actualizarProductosConLowStock(request, response);
                    break;
                default:
                    response.sendRedirect("app.jsp");
            }
        }
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        //String categoria = request.getParameter("categoria");
        int stock = Integer.parseInt(request.getParameter("stock"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");
        int idProveedor = 0;
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        Producto producto = new Producto(nombre, stock, precio, descripcion, idProveedor, idCategoria);
        int registrosInsertados = new ProductoDaoJDBC().insertar(producto);
        System.out.println("registrosInsertados = " + registrosInsertados);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private void consultarProductoAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("productos");
        HttpSession sesion = request.getSession(false);
        List<Producto> productos = new ProductoDaoJDBC().listar();
        Map<Integer, String> categoriaMap = mapCategorias();
        // Asignar el nombre de la categoría a cada producto
        for (Producto producto : productos) {
            String categoriaNombre = categoriaMap.get(producto.getIdCategoria());
            producto.setCategoriaNombre(categoriaNombre);
        }
        System.out.println("productos = " + productos);
        sesion.setAttribute("productos", productos);
        request.getRequestDispatcher("app.jsp").forward(request, response);

    }

    private void consultarProductoOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        request.removeAttribute("productos");
        String nombre = request.getParameter("nombre");
        Producto producto = new Producto(nombre);
        Producto productoConsultarOne = new ProductoDaoJDBC().encontrarByName(producto);
        // Asignar el nombre de la categoría a cada producto
        Map<Integer, String> categoriaMap = mapCategorias();
        //System.out.println("categoriaMap = " + categoriaMap);
        String categoriaNombre = categoriaMap.get(productoConsultarOne.getIdCategoria());
        productoConsultarOne.setCategoriaNombre(categoriaNombre);
        
        //creamos una lista con el producto completo
        List<Producto> productos = new ArrayList<>();
        productos.add(productoConsultarOne);
        //System.out.println("categoriaNombre = " + categoriaNombre);
        //System.out.println("productoConsultarOne = " + productoConsultarOne);
        System.out.println("productos = " + productos);

        //enviamos la informacion de vuelta al jsp
        if(productoConsultarOne.getNombre() != null){
            sesion.setAttribute("productos", productos);
        } else {
            sesion.setAttribute("productos", null);
        }
        //sesion.setAttribute("productos", productos);
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }

    private Map<Integer, String> mapCategorias() {
        List<CategoriaProducto> categorias = new CategoriaProductoDaoJDBC().listar();
        // Crear un mapa de categorías para rápida búsqueda por id
        Map<Integer, String> categoriaMap = new HashMap<>();
        for (CategoriaProducto categoria : categorias) {
            categoriaMap.put(categoria.getIdCategoria(), categoria.getNombre());
        }
        return categoriaMap;
    }
    
    private void editarProducto(HttpServletRequest request, HttpServletResponse Response) throws ServletException, IOException{
        request.removeAttribute("mensaje");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new Producto(idProducto);
        Producto productoEditar = new ProductoDaoJDBC().encontrarById(producto);
        System.out.println("productoEditar = " + productoEditar);
        request.setAttribute("productoEditar", productoEditar);
        request.setAttribute("modal", "editar_producto");
        request.getRequestDispatcher("app.jsp").forward(request, Response);
    }
    
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.removeAttribute("mensaje");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombre = request.getParameter("nombre");
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Producto producto = new Producto(idProducto, nombre, stock, precio, descripcion, idProveedor, idCategoria);
        System.out.println("producto = " + producto);
        int registrosActualizados = new ProductoDaoJDBC().actualizar(producto);
        System.out.println("registrosActualizados = " + registrosActualizados);
        
        request.setAttribute("mensaje", "Producto actualizado con exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    
    private void seleccionarEliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.removeAttribute("modal");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new Producto(idProducto);
        Producto productoSeleccionarEliminar = new ProductoDaoJDBC().encontrarById(producto);
        System.out.println("productoSleccionarEliminar = " + productoSeleccionarEliminar);
        request.setAttribute("modal", "eliminar_producto");
        request.setAttribute("productoSeleccionarEliminar", productoSeleccionarEliminar);
        request.getRequestDispatcher("app.jsp").forward(request,response);
        
    }
    
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.removeAttribute("mensaje");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new Producto(idProducto);
        int productosEliminados = new ProductoDaoJDBC().eliminar(producto);
        System.out.println("productosEliminados = " + productosEliminados);
        request.setAttribute("mensaje", "Producto eliminado con Exito!");
        request.getRequestDispatcher("app.jsp").forward(request, response);
    }
    
    private List<Producto> actualizarProductosConLowStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession sesion = request.getSession(false);
        sesion.removeAttribute("productosLowStock");
        //consultamos lal ista de productos
        List<Producto> productos = new ProductoDaoJDBC().listarProductosLowStock();
        sesion.setAttribute("productosLowStock", productos);
        request.getRequestDispatcher("app.jsp").forward(request, response);
        return productos;
    }
}
