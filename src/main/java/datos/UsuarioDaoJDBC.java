package datos;

import dominio.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_usuario, tipo FROM minimarket.usuario;";
    
    public List<Usuario> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String tipo = rs.getString("tipo");
                usuario = new Usuario(idUsuario, tipo);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }
}
