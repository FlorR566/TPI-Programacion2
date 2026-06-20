
package foodstore;

import foodstore.entities.Base;
import foodstore.entities.Categoria;
import foodstore.entities.DetallePedido;
import foodstore.entities.Pedido;
import foodstore.entities.Producto;
import foodstore.entities.Usuario;
import foodstore.enums.Estado;
import foodstore.enums.FormaPago;
import foodstore.enums.Rol;
import foodstore.enums.TipoValidacion;
import foodstore.utils.Validador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<Categoria> categorias = new ArrayList<Categoria>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    
    public static void main(String[] args) {
        
        try {            
            Main.init();
        } catch (Exception e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
        } finally {
            sc.close();
        }
        
    }
    
    private static void init() {
        int opcionMenu = -1;
        System.out.println("\n========= SISTEMA DE PEDIDOS (FOOD STORE) ==========");
        
        Main.sugerirCargarDatos();

        do {
            Main.mostrarMenu();                       
            
            try {
                System.out.print("\nPor favor, seleccione una opción y pulse 'Enter': ");
                opcionMenu = Integer.parseInt(sc.nextLine().trim());                
                
                if (opcionMenu == 0) {
                    System.out.println("\n====================== Adiós =======================");
                } else if (Main.cumpleRangoValido(opcionMenu, 1, 4)) {
                    Main.procesarOpcionMenu(opcionMenu);
                } else {
                    System.out.println("\nOpción inválida.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("\nOpción inválida. Por favor ingrese un número del 1 al 4 (0 para salir)");
            } catch (RuntimeException e) {
                System.out.println("\nError. Por favor inténtelo nuevamente o comuníquese con el administrador");
            }

        } while (opcionMenu != 0); 

        
    }
    
    // Operaciones de menu
    
    private static void mostrarMenu() {
        System.out.println("\n================== Menú principal ==================\n");
        System.out.println("1. Categorías");
        System.out.println("2. Productos"); 
        System.out.println("3. Usuarios");
        System.out.println("4. Pedidos");
        System.out.println("0. Salir");
        
    }
    
    private static void mostrarSubMenu(int opcionMenu) {
        String tituloSubmenu= opcionMenu == 1 ? "CATEGORIAS" : opcionMenu == 2 ? "PRODUCTOS" : opcionMenu == 3 ? "USUARIOS" : "PEDIDOS";
        
        System.out.println("\n================ " + tituloSubmenu + " ===============");
        System.out.println("\n1. Listar");
        System.out.println("2. Crear"); 
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.println("0. Salir (volver Menu inicial)");
        
    }
    
    private static void procesarOpcionMenu(int opcionMenu) {
        int opcionSubmenu = -1;
        
        do {
            
            Main.mostrarSubMenu(opcionMenu);
            System.out.print("\nSeleccione una opción del submenú: ");
           try {
               opcionSubmenu = Integer.parseInt(Main.sc.nextLine().trim());
               if (Main.cumpleRangoValido(opcionSubmenu, 0, 4)) {                   
                   // Evaluar la opción de menu principal elegida (qué entidad fue elegida)
                   switch (opcionMenu) {
                       case 1: {
                           Main.procesarOpcionCRUD(opcionSubmenu, Categoria.class.getSimpleName());
                           break;
                       }
                       case 2: {
                           Main.procesarOpcionCRUD(opcionSubmenu, Producto.class.getSimpleName());
                           break;
                       }
                       case 3: {
                           Main.procesarOpcionCRUD(opcionSubmenu, Usuario.class.getSimpleName());
                           break;
                       }
                       case 4: {
                           Main.procesarOpcionCRUD(opcionSubmenu, Pedido.class.getSimpleName());
                           break;

                       }                    
                       default:
                            System.out.println("\nOpción inválida\n");
                            break;
                       }
               }
               
            } catch (NumberFormatException e) {
                System.out.println("\nOpción inválida. Por favor ingrese un número de las opciones sugeridas\n");
            } catch (RuntimeException e) {
                System.out.println("Error. Por favor inténtelo nuevamente o comuníquese con el administrador");
            }
            
        } while (opcionSubmenu != 0);
    }
    
    private static <T> void procesarOpcionCRUD(int opcionSubmenu, String nombreClase) {
        boolean errorDeEntrada;                
        
        do {
            errorDeEntrada = false; // asume que el input del usuario va a ser válido
    
            try {
                switch(opcionSubmenu) {
                    case 1: {
                        Main.listar(nombreClase);
                        break;
                    }
                    case 2: {
                        Main.crear(nombreClase);
                        break;
                    }
                    case 3:{
                        Main.editar(nombreClase);
                        break;
                    }
                    case 4: {
                        Main.eliminar(nombreClase);
                        break;
                    }                 
                    default:
//                        System.out.println("\nOpción inválida\n");
                        break;
                }                
            } catch (IllegalArgumentException e) {
                System.out.println("Valor/es ingresado/s inválido/s: " + e.getMessage());
                errorDeEntrada = true; // Si se ingresa cualquier input inválido, sigue en el loop
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Ha ocurrido un error. Por favor inténtelo nuevamente o comuníquese con el administrador");
                errorDeEntrada = false; // este error tiene que subir
            }            
        } while (errorDeEntrada);        
    }
        
    // Operaciones CRUD
    
    private static void listar(String nombreClase) {           
        switch(nombreClase) {
            case "Categoria": {
                Main.imprimirPorConsola(categorias, nombreClase);
                break;
            }
            case "Producto": {
                Main.imprimirPorConsola(productos, nombreClase);
                break;
            }
            case "Usuario": {
                Main.imprimirPorConsola(usuarios, nombreClase);
                break;
            }
            case "Pedido": {
                Main.imprimirPorConsola(pedidos, nombreClase);
                break;
            }
            default: {
                throw new RuntimeException("Error: Entidad desconocida");                
            }
        }
    }
    
    // La implementación varía según cada objeto concreto (argumentos específicos para tomar con el scanner)
    private static void crear(String objeto) {        
        switch(objeto) {
            case "Categoria": {
                String nombre;
                String descripcion;
                
                // Tomar argumentos
                System.out.println("\n========== CREAR CATEGORIA ==========\n");
                System.out.print("Ingrese el nombre: ");
                
                nombre = Main.sc.nextLine().trim();                
                        
                while (!Validador.validarCadena(nombre)) {
                    System.out.print("Nombre inválido. Inténtelo nuevamente: ");
                    nombre = Main.sc.nextLine().trim();
                }
                
                // Verificar que no exista ya la misma categoría               
                Base categoriaExistente = Main.findElementoByNombre(nombre, Categoria.class.getSimpleName());
                
                if (categoriaExistente != null) {
                    System.out.println("\nUna categoría con ese nombre ya existe");
                    return;
                }
                
                System.out.print("Ingrese la descripcion: ");

                // Seguir pidiendo datos sólo si no lo encontró previamente
                descripcion = Main.sc.nextLine().trim();
                        
                while (!Validador.validarCadena(descripcion)) {
                    System.out.print("Descripcion inválida. Inténtelo nuevamente: ");
                    descripcion = Main.sc.nextLine().trim();
                }

                Categoria nuevaCategoria = new Categoria(nombre,descripcion);
                
                // Agregar a lista correspondiente                
                Main.categorias.add(nuevaCategoria);
                
                System.out.println("\nNueva categoría agregada con ID " + nuevaCategoria.getId());

                break;
            }
            case "Producto": {
                // Check lógico necesario por la dependencia entre entidades
                if (Main.categorias.size() == 0) {
                    System.out.println("\nNo existen categorías creadas. Debe crear previamente al menos una categoría para su producto");
                    break;
                }
                // Tomar argumentos
                System.out.println("\n========== CREAR PRODUCTO ==========\n");
                System.out.print("Ingrese el nombre: ");
                String nombre = Main.sc.nextLine().trim();

                while (!Validador.validarCadena(nombre)) {
                    System.out.print("Nombre inválido. Inténtelo nuevamente: ");
                    nombre = Main.sc.nextLine().trim();
                }

                // Verificar que no exista ya el mismo producto
                Base productoExistente = Main.findElementoByNombre(nombre, Producto.class.getSimpleName());

                if (productoExistente != null) {
                    System.out.println("\nUn producto con ese nombre ya existe.");
                    return;
                }

                // ==================
                System.out.print("Ingrese el precio: ");
                String precioIngresado = Main.sc.nextLine().trim();

                while (!Validador.esDigitoNoNegativoValido(precioIngresado)) {
                    System.out.print("Precio inválido. Inténtelo nuevamente: ");
                    precioIngresado = Main.sc.nextLine().trim();
                }
                double precio = Double.parseDouble(precioIngresado);

                // ==================
                System.out.print("Ingrese la descripcion: ");
                String descripcion = Main.sc.nextLine().trim();

                while (!Validador.validarCadena(descripcion)) {
                    System.out.print("Descripción inválida. Inténtelo nuevamente: ");
                    descripcion = Main.sc.nextLine().trim();
                }

                // ==================
                System.out.print("Ingrese el stock: ");
                String stockIngresado = Main.sc.nextLine().trim();

                while (!Validador.esNumeroEnteroNoNegativoValido(stockIngresado)) {
                    System.out.print("Stock inválido. Inténtelo nuevamente: ");
                    stockIngresado = Main.sc.nextLine().trim();
                }
                int stock = Integer.parseInt(stockIngresado);

                // ==================
                // Autogenera el nombre de la imagen en base al nombre del producto
                String imagen = nombre.toLowerCase().replace(" ", "") + ".png";

                // ==================
                System.out.print("Ingrese el nombre de la categoria para este producto: ");
                String nombreCategoria = Main.sc.nextLine().trim();

                Base categoriaBuscada = Main.findElementoByNombre(nombreCategoria, Categoria.class.getSimpleName());

                if (categoriaBuscada == null) {
                    System.out.println("\nCategoría no encontrada");
                    return;
                }

                Categoria categoria = (Categoria) categoriaBuscada;   // Casteamos de tipo Base a Categoria

                // ==================
                Producto nuevoProducto = new Producto(nombre, precio, descripcion, stock, imagen, categoria );

                // Agregar a lista correspondiente
                Main.productos.add(nuevoProducto);

                System.out.println("\nNuevo producto agregado con ID " + nuevoProducto.getId());

                break;
            }
            case "Usuario": {
                
                break;
            }
            case "Pedido": {
                
                // Check lógico necesario por la dependencia entre entidades (para evitar bucles infinitos)
                if (Main.productos.size() == 0) {
                    System.out.println("\nNo existen productos en la tienda. Debe crear al menos un producto para generar un pedido");
                    break;
                }
                
                String formaPago;
                String idUsuario;
                String estado;
                
                // Tomar argumentos
                System.out.println("\n========== CREAR PEDIDO ==========\n");
                
                System.out.print("Ingrese forma de pago (TARJETA, TRANSFERENCIA, EFECTIVO): ");
                formaPago = Main.sc.nextLine().trim();
                        
                while (!Validador.esFormaDePagoValida(formaPago)) {
                    System.out.print("Forma de pago inválida. Ingrese TARJETA, TRANSFERENCIA o EFECTIVO: ");
                    formaPago = Main.sc.nextLine().trim();
                }
                
                System.out.print("Ingrese el estado (PENDIENTE, CONFIRMADO, TERMINADO, CANCELADO): ");            
                estado = Main.sc.nextLine().trim();
                        
                while (!Validador.esEstadoValido(estado)) {
                    System.out.print("Estado inválido. Ingrese PENDIENTE, CONFIRMADO, TERMINADO o CANCELADO: ");
                    estado = Main.sc.nextLine().trim();
                }
                
                System.out.println("\nEl pedido debe tener un usuario.");            
                
                idUsuario = Main.pedirIdValido();
                
                // TODO: depende de Usuario
                Base elemento = Main.findElementoById(Integer.parseInt(idUsuario), Usuario.class.getSimpleName());

                if (!(elemento instanceof Usuario)) {
                    System.out.println("\nUsuario no encontrado\n");
                    return;
                }
                Usuario usuario = (Usuario) elemento;

                if (usuario.isEliminado()) {
                    System.out.println("\nProducto ya eliminado\n");
                    return;
                }

                System.out.println("\nUsuario encontrado: ID " + usuario.getId() + "\n");
                
                
                // Convertir los strings al enum correspondiente
                Estado estadoNuevoPedido = Estado.valueOf(estado.toUpperCase());
                FormaPago formaPagoNuevoPedido = FormaPago.valueOf(formaPago.toUpperCase());
                // El pedido se crea pero aún no se agrega en memoria porque no tiene detalles asociados
                // La operación puede ser cancelada todavía
                
                Pedido nuevoPedido = new Pedido(estadoNuevoPedido, formaPagoNuevoPedido, usuario); 
                
                // Iniciar bucle para agregar detalles
                System.out.println("=== Detalles del pedido ===");              
                boolean reiterarPregunta = true;
                
                System.out.print("Su pedido no contiene detalles. Desea agregar uno? (S/N): ");

                do {
                    String respuesta = Main.sc.nextLine().trim();

                    if (respuesta.trim().toLowerCase().equals("s")) {
                        
                        // es necesario dar la opción de salir con 0 porque puede quedarse en un bucle infinito
                        // si no existen productos 
                        System.out.print("\nIngrese el nombre de un producto para el detalle (0 para salir): ");

                        String nombreProducto = Main.sc.nextLine().trim();                

                        while (!Validador.validarCadena(nombreProducto) && !nombreProducto.equals("0")) {
                            System.out.print("\nNombre inválido. Inténtelo nuevamente (0 para salir):  ");
                            nombreProducto = Main.sc.nextLine().trim();
                        }

                        if (nombreProducto.equals("0")) {
                            System.out.println("\nOperación cancelada");
                            return; // tiene que salir obligatoriamente porque no debe agregar el pedido (se hace después de este while)
                        }

                        // Verificar que exista el producto             
                        Base productoExistente = Main.findElementoByNombre(nombreProducto, Producto.class.getSimpleName());

                        // Verificar 3 casos clave: que el producto no sea null, si existe que esté disponible o tenga stock, que no haya sido agregado previamente al pedido
                        if (productoExistente == null) {
                            System.out.println("\nProducto no encontrado");
                            // deja seguir el flujo (vuelve arriba)
                            continue;
                        } else if (productoExistente != null && (!((Producto) productoExistente).isDisponible() || ((Producto) productoExistente).getStock() == 0)) {
                            System.out.println("\nProducto no disponible o sin stock");
                            // deja seguir el flujo (vuelve arriba)
                            continue;
                        } else if (nuevoPedido.findDetallePedidoByProducto((Producto) productoExistente) != null) {
                            System.out.println("Producto ya agregado al pedido");
                            // el producto ya agregado no se permite modificar la cantidad acá (exclusivo del flujo de editar)
                            continue;
                        }

                        Producto producto = (Producto) productoExistente;   // Casteamos de tipo Base a Producto

                        // Solicitar la cantidad
                        System.out.print("Ingrese la cantidad : ");
                        String cantidad = Main.sc.nextLine().trim();

                        // cantidad no puede ser 0
                        while (!Validador.esNumeroEnteroValido(cantidad)) {
                            System.out.print("Cantidad inválida. Inténtelo nuevamente: ");
                            cantidad = Main.sc.nextLine().trim();
                        }
                        int cantidadDetalle = Integer.parseInt(cantidad);

                        // Recién ahora se agrega el detalle al pedido
                        nuevoPedido.addDetallePedido(cantidadDetalle, producto);

                        System.out.print("Desea agregar otro producto? (S/N): ");
                        // toma el input directamente al inicio                        
                        
                    } else if (respuesta.trim().toLowerCase().equals("n")) {
                        System.out.println("\nNo se agregarán más detalles al pedido\n");
                        reiterarPregunta = false;
                        break;
                    } else {
                        System.out.print("Opción inválida. Desea agregar otro producto? (S/N): ");
                        // toma el input directamente al inicio
                    }
                                        
                } while (reiterarPregunta);       
                
                if (nuevoPedido.getDetalles().size() > 0) {                                        
                    Main.pedidos.add(nuevoPedido);
                    System.out.println("\nNuevo pedido agregado con ID " + nuevoPedido.getId());
                 
                } else {
                    System.out.println("\nPedido sin detalles. Operación cancelada");
                }
                break;
            }
            default: {
                throw new RuntimeException("Error: Imposible crear objeto");
            }
        }
    }
    
    // La implementación varía según cada objeto concreto (argumentos específicos para tomar con el scanner)
    // Pero siempre con el ID del objeto para identificarlo
    private static void editar(String objeto) {
        switch(objeto) {
            case "Categoria": {
                System.out.println("\n========== EDITAR CATEGORIA ==========\n");
                // si eliminado, informar por consola
                // si no existe, informar mensaje específico
                // si eliminado, informar por consola
                // actualiza sólo nombre y/o descripcion y confirma operación
                String nombre = "";
                String descripcion = "";

                String id = pedirIdValido(); // Valida input vacío y números negativos
                
                Categoria categoria = (Categoria) Main.findElementoById(Integer.parseInt(id), Categoria.class.getSimpleName());

                if (categoria == null) {
                    System.out.println("\nCategoría no encontrada\n");
                } else {
                    if (categoria.isEliminado()) {
                        System.out.println("\nCategoría ya eliminada\n");
                    } else {
                        
                        System.out.println("\nCategoría encontrada: " + categoria + "\n");
                                          
                        // Flujo específico para CATEGORIA (cada elemento cambia y hace chequeos específicos)
                        
                        System.out.print("Ingrese el nuevo nombre (Presione 'Enter' para conservar el valor actual): ");
                        nombre = Main.sc.nextLine().trim();
                        
                        // Si no ingresa nada o un string vacío, se asume que conserva el nombre actual y avanza el flujo
                        if (nombre.trim().length() == 0) {
                            nombre = categoria.getNombre();
                        }
                        
                        System.out.print("Ingrese la nueva descripción (Presione 'Enter' para conservar el valor actual): ");
                        descripcion = Main.sc.nextLine().trim();
                        
                        // Si no ingresa nada o un string vacío, se asume que conserva el nombre actual y avanza el flujo
                        if (descripcion.trim().length() == 0) {
                            descripcion = categoria.getDescripcion();
                        }

                        // Confirmación (verificar respuesta correcta con loop)
                        // Si no hay cambios, salir
                        if (nombre.equals(categoria.getNombre()) && descripcion.equals(categoria.getDescripcion())) {
                                System.out.println("\nNo se registraron cambios. Saliendo\n");
                        } else {
                            
                            System.out.println("\nLa categoría tendrá los siguientes valores:");
                            System.out.println("Nombre: " + nombre);
                            System.out.println("Descripción: "+ descripcion);

                            System.out.print("Desea realizar estos cambios? (S/N): ");

                            boolean reiterarPregunta = true;

                            do {            
                                String respuesta = Main.sc.nextLine().trim();

                                if (respuesta.trim().toLowerCase().equals("s")) {

                                    categoria.setNombre(nombre);
                                    categoria.setDescripcion(descripcion);

                                    System.out.println("\nCambios realizados");

                                    reiterarPregunta = false;
                                } else if (respuesta.trim().toLowerCase().equals("n")) {
                                    System.out.println("\nLos cambios fueron cancelados");
                                    reiterarPregunta = false;
                                    break;
                                } else {
                                    System.out.print("Opción inválida. Desea realizar estos cambios? (S/N): ");      
                                }
                            } while (reiterarPregunta);
                        }                        
                    }
                }
            
                break;
            }
            case "Producto": {
                flujoEditarProducto();
                break;
            }
            case "Usuario": {
                
                break;
            }
            case "Pedido": {
                              
                break;
            }
            default: {
                throw new RuntimeException("Error: Imposible editar objeto");
            }
        }
    }
    
    // La implementación toma el ID para buscar y sólo setea eliminado = true;
    private static void eliminar(String objeto) {
        switch(objeto) {
            case "Categoria": {
                System.out.println("\n========== ELIMINAR CATEGORIA ==========\n");
                // si eliminado, informar por consola

                String id = pedirIdValido(); // Valida input vacío y números negativos
                
                Base categoria = Main.findElementoById(Integer.parseInt(id), Categoria.class.getSimpleName());

                if (categoria == null) {
                    System.out.println("\nCategoría no encontrada\n");
                } else {
                    if (categoria.isEliminado()) {
                        System.out.println("\nCategoría ya eliminada\n");
                    } else {
                        
                        System.out.println("\nCategoría encontrada: " + categoria + "\n");
                        System.out.print("Desea continuar con la eliminación? (S/N): ");

                        boolean reiterarPregunta = true;

                        do {            
                            String respuesta = Main.sc.nextLine().trim();

                            if (respuesta.trim().toLowerCase().equals("s")) {
                                categoria.setEliminado(true);
                                
                                System.out.println("\nCategoría eliminada\n");     

                                reiterarPregunta = false;
                            } else if (respuesta.trim().toLowerCase().equals("n")) {
                                System.out.println("\nEliminación cancelada\n");
                                reiterarPregunta = false;
                                break;
                            } else {
                                System.out.print("Opción inválida. Desea continuar con la eliminación? (S/N): ");      
                            }
                        } while (reiterarPregunta);                                                                
                    }
                }

                break;
            }
            case "Producto": {
                System.out.println("\n========== ELIMINAR PRODUCTO ==========\n");
                // si eliminado, informar por consola
                String id = pedirIdValido(); // Valida input vacío y números negativos

                Base elemento = Main.findElementoById(Integer.parseInt(id), Producto.class.getSimpleName());

                if (!(elemento instanceof Producto)) {
                    System.out.println("\nProducto no encontrado\n");
                    return;
                }

                Producto producto = (Producto) elemento;

                if (producto.isEliminado()) {
                    System.out.println("\nProducto ya eliminado\n");
                    return;
                }

                System.out.println("\nProducto encontrado: " + producto + "\n");
                System.out.print("Desea continuar con la eliminación? (S/N): ");

                boolean reiterarPregunta = true;

                do {
                    String respuesta = Main.sc.nextLine().trim();

                    if (respuesta.trim().toLowerCase().equals("s")) {
                        producto.setEliminado(true);
                        
                        System.out.println("\nProducto eliminado\n");

                        reiterarPregunta = false;
                    } else if (respuesta.trim().toLowerCase().equals("n")) {
                        System.out.println("\nEliminación cancelada\n");
                        reiterarPregunta = false;
                        break;
                    } else {
                        System.out.print("Opción inválida. Desea continuar con la eliminación? (S/N): ");
                    }
                } while (reiterarPregunta);


                break;
            }
            case "Usuario": {
                
                break;
            }
            case "Pedido": {
                System.out.println("\n========== ELIMINAR PEDIDO ==========\n");
                // si eliminado, informar por consola
                String id = pedirIdValido(); // Valida input vacío y números negativos
                
                Base pedido = Main.findElementoById(Integer.parseInt(id), Pedido.class.getSimpleName());

                if (pedido == null) {
                    System.out.println("\nPedido no encontrado\n");
                } else {
                    if (pedido.isEliminado()) {
                        System.out.println("\nPedido ya eliminado\n");
                    } else {
                        
                        System.out.println("\nPedido encontrado: " + pedido + "\n");
                        System.out.print("Desea continuar con la eliminación? (S/N): ");

                        boolean reiterarPregunta = true;

                        do {            
                            String respuesta = Main.sc.nextLine().trim();

                            if (respuesta.trim().toLowerCase().equals("s")) {
                                pedido.setEliminado(true);
                        
                                System.out.println("\nPedido eliminado\n");     

                                reiterarPregunta = false;
                            } else if (respuesta.trim().toLowerCase().equals("n")) {
                                System.out.println("\nEliminación cancelada\n");
                                reiterarPregunta = false;
                                break;
                            } else {
                                System.out.print("Opción inválida. Desea continuar con la eliminación? (S/N): ");      
                            }
                        } while (reiterarPregunta);                                                                
                    }
                }
                break;
            }
            default: {
                throw new RuntimeException("Error: Imposible eliminar objeto");
            }
        }
    }
    
    // Validaciones / Métodos auxiliares
    
    private static boolean cumpleRangoValido(int opcion, int min, int max) {
        return opcion >= min && opcion <= max;
    }
    
  
    // Sólo para categorias, productos y usuarios
    private static Base findElementoByNombre(String nombre, String nombreClase) {
        if (nombreClase.equalsIgnoreCase("categoria")) {
            for (Categoria categoria: Main.categorias) {
                if (categoria.getNombre().equalsIgnoreCase(nombre)){
                    return categoria;
                }
            }
        } else if (nombreClase.equalsIgnoreCase("producto")) {
            for (Producto producto: Main.productos) {
                if (producto.getNombre().equalsIgnoreCase(nombre)){
                    return producto;
                }
            }
        } else if (nombreClase.equalsIgnoreCase("usuario")) {
            for (Usuario usuario: Main.usuarios) {
                if (usuario.getNombre().equalsIgnoreCase(nombre)){
                    return usuario;
                }
            }
        } else {
            throw new RuntimeException("Error nombreClase buscando elemento por nombre: "+ nombreClase);
        }                     
        // Devolver null si no encuentra nada               
        return null;
    }
    
    // Para editar o eliminar cualquier entidad (busca TODOS los elementos, eliminados o no)
    private static Base findElementoById(int id, String nombreClase) {        
        if (nombreClase.toLowerCase().equals("categoria")) {
            for (Categoria categoria: Main.categorias) {
                if (categoria.getId() == id){
                    return categoria;
                }
            }
        } else if (nombreClase.toLowerCase().equals("producto")) {
            for (Producto producto: Main.productos) {
                if (producto.getId() == id){
                    return producto;
                }
            }
        } else if (nombreClase.toLowerCase().equals("usuario")) {
            for (Usuario usuario: Main.usuarios) {
                if (usuario.getId() == id){
                    return usuario;
                }
            }
        } else if (nombreClase.toLowerCase().equals("pedido")) {
            for (Pedido pedido: Main.pedidos) {
                if (pedido.getId() == id){
                    return pedido;
                }
            }
        } else {
            throw new RuntimeException("Error nombreClase buscando elemento por id: "+ nombreClase);
        }                     
        // Devolver null si no encuentra nada               
        return null;
    }
    
    private static <T> List<T> filtrarElementosEliminados(List<T> elementos) {

        List<T> listaNoEliminados = new ArrayList<>();
        
        for (T elemento: elementos) {
            if (elemento instanceof Base) { // necesario para poder invocar isEliminado
                boolean eliminado = ((Base) elemento).isEliminado();
                if (!eliminado) {                                                
                    listaNoEliminados.add(elemento);
                }
            }
        }        
        return listaNoEliminados;
    }
    
    // Otros

    private static <T> void imprimirPorConsola(List<T> elementos, String nombreClase) {
        
        System.out.println("");
        
        // Primero filtrar los eliminados
        List<T> listaFiltrada = Main.filtrarElementosEliminados(elementos);
        
        if (listaFiltrada.size() == 0) {
            String mensaje =  "";
            
            if (nombreClase.toLowerCase().equals("categoria")) {
                mensaje = "No hay categorías cargadas";
            } else if (nombreClase.toLowerCase().equals("producto")) {
                mensaje = "No hay productos cargados";
            } else if (nombreClase.toLowerCase().equals("usuario")) {
                mensaje = "No hay usuarios cargados";
            } else if (nombreClase.toLowerCase().equals("pedido")) {
                mensaje = "No hay pedidos cargados";
            } else {
                throw new RuntimeException("Error nombreClase listando elementos: "+ nombreClase);
            }

            System.out.println(mensaje);
        } else {
            for (T elemento: listaFiltrada) {
                System.out.println("- " + elemento);                
            }
            System.out.println("");
        }
        
    }
    
    // útil para debuggear (crear elementos para testear
    private static void sugerirCargarDatos() {
        boolean reiterarPregunta = true;

        System.out.print("La aplicación no contiene datos. Desea cargar datos de prueba? (S/N): ");
        do {            
            String cargarDatosPrueba = Main.sc.nextLine().trim();

            if (cargarDatosPrueba.trim().toLowerCase().equals("s")) {
                System.out.println("Cargando datos...");
                
                // TODO: inicializar objetos una vez estén hechas las clases
                
                Categoria c1 = new Categoria("Categoria test", "descripcion categoria test");                
                Main.categorias.add(c1);
                
                Usuario user = new Usuario("user","prueba","test@mail.com","11223344","password123",Rol.ADMIN);
                Main.usuarios.add(user);    
                Producto pp = new Producto("producto1", 10, "desc", 1, "imagen.png", c1);
                c1.agregarProducto(pp);
                Main.productos.add(pp);
                
                System.out.println("Datos cargados OK");

                reiterarPregunta = false;

            } else if (cargarDatosPrueba.trim().toLowerCase().equals("n")) {
                reiterarPregunta = false;
                return;
            } else {
                System.out.print("Opción inválida. Desea cargar datos de prueba? (S/N): ");      
            }
        } while (reiterarPregunta);
    }        


    private static void flujoEditarProducto() {
        System.out.println("\n========== EDITAR PRODUCTO ==========\n");
        // si eliminado, informar por consola
        // si no existe, informar mensaje específico
        // si eliminado, informar por consola
        // actualiza solo precio y/o stock y/o categoria y confirma operación

        String id = pedirIdValido(); // Valida input vacío y números negativos

        Base elemento = Main.findElementoById(Integer.parseInt(id), Producto.class.getSimpleName());

        if (!(elemento instanceof Producto)) {
            System.out.println("\nProducto no encontrado\n");
            return;
        }
        Producto producto = (Producto) elemento;

        if (producto.isEliminado()) {
            System.out.println("\nProducto ya eliminado\n");
            return;
        }

        System.out.println("\nProducto encontrado: " + producto + "\n");

        // Edición de campos
        double precio = Double.parseDouble(solicitarCampoGenerico("precio", Double.toString(producto.getPrecio()), TipoValidacion.DECIMAL_NO_NEGATIVO));
        int stock = Integer.parseInt(solicitarCampoGenerico("stock", Integer.toString(producto.getStock()), TipoValidacion.ENTERO_NO_NEGATIVO));
        Categoria categoria = solicitarNuevaCategoria(producto);

        if (categoria == null) return; // Si la categoría no existe, corta acá

        // Confirmación de cambios
        if (Double.compare(precio, producto.getPrecio()) == 0 && stock == producto.getStock() && categoria.equals(producto.getCategoria())) {
            System.out.println("\nNo se registraron cambios. Saliendo\n");
            return;
        }

        mostrarResumenCambios(precio, stock, categoria);

        if (confirmarCambios()) {
            producto.setPrecio(precio);
            producto.setStock(stock);            
            if (!categoria.equals(producto.getCategoria())) {
                producto.setCategoria(categoria);
            }            
            System.out.println("\nCambios realizados");
        } else {
            System.out.println("\nLos cambios fueron cancelados");
        }
    }

    private static String solicitarCampoGenerico(String nombreCampo, String valorActual, TipoValidacion tipoValidacion) {
        System.out.print("Ingrese el nuevo " + nombreCampo + " (Presione 'Enter' para conservar el valor actual): ");
        String entrada = Main.sc.nextLine().trim();

        if (entrada.isEmpty()) return valorActual; // Si da enter directo, devuelve el valor que ya tenía

        // Bucle dinámico según el tipo de validación que se le pida
        while (true) {
            boolean esValido = false;

            if (tipoValidacion == TipoValidacion.ENTERO_NO_NEGATIVO) {
                esValido = Validador.esNumeroEnteroNoNegativoValido(entrada);
            } else if (tipoValidacion == TipoValidacion.DECIMAL_NO_NEGATIVO) {
                esValido = Validador.esDigitoNoNegativoValido(entrada);
            }

            if (esValido) break; // Si pasó la prueba, rompemos el bucle

            // Si no es válido, vuelve a pedir
            System.out.print(nombreCampo + " inválido. Inténtelo nuevamente (o Enter para conservar): ");
            entrada = Main.sc.nextLine().trim();
            if (entrada.isEmpty()) return valorActual;
        }
        return entrada;
    }

    private static Categoria solicitarNuevaCategoria(Producto producto) {
        System.out.print("Ingrese la nueva Categoría (Presione 'Enter' para conservar el valor actual): ");
        String nuevaCategoria = Main.sc.nextLine().trim();

        if (nuevaCategoria.isEmpty()) return producto.getCategoria();

        Base categoriaBuscada = Main.findElementoByNombre(nuevaCategoria, Categoria.class.getSimpleName());
        if (categoriaBuscada == null) {
            System.out.println("\nCategoría no encontrada.\n");
            return null;
        }
        return (Categoria) categoriaBuscada;
    }

    private static void mostrarResumenCambios(double precio, int stock, Categoria categoria) {
        System.out.println("\nEl producto tendrá los siguientes valores:");
        System.out.println("Precio: $ " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Categoría: " + categoria.getNombre());
    }

    private static boolean confirmarCambios() {
        System.out.print("\n¿Desea realizar estos cambios? (S/N): ");

        while(true) {
            String respuesta =  Main.sc.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) return true;
            if (respuesta.equals("n")) return false;
            System.out.print("Opción inválida. ¿Desea realizar estos cambios? (S/N): ");
        }
    }

    private static String pedirIdValido() {
        System.out.print("Ingrese el ID: ");
        String id = Main.sc.nextLine().trim();

        // Valida input vacío y números negativos
        while(!(Validador.validarCadena(id) && Validador.esCodigoValido(id))) {
            System.out.print("ID inválido. Ingrese el ID: ");
            id = Main.sc.nextLine().trim();
        }
        return id;
    }
}
