package dominio;

public class DetalleFactura {
    private int idDetalleFactura;
    private int cantidad;
    private Float precio;
    private Float subtotal;
    private Float iva;
    private int idFactura;
    private int idProducto;

    public DetalleFactura() {
    }

    public DetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public DetalleFactura(int cantidad, Float precio, Float iva, Float subtotal, int idFactura, int idProducto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.iva = iva;
        this.subtotal = subtotal;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
    }

    public DetalleFactura(int idDetalleFactura, int cantidad, Float precio, Float iva, Float subtotal, int idFactura, int idProducto) {
        this.idDetalleFactura = idDetalleFactura;
        this.cantidad = cantidad;
        this.precio = precio;
        this.iva = iva;
        this.subtotal = subtotal;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "idDetalleFactura=" + idDetalleFactura + ", cantidad=" + cantidad + ", precio=" + precio + ", subtotal=" + subtotal + ", iva=" + iva + ", idFactura=" + idFactura + ", idProducto=" + idProducto + '}';
    }

    
    
    
    
}
