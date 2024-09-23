package dominio;

import java.sql.Date;

public class Factura {
    private int idFactura;
    private Date fecha;
    private float total;
    private int idCliente;
    private int idEmpleado;
    private String nombreCliente;
    private String apellidoCliente;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    
    //constructores
    public Factura(){
        
    }

    public Factura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Date fecha) {
        this.fecha = fecha;
    }
    

    public Factura(Date fecha, float total, int idCliente, int idEmpleado) {
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }
    

    public Factura(int idFactura, Date fecha, float total, int idCliente, int idEmpleado) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public Factura(int idFactura, Date fecha, float total, int idCliente, int idEmpleado, String nombreCliente, String apellidoCliente, String nombreEmpleado, String apellidoEmpleado) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
    }
    

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }
    

    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", fecha=" + fecha + ", total=" + total + ", idCliente=" + idCliente + ", idEmpleado=" + idEmpleado + '}';
    }
    
    
    
}
