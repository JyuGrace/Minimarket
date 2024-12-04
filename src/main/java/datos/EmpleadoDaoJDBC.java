package datos;

import dominio.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class EmpleadoDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_empleado, nombre, apellido, cedula, usuario, password, email, telefono, direccion, fecha_registro, id_usuario FROM minimarket.empleado;";
    private static final String SQL_SELECT_BY_ID = "SELECT id_empleado, nombre, apellido, cedula, usuario, password, email, telefono, direccion, fecha_registro, id_usuario FROM minimarket.empleado WHERE id_empleado = ?;";
    private static final String SQL_SELECT_BY_NAME = "SELECT id_empleado, nombre, apellido, cedula, usuario, password, email, telefono, direccion, fecha_registro, id_usuario FROM minimarket.empleado WHERE nombre = ?;";
    private static final String SQL_INSERT = "INSERT INTO minimarket.empleado (nombre, apellido, cedula, usuario, password, email, telefono, direccion, fecha_registro, id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE minimarket.empleado SET nombre=?, apellido=?, cedula=?, usuario=?, password=?, email=?, telefono=?, direccion=?, fecha_registro=?, id_usuario=? WHERE id_empleado = ?;";
    private static final String SQL_DELETE = "DELETE FROM minimarket.empleado WHERE id_empleado = ?";
    private static final String SQL_SELECT_BY_USER_PASS = "SELECT * FROM minimarket.empleado WHERE usuario=? AND password=?;";

    public EmpleadoDaoJDBC() {
    }
    
    
    public List<Empleado> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        List<Empleado> empleados = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idEmpleado = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cedula = rs.getString("cedula");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Date fechaRegistro = rs.getDate("fecha_registro");
                int tipoEmpleado = rs.getInt("id_usuario");
                empleado = new Empleado(idEmpleado, nombre, apellido, cedula, usuario, password, email, telefono, direccion, fechaRegistro, tipoEmpleado);
                empleados.add(empleado);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return empleados;
    }
    
    public Empleado encontrarById(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, empleado.getIdEmpleado());
            rs = stmt.executeQuery();
            rs.next();
            
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellido(rs.getString("apellido"));
            empleado.setCedula(rs.getString("cedula"));
            empleado.setUsuario(rs.getString("usuario"));
            empleado.setPassword(rs.getString("password"));
            empleado.setEmail(rs.getString("email"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setFechaRegistro(rs.getDate("fecha_registro"));
            empleado.setTipoEmpleado(rs.getInt("id_usuario"));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return empleado;
    }
    
    public Empleado encontrarByName(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, empleado.getNombre());
            rs = stmt.executeQuery();
            rs.next();
            
            empleado.setIdEmpleado(rs.getInt("id_empleado"));
            empleado.setApellido(rs.getString("apellido"));
            empleado.setCedula(rs.getString("cedula"));
            empleado.setUsuario(rs.getString("usuario"));
            empleado.setPassword(rs.getString("password"));
            empleado.setEmail(rs.getString("email"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setFechaRegistro(rs.getDate("fecha_registro"));
            empleado.setTipoEmpleado(rs.getInt("id_usuario"));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return empleado;
    }
    
    public int insertar(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCedula());
            stmt.setString(4, empleado.getUsuario());
            stmt.setString(5, empleado.getPassword());
            stmt.setString(6, empleado.getEmail());
            stmt.setString(7, empleado.getTelefono());
            stmt.setString(8, empleado.getDireccion());
            stmt.setDate(9, empleado.getFechaRegistro());
            stmt.setInt(10, empleado.getTipoEmpleado());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int actualizar(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCedula());
            stmt.setString(4, empleado.getUsuario());
            stmt.setString(5, empleado.getPassword());
            stmt.setString(6, empleado.getEmail());
            stmt.setString(7, empleado.getTelefono());
            stmt.setString(8, empleado.getDireccion());
            stmt.setDate(9, empleado.getFechaRegistro());
            stmt.setInt(10, empleado.getTipoEmpleado());
            stmt.setInt(11, empleado.getIdEmpleado());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(Empleado empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getIdEmpleado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public boolean validarUsuario(String usuario, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validar = false;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_USER_PASS);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if(rs.next()){
                validar = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return validar;
    }
    
    public Empleado encontrarByUserPass(String usuario, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_USER_PASS);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            rs.next();
            
            int idEmpleado = rs.getInt("id_empleado");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int idUsuario = rs.getInt("id_usuario");
            empleado = new Empleado(idEmpleado, nombre, apellido, idUsuario);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return empleado;
    }
}
