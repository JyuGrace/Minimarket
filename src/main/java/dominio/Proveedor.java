package dominio;


public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String apellido;
    private String nombreEmpresa;
    private String email;
    private String telefono;
    
    //constructores

    public Proveedor() {
    }

    public Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public Proveedor(String nombre, String apellido, String nombreEmpresa, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
        this.telefono = telefono;
    }

    public Proveedor(int idProveedor, String nombre, String apellido, String nombreEmpresa, String email, String telefono) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
        this.telefono = telefono;
    }
    
    //getters and setters

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + ", nombre=" + nombre + ", apellido=" + apellido + ", nombreEmpresa=" + nombreEmpresa + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
}
