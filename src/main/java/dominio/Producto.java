package dominio;

public class Producto {

    private int idProducto;
    private String nombre;
    private int stock;
    private double precio;
    private String descripcion;
    private int idProveedor = 0;
    private int idCategoria;
    private String categoriaNombre;

    //constructores
    public Producto() {

    }

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }
    

    public Producto(String nombre, int stock, double precio, String descripcion, int idProveedor, int idCategoria) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }

    public Producto(int idProducto, String nombre, int stock, double precio, String descripcion, int idProveedor, int idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }

    
    //getters and setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
    
    
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + ", descripcion=" + descripcion + ", idProveedor=" + idProveedor + ", idCategoria=" + idCategoria + '}';
    }
    
    

}
