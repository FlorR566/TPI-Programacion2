
package foodstore;

import foodstore.entities.Base;
import foodstore.entities.Categoria;
import foodstore.entities.Pedido;
import foodstore.entities.Producto;
import foodstore.entities.Usuario;
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
        System.out.println("========= SISTEMA DE PEDIDOS (FOOD STORE) ==========");
        
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
                   // Evaluar la opción de menu principal elegida
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
                        System.out.println("\nOpción inválida\n");
                        break;
                }                
            } catch (IllegalArgumentException e) {
                System.out.println("Valor/es ingresado/s inválido/s: " + e.getMessage());
                errorDeEntrada = true;
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
                System.out.println(nombre);
                        
                while (!Validador.validarCadena(nombre)) {
                    System.out.print("Nombre inválido. Inténtelo nuevamente: ");
                    nombre = Main.sc.nextLine().trim();
                }
                
                // Verificar que no exista ya la misma categoría               
                Base categoriaExistente = Main.findElementoByNombre(nombre, Categoria.class.getSimpleName());
                
                if (categoriaExistente != null) {
                    System.out.println("Una categoría con ese nombre ya existe");
                    return;
                }
                
                System.out.print("Ingrese la descripcion: ");

                // Seguir pidiendo datos sólo si no existe
                descripcion = Main.sc.nextLine().trim();
                        
                while (!Validador.validarCadena(descripcion)) {
                    System.out.print("Descripcion inválida. Inténtelo nuevamente: ");
                    descripcion = Main.sc.nextLine().trim();
                }

                Categoria nuevaCategoria = new Categoria(nombre,descripcion);
                
                // Agregar a lista correspondiente
                
                Main.categorias.add(nuevaCategoria);
                
                System.out.println("Nueva categoría agregada con ID " + nuevaCategoria.getId());
                
                
                break;
            }
            case "Productos": {                
                break;
            }
            case "Usuarios": {
                
                break;
            }
            case "Pedidos": {
                
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
            case "Categoría": {
                
                break;
            }
            case "Productos": {
                
                break;
            }
            case "Usuarios": {
                
                break;
            }
            case "Pedidos": {
                
                break;
            }
            default: {
                throw new RuntimeException("Error: Imposible editar objeto");
            }
        }
    }
    
    // La implementación toma el ID
    private static void eliminar(String objeto) {
        switch(objeto) {
            case "Categoría": {
                
                break;
            }
            case "Productos": {
                
                break;
            }
            case "Usuarios": {
                
                break;
            }
            case "Pedidos": {
                
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
    
    private static Base findElementoByNombre(String nombre, String nombreClase) {
        if (nombreClase.toLowerCase().equals("categoria")) {
            for (Categoria categoria: Main.categorias) {
                if (categoria.getNombre().toLowerCase().equals(nombre)){
                    return categoria;
                }
            }
        } else if (nombreClase.toLowerCase().equals("producto")) {
            for (Producto producto: Main.productos) {
                if (producto.getNombre().toLowerCase().equals(nombre)){
                    return producto;
                }
            }
        } else if (nombreClase.toLowerCase().equals("usuario")) {
            for (Usuario usuario: Main.usuarios) {
                if (usuario.getNombre().toLowerCase().equals(nombre)){
                    return usuario;
                }
            }
        } else {
            throw new RuntimeException("Error nombreClase buscando elemento: "+ nombreClase);
        }                     
        // Devolver null si no encuentra nada               
        return null;
    }
    
    
    // Otros
    
    private static <T> void imprimirPorConsola(List<T> elementos, String nombreClase) {
        System.out.println("");        
        
        if (elementos.size() == 0) {
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
            for (T elemento: elementos) {
                System.out.println("- " + elemento);
            }
            System.out.println("");
        }
        
    }
    
    private static void sugerirCargarDatos() {
        boolean reiterarPregunta = true;

        System.out.print("La aplicación no contiene datos. Desea cargar datos de prueba? (S/N): ");
        do {            
            String cargarDatosPrueba = Main.sc.nextLine().trim();

            if (cargarDatosPrueba.trim().toLowerCase().equals("s")) {
                System.out.println("Cargando datos...");
                
                // TODO: inicializar objetos una vez estén hechas las clases
                
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
    
    
}
