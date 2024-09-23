package dominio;

import java.sql.Date;


public class ConsultasFacturas {
    private int idFactura;
    private Date fecha;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String cedulaCliente;
    private Float total;

    public ConsultasFacturas() {
    }

    public ConsultasFacturas(int idFactura, Date fecha, String nombreEmpleado, String apellidoEmpleado, String cedulaCliente, Float total) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.cedulaCliente = cedulaCliente;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public Float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "ConsultasFacturas{" + "idFactura=" + idFactura + ", fecha=" + fecha + ", nombreEmpleado=" + nombreEmpleado + ", apellidoEmpleado=" + apellidoEmpleado + ", cedulaCliente=" + cedulaCliente + ", total=" + total + '}';
    }
    
    
    
                    
}
