
package foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        try {            
            Main.init();
        } catch (Exception e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
        } finally {
            sc.close();
        }
        
    }
    
    public static void init() {
        int opcionMenu = -1;
        System.out.println("========= SISTEMA DE PEDIDOS (FOOD STORE) ==========");

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
    
    public static void mostrarMenu() {
        System.out.println("\n================== Menú principal ==================\n");
        System.out.println("1. Categorías");
        System.out.println("2. Productos"); 
        System.out.println("3. Usuarios");
        System.out.println("4. Pedidos");
        System.out.println("0. Salir");
        
    }
    
    public static void mostrarSubMenu(int opcionMenu) {
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
                   System.out.println("opcionMenu: " + opcionMenu + ", opcionSubMenu: " + opcionSubmenu );
                   // Evaluar la opción de menu principal elegida
                   switch (opcionMenu) {
                       case 1: {
                           Main.procesarOpcionCRUD(opcionSubmenu, "Categoria"); // crear un enum o mandar la clase?
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
                        System.out.println("Listar " + objeto);
                        
                        break;                        
                    }
                    case 2: {
                        
                        System.out.println("Crear " + objeto);
                        
                        break;
                        
                    }
                    case 3:{
                        
                        System.out.println("Editar " + objeto);
                        
                        break;
                        
                    }
                    case 4: {
                        
                        System.out.println("Eliminar "+ objeto);                       
                        
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
    
    
    
    private static boolean cumpleRangoValido(int opcion, int min, int max) {
        return opcion >= min && opcion <= max;
    }
    
}
