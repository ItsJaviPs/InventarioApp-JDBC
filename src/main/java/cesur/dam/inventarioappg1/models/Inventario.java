package cesur.dam.inventarioappg1.models;

import lombok.Data;

import java.time.LocalDateTime;

/** Una fila de la tabla "inventario" de la base de datos proyecto_ja. */
@Data
public class Inventario {
    private Integer id;
    private String nombre;
    private Integer cantidad;
    private String comentario;
    private LocalDateTime fecha;
}
