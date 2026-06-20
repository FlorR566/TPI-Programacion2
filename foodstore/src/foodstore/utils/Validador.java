package foodstore.utils;

import foodstore.enums.Estado;
import foodstore.enums.FormaPago;

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

    // Acepta 0 o cualquier decimal positivo (para precio)
    public static boolean esDigitoNoNegativoValido(String cadena) {
        return (cadena.matches("^\\d+\\.\\d+$") || cadena.matches("^\\d+$"))
                && Double.parseDouble(cadena) >= 0;
    }

    // Acepta 0 o cualquier entero positivo (para stock)
    public static boolean esNumeroEnteroNoNegativoValido(String cadena) {
        return cadena.matches("^\\d+$") && Integer.parseInt(cadena) >= 0;
    }
    
    public static boolean esFormaDePagoValida(String cadena) {
        return cadena.equalsIgnoreCase(FormaPago.EFECTIVO.toString()) || cadena.equalsIgnoreCase(FormaPago.TARJETA.toString()) || cadena.equalsIgnoreCase(FormaPago.TRANSFERENCIA.toString());
    }
    
    public static boolean esEstadoValido(String cadena) {
        return cadena.equalsIgnoreCase(Estado.CANCELADO.toString()) || cadena.equalsIgnoreCase(Estado.CONFIRMADO.toString()) || cadena.equalsIgnoreCase(Estado.PENDIENTE.toString()) || cadena.equalsIgnoreCase(Estado.TERMINADO.toString());
    }

    // Valida que el mail contenga '@' y que esté en medio
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;

        int posicionArroba = email.indexOf("@");
        if (posicionArroba <= 0 ||  posicionArroba == email.length() - 1) return false;

        return true;
    }

    public static boolean validarCelular(String celular) {
        if (celular == null || celular.trim().isEmpty()) return false;

        String limpio= celular.replace(" ", "").replace("-", "").replace("+","");

        // Para debug (luego eliminar)
        if (limpio.length() < 4) return false;
        // if ( limpio.length() < 10 || limpio.length() > 13) return false;

        for (int i=0; i < limpio.length(); i++ ) { // chequea que cada caracter solo sea numérico
            if (!Character.isDigit(limpio.charAt(i))) return false;
        }

        return true;
    }
}
