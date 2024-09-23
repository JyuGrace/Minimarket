package dominio;


public class Usuario {
    private int idUsuario;
    private String tipo;

    public Usuario() {
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String tipo) {
        this.tipo = tipo;
    }

    public Usuario(int idUsuario, String tipo) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", tipo=" + tipo + '}';
    }
    
    
            
}
