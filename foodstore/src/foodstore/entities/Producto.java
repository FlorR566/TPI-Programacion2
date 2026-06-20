package foodstore.entities;

public class Producto extends Base {
    private static long contadorProductos = 0;
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    
    private Categoria categoria;
    
    public Producto (String nombre, double precio, String descripcion, int stock, String imagen, Categoria categoria) {
        super(++contadorProductos);
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setDescripcion(descripcion);
        this.setStock(stock);
        this.setImagen(imagen);
        this.setDisponible(true);
        
        this.setCategoria(categoria);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {       
         if (nombre != null && nombre.trim().length() > 0) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre producto inválido");
        }
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
         if (precio >= 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("Precio producto inválido");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.trim().length() > 0) {
            this.descripcion = descripcion;
        } else {
            throw new IllegalArgumentException("Descripción producto inválido");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
         if (stock >= 0) {
            this.stock = stock;
            if (stock == 0) {
                this.setDisponible(false);
            }
        } else {
            throw new IllegalArgumentException("Stock producto inválido");
        }
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        if (imagen != null && imagen.trim().length() > 0) {
            this.imagen = imagen;
        } else {
            throw new IllegalArgumentException("Imagen producto inválido");
        }
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        if (this.stock == 0) {
            this.disponible = false;
        } else {            
            this.disponible = disponible;
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria != null && this.categoria != categoria) {            
            this.categoria = categoria; // setear categoría primero, luego agregar a listado de producto de categoría (check específico ahí)
            if (!categoria.getProductos().contains(this)) {                
                categoria.agregarProducto(this); // evitar referencias circulares
            }
        } else {
            throw new IllegalArgumentException("Categoría del producto inválida");
        }
    }

    @Override
    public String toString() {
        // Formato en una sola fila compacta: [ID] Nombre | Categoría | Precio: $X | Stock: X
        return String.format("[ID: %d] %-20s | Cat: %-15s | Precio: $ %-8.2f | Stock: %d",
                this.getId(),
                this.getNombre(),
                this.categoria.getNombre(),
                this.getPrecio(),
                this.getStock()
        );
    }
    
    
}
