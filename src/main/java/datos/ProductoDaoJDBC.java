
package datos;

import dominio.Producto;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDaoJDBC {
    
    private static final String SQL_SELECT = "SELECT id_producto, nombre, stock, precio, descripcion, id_proveedor, id_categoria FROM minimarket.producto;";
    private static final String SQL_SELECT_STOCK_AVAILABLE = "SELECT * FROM minimarket.producto WHERE stock > ?;";
    private static final String SQL_SELECT_STOCK_LOW = "SELECT * FROM minimarket.producto WHERE stock < ?;";
    private static final String SQL_SELECT_BY_ID = "SELECT id_producto, nombre, stock, precio, descripcion, id_proveedor, id_categoria FROM minimarket.producto WHERE id_producto = ?;";
    private static final String SQL_SELECT_BY_NAME = "SELECT id_producto, nombre, stock, precio, descripcion, id_proveedor, id_categoria FROM minimarket.producto WHERE nombre = ?;";
    private static final String SQL_INSERT = "INSERT INTO minimarket.producto (nombre, stock, precio, descripcion, id_proveedor, id_categoria) VALUES (?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE minimarket.producto SET nombre=?, stock=?, precio=?, descripcion=?, id_proveedor=?, id_categoria=? WHERE id_producto=?;";
    private static final String SQL_DELETE = "DELETE FROM minimarket.producto WHERE id_producto=?;";
    
    
    public List<Producto> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                int idProveedor = rs.getInt("id_proveedor");
                int idCategoria = rs.getInt("id_categoria");
                producto = new Producto(idProducto, nombre, stock, precio, descripcion, idProveedor, idCategoria);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return productos;
    }
    
    public List<Producto> listarStockDisponible(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        int stockParameter = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_STOCK_AVAILABLE);
            stmt.setInt(1, stockParameter);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                int idProveedor = rs.getInt("id_proveedor");
                int idCategoria = rs.getInt("id_categoria");
                producto = new Producto(idProducto, nombre, stock, precio, descripcion, idProveedor, idCategoria);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return productos;
    }
    
    public List<Producto> listarProductosLowStock(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        int stockParameter = 10;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_STOCK_LOW);
            stmt.setInt(1, stockParameter);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                int idProveedor = rs.getInt("id_proveedor");
                int idCategoria = rs.getInt("id_categoria");
                producto = new Producto(idProducto, nombre, stock, precio, descripcion, idProveedor, idCategoria);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return productos;
    }
    
    public Producto encontrarById(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, producto.getIdProducto());
            rs = stmt.executeQuery();
            rs.next();
            
            producto.setNombre(rs.getString("nombre"));
            producto.setStock(rs.getInt("stock"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setIdProveedor(rs.getInt("id_proveedor"));
            producto.setIdCategoria(rs.getInt("id_categoria"));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return producto;
    }
    
    public String encontrarNombreProductoById(int idProducto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String productoNombre = "";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idProducto);
            rs = stmt.executeQuery();
            rs.next();
            productoNombre = rs.getString("nombre");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return productoNombre;
    }
    
    public Producto encontrarByName(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, producto.getNombre());
            rs = stmt.executeQuery();
            rs.next();
            
            producto.setIdProducto(rs.getInt("id_producto"));
            producto.setStock(rs.getInt("stock"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setIdProveedor(rs.getInt("id_proveedor"));
            producto.setIdCategoria(rs.getInt("id_categoria"));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return producto;
    }
    
    public int insertar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getStock());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5,producto.getIdProveedor());
            stmt.setInt(6, producto.getIdCategoria());
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int actualizar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getStock());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5, producto.getIdProveedor());
            stmt.setInt(6, producto.getIdCategoria());
            stmt.setInt(7, producto.getIdProducto());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(Producto producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
           Conexion.close(stmt);
            Conexion.close(conn); 
        }
        return rows;
    }
    
    
}
