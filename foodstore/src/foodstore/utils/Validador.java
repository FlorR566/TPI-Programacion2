package foodstore.utils;

public class Validador {
    
    public static boolean validarCadena(String cadena) {
        return cadena != null && cadena.trim().length() > 0; 
    }
    
    public static boolean validarNumero(double numero) {
        return numero > 0;
    }
    
    // Verifica mediante regex que el string sea de tipo dígito (doble o entero) positivo (precios, etc)
    public static boolean esDigitoPositivoValido(String cadena) {
        return (cadena.matches("^\\d+\\.\\d+$") || cadena.matches("^\\d+$") ) && Validador.validarNumero(Double.parseDouble(cadena));
    }
    
    // Verifica mediante regex que el string sea de tipo dígito entero positivo (ids)
    public static boolean esCodigoValido(String cadena) {
        return cadena.matches("^\\d+$");
    }
    
    public static boolean esNumeroEnteroValido(String cadena) {
        return cadena.matches("^\\d+$") && Integer.parseInt(cadena) > 0;
    }

    // Acepta 0 o ualquier decimal positivo (para precio)
    public static boolean esDigitoNoNegativoValido(String cadena) {
        return (cadena.matches("^\\d+\\.\\d+$") || cadena.matches("^\\d+$"))
                && Double.parseDouble(cadena) >= 0;
    }

    // Acepta 0 o cualquier entero positivo (para stock)
    public static boolean esNumeroEnteroNoNegativoValido(String cadena) {
        return cadena.matches("^\\d+$") && Integer.parseInt(cadena) >= 0;
    }

}
