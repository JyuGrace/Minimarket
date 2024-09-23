package datos;

import dominio.DetalleFactura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DetalleFacturaDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_detalle_factura, cantidad, precio_unitario, iva, subtotal, id_factura, id_producto FROM minimarket.detalle_factura;";
    private static final String SQL_SELECT_BY_ID_FACTURA = "SELECT id_detalle_factura, cantidad, precio_unitario, iva, subtotal, id_factura, id_producto FROM minimarket.detalle_factura WHERE id_factura = ?;";
    private static final String SQL_INSERT = "INSERT INTO minimarket.detalle_factura (cantidad, precio_unitario, iva, subtotal, id_factura, id_producto) VALUES (?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE minimarket.detalle_factura SET cantidad=?, precio_unitario=?, iva=?, subtotal=?, id_factura=?, id_producto=? WHERE id_detalle_factura=?;";
    private static final String SQL_DELETE = "DELETE minimarket.detalle_factura WHERE detalle_factura = ?;";
    
    public List<DetalleFactura> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleFactura detalleFactura = null;
        List<DetalleFactura> detalleFacturas = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idDetalleFactura = rs.getInt("id_detalle_factura");
                int cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio_unitario");
                Float iva = rs.getFloat("iva");
                Float subtotal = rs.getFloat("subtotal");
                int idFactura = rs.getInt("id_factura");
                int id_producto = rs.getInt("id_producto");
                detalleFactura = new DetalleFactura(idDetalleFactura, cantidad, precio, iva, subtotal, idFactura, id_producto);
                detalleFacturas.add(detalleFactura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalleFacturas;
    }
    
    public List<DetalleFactura> listarByIdFactura(int idFactura){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleFactura detalleFactura = null;
        List<DetalleFactura> detalleFacturas = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_FACTURA);
            stmt.setInt(1, idFactura);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idDetalle = rs.getInt("id_detalle_factura");
                int cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio_unitario");
                Float iva = rs.getFloat("iva");
                Float subtotal = rs.getFloat("subtotal");
                int idFac = rs.getInt("id_factura");
                int id_producto = rs.getInt("id_producto");
                detalleFactura = new DetalleFactura(idDetalle, cantidad, precio, iva, subtotal, idFactura, id_producto);
                //detalleFactura = new DetalleFactura(cantidad, precio, iva, subtotal, idFac, id_producto);
                detalleFacturas.add(detalleFactura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalleFacturas;
    }
    
    public int insertar(DetalleFactura detalleFactura){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, detalleFactura.getCantidad());
            stmt.setFloat(2, detalleFactura.getPrecio());
            stmt.setFloat(3, detalleFactura.getIva());
            stmt.setFloat(4, detalleFactura.getSubtotal());
            stmt.setInt(5, detalleFactura.getIdFactura());
            stmt.setInt(6, detalleFactura.getIdProducto());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int actualizar(DetalleFactura detalleFactura){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, detalleFactura.getCantidad());
            stmt.setFloat(2, detalleFactura.getPrecio());
            stmt.setFloat(3, detalleFactura.getIva());
            stmt.setFloat(4, detalleFactura.getSubtotal());
            stmt.setInt(5, detalleFactura.getIdFactura());
            stmt.setInt(6, detalleFactura.getIdProducto());
            stmt.setInt(7, detalleFactura.getIdDetalleFactura());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(DetalleFactura detalleFactura){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, detalleFactura.getIdDetalleFactura());
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
