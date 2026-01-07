package es.unizar.eina.M117_quads.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase de utilidad encargada de validar los campos de un {@link Quad}.
 * Contiene la lógica de negocio asociada a las clases de equivalencia.
 */
public class Validator {
    // Se define un formateador estático para reutilizarlo y ser más eficiente.
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    static {
        // Se asegura de que el formateo sea estricto (e.g., no acepta "31/02/2024").
        sdf.setLenient(false);
    }
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

    public static void validateReserva(String nombre, Date fechaRecogida, Date fechaDevolucion, String numeroTelef) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }

        if (fechaRecogida == null || fechaDevolucion == null) {
            throw new IllegalArgumentException("Formato de fecha inválido");
        }

        if (numeroTelef == null || numeroTelef.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede ser nulo o vacío");
        }

        if (fechaRecogida.after(fechaDevolucion)) {
            throw new IllegalArgumentException("La fecha de recogida no puede ser posterior a la de devolución");
        }
    }

    /**
     * Convierte una cadena de fecha a un objeto Date.
     */
    public static Date toDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);
        return sdf.parse(dateString);
    }
}
