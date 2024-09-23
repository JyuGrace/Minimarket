package dominio;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String email;
    private String telefono;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String cedula, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente(int idCliente, String nombre, String apellido, String cedula, String email, String telefono, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    //constructor que  crea un cliente con su id
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    //constructor que crea un cliente con su cedula
    public Cliente(String cedula) {
        this.cedula = cedula;
    }

    public int getIdCliente() {
        return idCliente;
    }
    //este metodo no deberia estar aqui ya que no nos interesa cambiar el id del cliente, pero esta aqui para construi el objeto completo, no es necesario
    public int setIdCliente(int idCliente) {
        return this.idCliente = idCliente;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

}
