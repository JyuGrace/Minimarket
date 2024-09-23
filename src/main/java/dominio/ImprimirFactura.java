package dominio;

public class ImprimirFactura {
    private String nombreProducto;
    private int cantidad;
    private Float precioUnitario;
    private Float subtotal;

    public ImprimirFactura(String nombreProducto, int cantidad, Float precioUnitario, Float subtotal) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "ImprimirFactura{" + "nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }
    
    
    
}
