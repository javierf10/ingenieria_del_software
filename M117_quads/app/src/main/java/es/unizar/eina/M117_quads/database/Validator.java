package es.unizar.eina.M117_quads.database;

/**
 * Clase de utilidad encargada de validar los campos de un {@link Quad}.
 * Contiene la lógica de negocio asociada a las clases de equivalencia.
 */
public class Validator {

    /**
     * Valida un quad completo según las reglas definidas.
     *
     * @param quad Quad a validar.
     * @throws IllegalArgumentException si alguno de los campos no es válido.
     */
    public static void validateQuad(Quad quad) {
        if (quad == null) {
            throw new IllegalArgumentException("El quad no puede ser null");
        }

        if (!isMatriculaValida(quad.getMatricula())) {
            throw new IllegalArgumentException(
                    "La matrícula debe tener formato NNNNLLL"
            );
        }

        if (!isTipoValido(quad.getTipo())) {
            throw new IllegalArgumentException(
                    "El tipo debe ser 1 o 2"
            );
        }

        if (!isPrecioValido(quad.getPrecio())) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que 0"
            );
        }
    }

    private static boolean isMatriculaValida(String matricula) {
        if (matricula == null || matricula.length() != 7) return false;

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(matricula.charAt(i))) return false;
        }

        for (int i = 4; i < 7; i++) {
            char c = matricula.charAt(i);
            if (!Character.isUpperCase(c)) return false;
        }

        return true;
    }

    private static boolean isTipoValido(int tipo) {
        return tipo == 1 || tipo == 2;
    }

    private static boolean isPrecioValido(double precio) {
        return precio > 0;
    }
}
