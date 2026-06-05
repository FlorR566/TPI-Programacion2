
package foodstore;

import foodstore.entities.Base;
import foodstore.entities.Categoria;
import foodstore.entities.Pedido;
import foodstore.entities.Producto;
import foodstore.entities.Usuario;
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
                           Main.procesarOpcionCRUD(opcionSubmenu, "Categoría"); // crear un enum o mandar la clase?
                           break;
                       }
                       case 2: {
                           Main.procesarOpcionCRUD(opcionSubmenu, "Productos"); // crear un enum o mandar la clase?
                           break;
                       }
                       case 3: {
                           Main.procesarOpcionCRUD(opcionSubmenu, "Usuarios"); // crear un enum o mandar la clase?
                           break;
                       }
                       case 4: {
                           Main.procesarOpcionCRUD(opcionSubmenu, "Pedidos"); // crear un enum o mandar la clase?
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
    
    private static void procesarOpcionCRUD(int opcionSubmenu, String objeto) {
        boolean errorDeEntrada;                
        
        do {
            errorDeEntrada = false; // asume que el input del usuario va a ser válido
    
            try {
                switch(opcionSubmenu) {
                    case 1: {
                        Main.listar(objeto);
                        break;
                    }
                    case 2: {
                        Main.crear(objeto);
                        break;
                    }
                    case 3:{
                        Main.editar(objeto);
                        break;
                    }
                    case 4: {
                        Main.eliminar(objeto);
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
                System.out.println("Error. Por favor inténtelo nuevamente o comuníquese con el administrador");
                errorDeEntrada = true;
            }
            
        } while (errorDeEntrada);
        
    }
    
    
    // Operaciones CRUD
    
    private static void listar(String objeto) {
        switch(objeto) {
            case "Categoría": {
                Main.imprimirPorConsola(categorias);
                break;
            }
            case "Productos": {
                Main.imprimirPorConsola(productos);
                break;
            }
            case "Usuarios": {
                Main.imprimirPorConsola(usuarios);
                break;
            }
            case "Pedidos": {
                Main.imprimirPorConsola(pedidos);
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
            case "Categoría": {
                // validar el input
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
                throw new RuntimeException("Error: Entidad desconocida");                
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
                throw new RuntimeException("Error: Entidad desconocida");                
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
                throw new RuntimeException("Error: Entidad desconocida");                
            }
        }
    }
    
    // Validaciones
    
    private static boolean cumpleRangoValido(int opcion, int min, int max) {
        return opcion >= min && opcion <= max;
    }
    
    // Otros
    
    private static <T> void imprimirPorConsola(List<T> elementos) {
        System.out.println("");
        if (elementos.size() == 0) {
            System.out.println("No hay elementos en la lista");
        } else {            
            for (T elemento: elementos) {
                System.out.println("- " + elemento);
            }
        }
        System.out.println("");
        
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
