
package dominio;


public class CategoriaProducto {
    private int idCategoria;
    private String nombre;
    private String descripcion;
    
    //constrcutores
    public CategoriaProducto(){
    }

    public CategoriaProducto(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaProducto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public CategoriaProducto(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    //getters and setters

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CategoriaProducto{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
