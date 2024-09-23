package datos;

import dominio.Factura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FacturaDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_factura, fecha, total, id_cliente, id_empleado FROM minimarket.factura;";
    private static final String SQL_SELECT_BY_ID = "SELECT id_factura, fecha, total, id_cliente, id_empleado FROM minimarket.factura WHERE id_factura = ?;";
    private static final String SQL_SELECT_BY_DATE_ALL = "SELECT id_factura, fecha, total, id_cliente, id_empleado FROM minimarket.factura;";
    private static final String SQL_SELECT_BY_DATE_ONE = "SELECT id_factura, fecha, total, id_cliente, id_empleado FROM minimarket.factura WHERE fecha = ?;";
    private static final String SQL_INSERT = "INSERT INTO minimarket.factura (fecha, total, id_cliente, id_empleado) VALUES(?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE minimarket.factura SET fecha=?, total=?, id_cliente=?, id_empleado=? FROM minimarket.factura WHERE id_factura = ?;";
    private static final String SQL_DELETE = "DELETE FROM minimarket.factura WHERE id_factura = ?;";
    
    public List<Factura> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura factura = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idFactura = rs.getInt("id_factura");
                Date fecha = rs.getDate("fecha");
                float total = rs.getFloat("total");
                int idCliente = rs.getInt("id_cliente");
                int idEmpleado = rs.getInt("id_empleado");
                factura = new Factura(idFactura, fecha, total, idCliente, idEmpleado);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }

    public Factura encontrarById(Factura factura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, factura.getIdFactura());
            rs = stmt.executeQuery();
            rs.next();
            factura.setFecha(rs.getDate("fecha"));
            factura.setTotal(rs.getFloat("total"));
            factura.setIdCliente(rs.getInt("id_cliente"));
            factura.setIdEmpleado(rs.getInt("id_empleado"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return factura;
    }
    
    public List<Factura> encontrarFacturaOnes(java.sql.Date fecha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura factura = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_DATE_ONE);
            stmt.setDate(1, fecha);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idFactura = rs.getInt("id_factura");
                java.sql.Date fechaSQL = rs.getDate("fecha");
                Float total = rs.getFloat("total");
                int idCliente = rs.getInt("id_cliente");
                int idEmpleado = rs.getInt("id_empleado");
                factura = new Factura(idFactura, fechaSQL, total, idCliente, idEmpleado);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }
    
    public List<Factura> encontrarFacturaAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura factura = null;
        List<Factura> facturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_DATE_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idFactura = rs.getInt("id_factura");
                Date fecha = rs.getDate("fecha");
                float total = rs.getFloat("total");
                int idCliente = rs.getInt("id_cliente");
                int idEmpleado = rs.getInt("id_empleado");
                factura = new Factura(idFactura, fecha, total, idCliente, idEmpleado);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return facturas;
    }
    
    public int insertar(Factura factura){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int lastInsertId = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, factura.getFecha());
            stmt.setFloat(2, factura.getTotal());
            stmt.setInt(3, factura.getIdCliente());
            stmt.setInt(4, factura.getIdEmpleado());
            int rows = stmt.executeUpdate();
            
            if(rows >0){
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    lastInsertId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lastInsertId;
    }
    
    public int actualizar(Factura factura){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareCall(SQL_UPDATE);
            stmt.setDate(1, factura.getFecha());
            stmt.setFloat(2, factura.getTotal());
            stmt.setInt(3, factura.getIdCliente());
            stmt.setInt(4, factura.getIdEmpleado());
            stmt.setInt(5, factura.getIdFactura());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(Factura factura){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, factura.getIdFactura());
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
