package es.unizar.eina.M117_quads.testing;

import java.util.Calendar;
import java.util.Date;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;
import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.database.ReservaRepository;

/**
 * Clase auxiliar para ejecutar pruebas de volumen.
 */
public class VolumeTestHelper {

    /** Inserta un número masivo de quads */
    public static void insertManyQuads(QuadRepository repository, int count) {
        for (int i = 1; i <= count; i++) {
            String matricula = String.format("%04dABC", i);
            Quad quad = new Quad(matricula, 1, 10, "Quad volumen " + i);
            repository.insert(quad);
        }
    }

    /** Inserta un número masivo de reservas */
    public static void insertManyReservas(ReservaRepository repository, int count) {
        Calendar cal = Calendar.getInstance();

        for (int i = 1; i <= count; i++) {
            cal.setTime(new Date());
            Date recogida = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date devolucion = cal.getTime();

            Reserva reserva = new Reserva(
                    "ClienteTest_" + i,
                    "600000" + i,
                    recogida,
                    devolucion
            );

            repository.insert(reserva);
        }
    }
}
