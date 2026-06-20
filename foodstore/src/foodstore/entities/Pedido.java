package foodstore.entities;

import foodstore.enums.Estado;
import foodstore.enums.FormaPago;
import foodstore.interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {
    private static long contadorPedidos = 0;
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;    
    private Usuario usuario; 
    private List<DetallePedido> detalles; 

    public Pedido (Estado estado, FormaPago formaPago, Usuario usuario) {
        super(++contadorPedidos);
        this.setEstado(estado);
        this.setFormaPago(formaPago);
        this.setUsuario(usuario);
        this.fecha = LocalDate.now();
        this.detalles = new ArrayList<>();
    }
    
    @Override
    public void calcularTotal() {
        double total = 0;
        
        for (DetallePedido detalle: this.detalles) {
            total += detalle.getSubtotal();
        }        
        this.setTotal(total);
    }
    

    
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = this.findDetallePedidoByProducto(producto);
        // en caso de que se agregue un producto ya existente en el pedido, modificar unicamente la cantidad. Caso contrario, agregarlo como nuevo
        if (detalle != null) {
            System.out.println("Producto ya incluido en el pedido. Modificando cantidad...\n");
            detalle.setCantidad(cantidad);            
        } else {
            DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
            this.detalles.add(nuevoDetalle);
        }        
        this.calcularTotal();
        
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido d: this.detalles) {
            if (d.getProducto().getId() == producto.getId()) {
                return d;
            }
        }
        return null;
    }
    
    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalle = this.findDetallePedidoByProducto(producto);
        if (detalle != null) {
            this.detalles.remove(detalle);            
            this.calcularTotal();
        }
        
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha != null) {            
            this.fecha = fecha;
        } else {
            throw new IllegalArgumentException("Fecha pedido inválida");
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {            
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado pedido inválido");
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total >= 0) {            
            this.total = total;
        } else {
            throw new IllegalArgumentException("Total pedido inválido");
        }
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        if (formaPago != null) {            
            this.formaPago = formaPago;
        } else {
            throw new IllegalArgumentException("FormaPago pedido inválida");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        // si el usuario no es null y el usuario actual asignado al pedido es distinto del que tiene actualmente (null u otro usuaario), agregarlo/sobreescribirlo
        if (usuario != null && this.usuario != usuario) {
            this.usuario = usuario;
            if (!usuario.getPedidos().contains(this)) { // chequea si agregarPedido no fue ya invocado (referencia circular)                
                usuario.agregarPedido(this);
            }
        } else {
            throw new IllegalArgumentException("Usuario pedido inválido");
        }
    }

    public List<DetallePedido> getDetalles() {
        return List.copyOf(detalles);
    }

    public void setDetalles(List<DetallePedido> detalles) {
        if (this.detalles == null) {            
            this.detalles = detalles;
        }  else {
            System.out.println("Lista detalles ya inicializada para pedido");
        }
    }

    @Override
    public String toString() {        
        
        String listaDetalles = "";
        
        if (this.detalles.size() > 0) {            
            for (DetallePedido d : this.detalles) {
                listaDetalles += " - " + d.toString() + "\n";
            }
        } else {
            listaDetalles = "Este pedido no tiene ningún detalle";
        }
         
        String cadena = String.format("Pedido #%d: | Fecha: %s | Estado: %s | FormaPago: %s | Usuario: #%d\n"
                + "-----------------------------------------------------------------------\n"
                + "%s \n"
                + "TOTAL DEL PEDIDO: $ %.2f\n"
                + "-----------------------------------------------------------------------\n",                
                this.getId(), this.getFecha(), this.getEstado(), this.getFormaPago(), this.getUsuario().getId(), listaDetalles, this.getTotal());

        return cadena;
    }
    
    
}
