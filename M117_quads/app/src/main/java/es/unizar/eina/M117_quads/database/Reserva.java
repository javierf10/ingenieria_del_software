package es.unizar.eina.M117_quads.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

/**
 * Clase anotada como entidad que representa una Reserva.
 * Consta de id, nombre, numero de telefono, fecha de recogida y fecha de devolucion.
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

    /** Constructor principal de la entidad Reserva */
    public Reserva(@NonNull String nombre, String numeroTelef, @NonNull Date fechaRecogida, @NonNull Date fechaDevolucion) {
        this.nombre = nombre;
        this.numeroTelef = numeroTelef;
        this.fechaRecogida = fechaRecogida;
        this.fechaDevolucion = fechaDevolucion;
    }

    /** Devuelve el identificador de la reserva */
    public int getId() {
        return this.id;
    }

    /** Devuelve el nombre del cliente de la reserva */
    @NonNull
    public String getNombre() {
        return this.nombre;
    }

    /** Devuelve el número de teléfono de la reserva */
    public String getNumeroTelef() {
        return this.numeroTelef;
    }

    /** Devuelve la fecha de recogida de la reserva */
    @NonNull
    public Date getFechaRecogida() {
        return this.fechaRecogida;
    }

    /** Devuelve la fecha de devolución de la reserva */
    @NonNull
    public Date getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /** Permite actualizar el identificador de la reserva */
    public void setId(int id) {
        this.id = id;
    }

    /** Permite actualizar el nombre del cliente de la reserva */
    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    /** Permite actualizar el numero de telefono de la reserva */
    public void setNumeroTelef(String numeroTelef) {
        this.numeroTelef = numeroTelef;
    }

    /** Permite actualizar la fecha de recogida de la reserva */
    public void setFechaRecogida(@NonNull Date fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    /** Permite actualizar la fecha de devolucion de la reserva */
    public void setFechaDevolucion(@NonNull Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id &&
                nombre.equals(reserva.nombre) &&
                Objects.equals(numeroTelef, reserva.numeroTelef) &&
                fechaRecogida.equals(reserva.fechaRecogida) &&
                fechaDevolucion.equals(reserva.fechaDevolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numeroTelef, fechaRecogida, fechaDevolucion);
    }
    */
}
