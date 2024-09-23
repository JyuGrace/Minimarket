package datos;

import dominio.CategoriaProducto;
import java.sql.*;
import java.util.*;


public class CategoriaProductoDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_categoria, nombre, descripcion FROM minimarket.categoria_producto;";
    
    public List<CategoriaProducto> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CategoriaProducto categoria = null;
        List<CategoriaProducto> categorias = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idCategoria = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                categoria = new CategoriaProducto(idCategoria, nombre, descripcion);
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return categorias;
    }
}
