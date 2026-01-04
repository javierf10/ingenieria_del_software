package es.unizar.eina.M117_quads.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "reserva_quad_cross_ref",
        primaryKeys = {"reserva_id", "quad_id"},
        foreignKeys = {
            @ForeignKey(entity = Reserva.class,
                        parentColumns = "reserva_id",
                        childColumns = "reserva_id",
                        onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Quad.class,
                        parentColumns = "quad_id",
                        childColumns = "quad_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
            @Index(value = {"reserva_id"}),
            @Index(value = {"quad_id"})
        })
public class ReservaQuadCrossRef {
    @ColumnInfo(name = "reserva_id")
    public int reservaId;

    @ColumnInfo(name = "quad_id")
    public int quadId;

    public ReservaQuadCrossRef(int reservaId, int quadId) {
        this.reservaId = reservaId;
        this.quadId = quadId;
    }
}
