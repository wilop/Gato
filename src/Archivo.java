import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Wilberth Anonio López Aguilar
 */
public class Archivo {

    private final String RUTA = "gato.txt";

    /**
     * Crea una clase Archivo para manejar los archivo de texto.
     */
    public Archivo() {
    }

    /**
     * Guarda un ArrayList de puntuaciones en un arcivo de texto.
     * 
     * @param puntuaciones ArrayList de puntuaciones a guardar.
     * @throws IOException Excepción, arcivo no encontrado.
     */
    public void guardarPuntuaciones(ArrayList<Puntuacion> puntuaciones) throws IOException {

        String datos = llenarDatos(puntuaciones);

        FileWriter escritor = null;
        PrintWriter imprimir = null;
        try {
            File archivo = new File(RUTA);
            if (!archivo.exists()) {
                // archivo.getParentFile().mkdirs();
                archivo.createNewFile();

            }
            escritor = new FileWriter(archivo);
            imprimir = new PrintWriter(escritor);
            imprimir.println(datos.replaceAll("\n", "\r\n"));
        } catch (IOException e) {
            throw new IOException("archivo no encontrado");
        } finally {
            try {
                if (escritor != null) {
                    escritor.close();

                }

            } catch (IOException e) {
                throw new IOException("archivo no se pudo cerrar");

            }
        }

    }

    /**
     * Carga un ArrayList de puntuaciones desde un archivo de texto.
     * 
     * @return Un ArrayList recuperado del archivo.
     * @throws IOException Excepción, arcivo no encontrado.
     */
    public ArrayList<Puntuacion> cargarPuntuaciones() throws IOException {
        File archivo = null;
        FileReader lector = null;
        BufferedReader buffer = null;
        String datos = "";
        try {
            archivo = new File(RUTA);
            if (!archivo.exists()) {
                // archivo.getParentFile().mkdirs();
                archivo.createNewFile();

            }
            lector = new FileReader(archivo);
            buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                datos += linea + "\n";

            }

        } catch (IOException e) {
            throw new IOException("archivo no encontrado");
        } finally {
            try {
                if (lector != null) {
                    lector.close();

                }

            } catch (IOException e) {
                throw new IOException("archivo no se pudo cerrar");

            }

        }
        if (datos != "") {
            return llenarPuntuaciones(datos);
        } else {
            return new ArrayList<Puntuacion>();
        }

    }

    /**
     * Crea un ArrayList de puntuaciones con los datos recuperados del archivo.
     * 
     * @param datos Texto recuperado del archivo.
     * @return ArrayList de puntuaciones.
     */
    private ArrayList<Puntuacion> llenarPuntuaciones(String datos) {
        String[] lineas = datos.split("\n");
        ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();

        for (String linea : lineas) {
            String[] atributos = linea.split(",");
            Puntuacion puntuacion = new Puntuacion();
            puntuacion.setFecha(LocalDateTime.parse(atributos[0]));
            puntuacion.setAlias(atributos[1]);
            puntuacion.setPuntos(Integer.parseInt(atributos[2]));
            puntuaciones.add(puntuacion);

        }

        return puntuaciones;
    }

    /**
     * Crea una cadena de texto a partir de un ArrayList de puntuaciones a guardar.
     * 
     * @param puntuaciones ArrayList de puntuaciones.
     * @return Una cadena de texto a guardar.
     */
    private String llenarDatos(ArrayList<Puntuacion> puntuaciones) {
        String datos = "";
        for (Puntuacion puntuacion : puntuaciones) {

            datos += puntuacion.getFecha() + ",";
            datos += puntuacion.getAlias() + ",";
            datos += puntuacion.getPuntos() + "\n";

        }
        return datos;
    }
}
