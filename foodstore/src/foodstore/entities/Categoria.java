package foodstore.entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    private static long contadorCategorias = 0;
    private String nombre;
    private String descripcion;
    private List<Producto> productos;
    
    
    public Categoria (String nombre, String descripcion) {
        super(++contadorCategorias);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.productos = new ArrayList<>();

    }
    
    public void agregarProducto(Producto producto) {
        //  Validar que el producto no sea null, que pertenezca a la categoría y que no haya sido agregado previamente
        if (producto != null && !this.productos.contains(producto)) {
            this.productos.add(producto);           
            if (!producto.getCategoria().equals(this)) { // evitar referencia circular (si el flujo inicio con setCategoria del producto, corta acá. Lo mismo si se cambió de categoría
                producto.setCategoria(this);
            }
        } else {
            System.out.println("Producto inválido");            
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.trim().length() > 0) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre categoría inválido");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.trim().length() > 0) {
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("Descripción categoría inválido");
        }
    }

    public List<Producto> getProductos() {
        return List.copyOf(this.productos);
    }

    private void setProductos(List<Producto> productos) {
        if (this.productos == null) {            
            this.productos = productos;
        } else {
            System.out.println("Lista productos ya inicializada para categoría");
        }
    }

    @Override
    public String toString() {
        
        String listaProductos = "";
        
        if (this.productos.size() > 0) {
            for (Producto p : this.productos) {
                listaProductos += " - " + p.getNombre() + "\n";
            }
        } else {
            listaProductos = "Sin productos";
        }
        
        String cadena = String.format("Categoría #%d: %s \n Descripción: %s \n Productos: \n%s \n Fecha creación: %s \n", this.getId(), nombre, descripcion, listaProductos, this.getCreatedAt().toString());
        
        return cadena;
    }
    
    
    
    
}
