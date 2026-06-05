package foodstore.entities;

import foodstore.enums.Rol;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Base {
   private static long contadorUsuarios = 0;
   private String nombre;
   private String apellido;
   private String mail;
   private String celular;
   private String contrasena;
   private Rol rol;
   //
   private List<Pedido> pedidos;
   
   public Usuario(String nombre, String apellido, String mail, String celular, String contrasena, Rol rol) {
       super(++contadorUsuarios);
       this.setNombre(nombre);
       this.setApellido(apellido);
       this.setMail(mail);
       this.setCelular(celular);
       this.setContrasena(contrasena);
       this.setRol(rol);
       this.pedidos = new ArrayList<>();
   }
   
    public void agregarPedido(Pedido pedido) {
        // Validar que el pedido no sea null, que pertenezca al usuario y que no haya sido agregado previamente
        if (pedido != null && !this.pedidos.contains(pedido)) {
            this.pedidos.add(pedido);
            // si el pedido no tiene seteado el usuario, setearlo (puede tenerlo ya seteado si setUsuario fue invocado antes(referencia circular)
            if (!pedido.getUsuario().equals(this)) {
                pedido.setUsuario(this);                
            }
        } else {
            System.out.println("Pedido inválido o ya agregado a la lista");
        }        
    }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.trim().length() > 0) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre usuario inválido");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido != null && apellido.trim().length() > 0) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("Apellido usuario inválido");
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail != null && mail.trim().length() > 0) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Mail usuario inválido");
        }
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if (celular != null && celular.trim().length() > 0) {
            this.celular = celular;
        } else {
            throw new IllegalArgumentException("Celular usuario inválido");
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena != null && contrasena.trim().length() > 0) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("Contraseña usuario inválido");
        }
        
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        if (rol != null) {            
            this.rol = rol;
        } else {
            throw new IllegalArgumentException("Rol usuario inválido");
        }
    }

    public List<Pedido> getPedidos() {
        return List.copyOf(pedidos);
    }

    public void setPedidos(List<Pedido> pedidos) {
        if (this.pedidos == null) {
            this.pedidos = pedidos;
        } else {
            System.out.println("Lista pedidos ya inicializada para usuario");
        }
    }
    
 

    @Override
    public String toString() {
                
        String listaPedidos = "";
        double totalAcumulado = 0;
        if (this.pedidos.size() > 0) {            
            for (Pedido p : this.pedidos) {
                listaPedidos += "> " + p.toString() + "\n";
                totalAcumulado += p.getTotal();
            }
        } else {
            listaPedidos = "Sin pedidos";
        }                
        
        String cadena = String.format(""
                + "===========================================================\n"
                + "Usuario #%d: %s %s | Mail: %s | Rol: %s \n"
                + "===========================================================\n"
                + " %s \n" // Pedidos (con detalle y total del pedido
                + "TOTAL ACUMULADO: $ %.2f \n" 
                + "===========================================================\n",
                this.getId(), nombre, apellido, mail, rol, listaPedidos, totalAcumulado);
        
        return cadena;
    }
    
    


   
    
   
}
