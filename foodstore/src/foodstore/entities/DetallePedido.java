package foodstore.entities;

public class DetallePedido extends Base {
    private static long contadorDetalles = 0;
    private int cantidad;
    private double subtotal; 
    private Producto producto;
    
    public DetallePedido(int cantidad, Producto producto) {
        super(++contadorDetalles);
        this.setCantidad(cantidad);
        this.setProducto(producto);
        this.subtotal = this.calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
            if (this.getCantidad() != cantidad) {                
                this.cantidad = cantidad;
            } else {
                System.out.println("Misma cantidad. Sin cambios.\n");
            }
        } else {
            throw new IllegalArgumentException("Cantidad detalle pedido inválida");
        }
        if (this.producto != null && this.cantidad > 0) { // check necesario porque la cantidad se setea antes que el producto (del cual obtenemos el precio                       
            this.setSubtotal(this.calcularSubtotal()); // si cambia la cantidad, el subtotal puede cambiar               
        }             
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        if (subtotal > 0) {            
            this.subtotal = subtotal;
        } else {
            throw new IllegalArgumentException("Subtotal detalle pedido inválido");
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto != null) {            
            this.producto = producto;           
            if (this.cantidad > 0) { // check necesario en caso de que se cambie el orden en el constructor y se setee primero el producto y luego la cantidad                
                this.calcularSubtotal(); // si cambia un producto por otro, el subtotal puede cambiar
                
            } 
        } else {
            throw new IllegalArgumentException("Producto detalle pedido inválido");
        }
        if (this.producto != null && this.cantidad > 0) { // check necesario porque la cantidad se setea antes que el producto (del cual obtenemos el precio                       
            this.setSubtotal(this.calcularSubtotal()); // si cambia la cantidad, el subtotal puede cambiar               
        }
    }
    
    private double calcularSubtotal(){
        
        return this.producto.getPrecio() * this.getCantidad();
    }

    @Override
    public String toString() {
        
        String cadena = String.format("DetallePedido #%d: %s (Precio unitario: $ %.2f x %d items)  => Subtotal: $ %.2f", 
                this.getId(), this.producto.getNombre(), this.producto.getPrecio(), this.cantidad, this.subtotal);

        return cadena;
    }
    
    
    
    
    
}
