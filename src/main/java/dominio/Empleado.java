package dominio;

import java.sql.Date;


public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String cedula;
    private String usuario;
    private String password;
    private String email;
    private String telefono;
    private String direccion;
    private Date fechaRegistro;
    private int tipoEmpleado;
    
    
    //constructores
    public Empleado() {
    }

    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
    
    public Empleado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Empleado(int idEmpleado, String nombre, String apellido) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Empleado(String nombre, String apellido, String cedula, String usuario, String password, String email, String telefono, String direccion, Date fechaRegistro, int tipoEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado(int idEmpleado, String nombre, String apellido, String cedula, String usuario, String password, String email, String telefono, String direccion, Date fechaRegistro, int tipoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.tipoEmpleado = tipoEmpleado;
    }
    
    //getters and setters

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(int tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + ", usuario=" + usuario + ", password=" + password + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", tipoEmpleado=" + tipoEmpleado + '}';
    }

    
    
    
}
