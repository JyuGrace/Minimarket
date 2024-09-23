package datos;

import dominio.Proveedor;
import java.sql.*;
import java.util.*;

public class ProveedorDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_proveedor, nombre, apellido, nombre_empresa, email, telefono FROM minimarket.proveedor;";
    private static final String SQL_SELECT_BY_ID = "SELECT id_proveedor, nombre, apellido, nombre_empresa, email, telefono FROM minimarket.proveedor WHERE id_proveedor = ?;";
    private static final String SQL_SELECT_BY_NAME = "SELECT id_proveedor, nombre, apellido, nombre_empresa, email, telefono FROM minimarket.proveedor WHERE nombre = ?;";
    private static final String SQL_INSERT = "INSERT INTO minimarket.proveedor (nombre, apellido, nombre_empresa, email, telefono) VALUES (?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE minimarket.proveedor SET nombre=?, apellido=?, nombre_empresa=?, email=?, telefono=? WHERE id_proveedor=?;";
    private static final String SQL_DELETE = "DELETE FROM minimarket.proveedor WHERE id_proveedor=?;";

    public List<Proveedor> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedor proveedor = null;
        List<Proveedor> proveedores = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProveedor = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String nombre_empresa = rs.getString("nombre_empresa");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                proveedor = new Proveedor(idProveedor, nombre, apellido, nombre_empresa, email, telefono);
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return proveedores;
    }

    public Proveedor encontrarById(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, proveedor.getIdProveedor());
            rs = stmt.executeQuery();
            rs.next();
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setApellido(rs.getString("apellido"));
            proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setTelefono(rs.getString("telefono"));

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return proveedor;
    }
    
    public Proveedor encontrarByName(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, proveedor.getNombre());
            rs = stmt.executeQuery();
            rs.next();
            proveedor.setIdProveedor(rs.getInt("id_proveedor"));
            proveedor.setApellido(rs.getString("apellido"));
            proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setTelefono(rs.getString("telefono"));
            

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return proveedor;
    }
    
    public int insertar(Proveedor proveedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getApellido());
            stmt.setString(3, proveedor.getNombreEmpresa());
            stmt.setString(4, proveedor.getEmail());
            stmt.setString(5, proveedor.getTelefono());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int actualizar(Proveedor proveedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getApellido());
            stmt.setString(3, proveedor.getNombreEmpresa());
            stmt.setString(4, proveedor.getEmail());
            stmt.setString(5, proveedor.getTelefono());
            stmt.setInt(6, proveedor.getIdProveedor());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(Proveedor proveedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, proveedor.getIdProveedor());
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
