package foodstore.utils;

public class Validador {
    
    public static boolean validarCadena(String cadena) {
        return cadena != null && cadena.trim().length() > 0; 
    }
    
    public static boolean validarNumero(double numero) {
        return numero > 0;
    }
    
    // Verifica mediante regex que el string sea de tipo dígito (doble o entero) positivo
    public static boolean esDigitoPositivoValido(String cadena) {
        return (cadena.matches("^\\d+\\.\\d+$") || cadena.matches("^\\d+$") ) && Validador.validarNumero(Double.parseDouble(cadena));
    }
    
    // Verifica mediante regex que el string sea de tipo dígito entero positivo
    public static boolean esCodigoValido(String cadena) {
        return cadena.matches("^\\d+$");
    }
    
    public static boolean esNumeroEnteroValido(String cadena) {
        return cadena.matches("^\\d+$") && Integer.parseInt(cadena) > 0;
    }
    
}
