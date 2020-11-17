package utils;

public class CodigosGenerador {

    // opc = 0, obtiene un codigo generado para un producto
    // opc = 1, obtiene un codigo generado para un proveedor
    public static String getCodigo(int opc) {
        return opc == 0 ? "P" + getSucesion(4) : "PRVDR" + getSucesion(5);
    }

    private static String getSucesion(int sucesion) {
        String numeros = "";
        for (int i = 0; i < sucesion; i++) {
            int numero = (int) ((Math.random() * 9) + 1);
            numeros += String.valueOf(numero);
        }
        return numeros;
    }

    // Prueba de uso
    /*public static void main(String[] args) {
        System.out.println(getCodigo(0));
        System.out.println(getCodigo(1));
    }*/
}
