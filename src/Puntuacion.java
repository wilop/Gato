import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Wilberth Antonio López Aguilar
 */
public class Puntuacion {

    private String alias;
    private LocalDateTime fecha;
    private int puntos;

    public Puntuacion(String alias, LocalDateTime fecha, int puntos) {
        this.alias = alias;
        this.fecha = fecha;
        this.puntos = puntos;
    }

    public Puntuacion() {
        alias = "jugador";
        fecha = LocalDateTime.now();
    }

    /**
     * @return String return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return Date return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return int return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
/**
 * Arreglo con las propiedades de la clase facilitar llenado de modelo de tabla.
 * @return Arreglo con las propiedades de la clase (Alias, Fecha y Puntos).
 */
    public Object[] toArray() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Para dar formato a la fecha y hora

        Object[] arreglo = { alias, fecha.format(formato), puntos };
        return arreglo;
    }
}
