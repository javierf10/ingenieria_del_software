package es.unizar.eina.M117_quads.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase anotada como entidad que representa un Quad.
 * Consta de matrícula, tipo, precio y descripción.
 */
@Entity(tableName = "Quads")
public class Quad {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quad_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "matricula")
    private String matricula;

    @ColumnInfo(name = "tipo")
    private int tipo;

    @ColumnInfo(name = "precio")
    private int precio;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    /** Constructor principal de la entidad Quad */
    public Quad(@NonNull String matricula, int tipo, int precio, String descripcion) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    /** Devuelve el identificador del quad */
    public int getId() {
        return this.id;
    }

    /** Permite actualizar el identificador del quad */
    public void setId(int id) {
        this.id = id;
    }

    /** Devuelve la matrícula del quad */
    public String getMatricula() {
        return this.matricula;
    }

    /** Devuelve el tipo del quad */
    public int getTipo() {
        return this.tipo;
    }

    /** Devuelve el precio del quad */
    public int getPrecio() {
        return this.precio;
    }

    /** Devuelve la descripción del quad */
    public String getDescripcion() {
        return this.descripcion;
    }

    /** Permite actualizar la matrícula */
    public void setMatricula(@NonNull String matricula) {
        this.matricula = matricula;
    }

    /** Permite actualizar el tipo */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /** Permite actualizar el precio */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /** Permite actualizar la descripción */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
