package es.unizar.eina.M117_quads.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Clase anotada como entidad que representa una Reserva.
 * Consta de nombre del cliente, número de teléfono, fecha de recogida y fecha de devolución.
 */
@Entity(tableName = "Reservas")
public class Reserva {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reserva_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "numeroTelef")
    private String numeroTelef;

    @NonNull
    @ColumnInfo(name = "fechaRecogida")
    private Date fechaRecogida;

    @NonNull
    @ColumnInfo(name = "fechaDevolucion")
    private Date fechaDevolucion;

    /**
     * Constructor principal de la entidad Reserva.
     *
     * @param nombre         nombre del cliente
     * @param numeroTelef    número de teléfono del cliente
     * @param fechaRecogida  fecha en la que se recoge el quad
     * @param fechaDevolucion fecha en la que se devuelve el quad
     */
    public Reserva(@NonNull String nombre,
                   String numeroTelef,
                   @NonNull Date fechaRecogida,
                   @NonNull Date fechaDevolucion) {

        this.nombre = nombre;
        this.numeroTelef = numeroTelef;
        this.fechaRecogida = fechaRecogida;
        this.fechaDevolucion = fechaDevolucion;
    }

    /** Devuelve el identificador de la reserva */
    public int getId() {
        return this.id;
    }

    /** Permite actualizar el identificador de la reserva */
    public void setId(int id) {
        this.id = id;
    }

    /** Devuelve el nombre del cliente */
    public String getNombre() {
        return this.nombre;
    }

    /** Permite actualizar el nombre del cliente */
    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    /** Devuelve el número de teléfono */
    public String getNumeroTelef() {
        return this.numeroTelef;
    }

    /** Permite actualizar el número de teléfono */
    public void setNumeroTelef(String numeroTelef) {
        this.numeroTelef = numeroTelef;
    }

    /** Devuelve la fecha de recogida */
    public Date getFechaRecogida() {
        return this.fechaRecogida;
    }

    /** Permite actualizar la fecha de recogida */
    public void setFechaRecogida(Date fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    /** Devuelve la fecha de devolución */
    public Date getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /** Permite actualizar la fecha de devolución */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
