
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * @author Wilberth Antonio López Aguilar
 */

public class VentanaGato extends javax.swing.JDialog {

    private static String jugadorX; // Jugador Gato
    private static String jugadorO; // Jugador Ratón
    private boolean turno;
    private boolean jugar;
    private static int tamanoTablero;
    private int tamanoCasilla;
    private int puntosX;
    private int puntosO;
    private JLabel[][] matrizTablero;
    private JLabel[] linea;

    public VentanaGato(javax.swing.JFrame parent, boolean modal, String jugadorX, String jugadorO, int tamanoTablero) {
        super(parent, modal);
        initComponents();
        VentanaGato.jugadorX = jugadorX;
        VentanaGato.jugadorO = jugadorO;
        VentanaGato.tamanoTablero = tamanoTablero;
        crearTablero();
        turno = true;
        jugar = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel("El Gato y El Ratón");
        lblEstado = new javax.swing.JLabel();
        lblJugadorX = new javax.swing.JLabel("Jugador X");
        lblJugadorO = new javax.swing.JLabel("Jugador O");
        lblPuntosX = new javax.swing.JLabel("0");
        lblPuntosO = new javax.swing.JLabel("0");
        lblTurno = new javax.swing.JLabel();

        tablero = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JButton("Siguiente Partida");
        btnTerminar = new javax.swing.JButton("Terminar Juego");

        setTitle("Gato y Ratón");
        setSize(getPreferredSize());
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        c.anchor = java.awt.GridBagConstraints.CENTER;
        c.fill = java.awt.GridBagConstraints.NONE;
        c.insets.set(5, 5, 5, 5);
        setLayout(layout);

        lblTitulo.setFont(new java.awt.Font("Arial Black", java.awt.Font.BOLD, 25));
        c.gridx = 3;
        c.gridy = 0;
        add(lblTitulo, c);

        lblEstado.setFont(new java.awt.Font("Arial Black", java.awt.Font.BOLD, 20));
        c.gridx = 3;
        c.gridy = 2;
        add(lblEstado, c);

        lblJugadorX.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblJugadorX.setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon("res/gato.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));
        c.gridx = 2;
        c.gridy = 1;
        add(lblJugadorX, c);

        lblPuntosX.setBorder(new javax.swing.border.LineBorder(Color.BLUE, 2));
        lblPuntosX.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        c.gridx = 2;
        c.gridy = 2;
        add(lblPuntosX, c);

        lblJugadorO.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblJugadorO.setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon("res/raton.png").getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT)));
        c.gridx = 4;
        c.gridy = 1;
        add(lblJugadorO, c);
        lblPuntosO.setBorder(new javax.swing.border.LineBorder(Color.BLUE, 2));
        lblPuntosO.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        c.gridx = 4;
        c.gridy = 2;
        add(lblPuntosO, c);

        c.fill = java.awt.GridBagConstraints.NONE;

        c.gridx = 3;
        c.gridy = 3;
        add(tablero, c);

        c.gridx = 2;
        c.gridy = 4;
        btnTerminar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });
        add(btnTerminar, c);

        lblTurno.setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon("res/gato.png").getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
        c.gridx = 3;
        c.gridy = 4;
        add(lblTurno, c);

        c.gridx = 4;
        c.gridy = 4;
        btnSiguiente.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        add(btnSiguiente, c);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void btnTerminarActionPerformed(ActionEvent evt) {
        // Sí los jugadores no escriben el alias en el menú principal abre la ventana de
        // jugadores.
        if (jugadorX == null || jugadorO == null) {
            VentanaJugadores ventana = new VentanaJugadores(null, true);
            ventana.setVisible(true);
            jugadorX = ventana.getJugadorX();
            jugadorO = ventana.getJugadorO();
        }
        registrarPuntuacion();
        dispose(); // Salir de la ventana.
    }

    protected void labelMouseClicked(MouseEvent evt) {
        JLabel casilla = (JLabel) evt.getComponent(); // La casilla presionada del tablero.
        jugar(casilla);
    }

    protected void btnSiguienteActionPerformed(ActionEvent evt) {
        reiniciarTablero();
    }

    /**
     * Crea el tablero del juego y establece los alias del los jugadores.
     * Determina el tamaño de las casillas de acuerdo al tamaño del tablero, las
     * configura y agrega al tablero.
     */
    private void crearTablero() {

        lblJugadorX.setText(jugadorX);
        lblJugadorO.setText(jugadorO);

        switch (tamanoTablero) {
            case 3: // Tamaño 3x3
                tamanoCasilla = 120;
                break;
            case 5: // Tamaño 5x5
                tamanoCasilla = 72;
                break;
            case 7: // Tamaño 7x7
                tamanoCasilla = 52;
                break;

            default: // Tamaño por defecto (3x3)
                tamanoTablero = 3;
                tamanoCasilla = 120;
                break;
        }

        // Configura el tablero en forma de regilla.
        tablero.setLayout(new java.awt.GridLayout(tamanoTablero, tamanoTablero));
        tablero.setBackground(Color.BLACK);

        // Configura las propiedades de las casillas y agrega el evento para el mouse.
        for (int i = 0; i < tamanoTablero * tamanoTablero; i++) {
            javax.swing.JLabel casilla = new javax.swing.JLabel();
            casilla.setSize(tamanoCasilla, tamanoCasilla);
            casilla.setMinimumSize(new Dimension(tamanoCasilla, tamanoCasilla));
            casilla.setMaximumSize(new Dimension(tamanoCasilla, tamanoCasilla));
            casilla.setPreferredSize(new Dimension(tamanoCasilla, tamanoCasilla));
            casilla.setOpaque(true);
            casilla.setBorder(new javax.swing.border.LineBorder(Color.DARK_GRAY, 4));
            casilla.setBackground(Color.GRAY);
            casilla.setForeground(Color.WHITE);
            casilla.setHorizontalTextPosition(SwingConstants.CENTER);
            casilla.setVerticalTextPosition(SwingConstants.CENTER);
            casilla.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    labelMouseClicked(evt);
                }
            });
            tablero.add(casilla); // Agrega las casillas al tablero.
        }
    }

    /**
     * Es la logica principal del juego.
     * Verifica sí hay casillas disponibles, sí hay ganador, cambia de turno, sí
     * aun se puede marcar y sí la marca es correcta.
     * 
     * @param casilla La casilla del tablero presionada.
     */
    private void jugar(JLabel casilla) {
        if (jugar) { // Sí aun se puede marcar
            if (marcar(casilla)) { // Sí se hace una marca correcta

                if (hayGanador(turno)) {
                    lblEstado.setText("¡Hay Ganador!");
                    puntuar(turno);
                    return;
                }
                if (hayEspacio()) {
                    cambiarTurno();
                } else {
                    lblEstado.setText("¡Empate!");
                    puntuar();
                    return;
                }
            }
        }
    }

    /**
     * Verifica y marca una casilla del tablero por cada turno y agrega el icono del
     * gato o el
     * ratón.
     * 
     * @param casilla La casilla del tablero presionada.
     * @return True sí la marca es correcta. False sí ya había marca.
     */
    private boolean marcar(JLabel casilla) {

        int tamanoIcono = tamanoCasilla - 20;

        if (casilla.getName() == null) { // Verifica que la casilla este en blanco

            if (turno) {
                casilla.setIcon(new ImageIcon(
                        new ImageIcon("res/gato.png").getImage().getScaledInstance(tamanoIcono, tamanoIcono,
                                Image.SCALE_DEFAULT)));

                casilla.setName("X");

            } else {
                casilla.setIcon(new ImageIcon(
                        new ImageIcon("res/raton.png").getImage().getScaledInstance(tamanoIcono, tamanoIcono,
                                Image.SCALE_DEFAULT)));

                casilla.setName("O");
            }
            return true; // Cuando se marca en una casilla limpia
        }
        return false; // Cuando la casilla esta ocupada.
    }

    /**
     * Determina sí hay ganador ordenando las casillas en una matriz y buscando por
     * filas, columnas y diagonales.
     * 
     * @param turno El turno de cada jugador. True Gato y false Ratón.
     * @return True sí hay ganador. False sí no lo hay.
     */
    private boolean hayGanador(boolean turno) {
        String jugador = turno ? "X" : "O"; // Establece el turno X (Gato) o O (Ratón).
        int i = 0;
        matrizTablero = new JLabel[tamanoTablero][tamanoTablero]; // Crea una matriz con las casillas del tablero.

        for (int f = 0; f < tamanoTablero; f++) {
            for (int c = 0; c < tamanoTablero; c++) {
                matrizTablero[f][c] = (JLabel) tablero.getComponent(i);
                i++;
            }

        }

        if (hayGanadorPorFila(jugador)) {
            return true;
        }
        if (hayPorColumna(jugador)) {
            return true;
        }
        if (hayGanadorPorDiagonalDescendente(jugador)) {
            return true;
        }
        if (hayGanadorPorDiagonalAscendente(jugador)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica que el jugador complete una diagonal ascendente.
     * 
     * @param jugador El jugador a verificar.
     * @return True sí hay ganador. False sí no lo hay.
     */
    private boolean hayGanadorPorDiagonalAscendente(String jugador) {
        int count = 0;
        linea = new JLabel[tamanoTablero];

        for (int c = 0; c < matrizTablero[0].length; c++) {
            if (matrizTablero[c][(tamanoTablero - 1) - c].getName() == jugador) {
                linea[count] = matrizTablero[c][(tamanoTablero - 1) - c];
                count++; // Se incrementa cuando encuentra una casilla marcada para el jugador
            }
        }
        if (count == tamanoTablero) { // Cuando completa la línea
            resaltarLinea(linea);
            return true;
        }
        linea = null; // Sí no gana la linea pasa a null.
        return false;
    }

    /**
     * Verifica que el jugador complete una diagonal descendente.
     * 
     * @param jugador El jugador a verificar.
     * @return True sí hay ganador. False sí no lo hay.
     */
    private boolean hayGanadorPorDiagonalDescendente(String jugador) {
        int count = 0;
        linea = new JLabel[tamanoTablero];

        for (int c = 0; c < matrizTablero[0].length; c++) {
            if (matrizTablero[c][c].getName() == jugador) {
                linea[count] = matrizTablero[c][c];
                count++; // Se incrementa cuando encuentra una casilla marcada para el jugador
            }
        }
        if (count == tamanoTablero) { // Cuando completa la línea
            resaltarLinea(linea);
            return true;
        }
        linea = null; // Sí no gana la linea pasa a null.
        return false;
    }

    /**
     * Verifica que el jugador complete una columna.
     * 
     * @param jugador El jugador a verificar.
     * @return True sí hay ganador. False sí no lo hay.
     */
    private boolean hayPorColumna(String jugador) {
        linea = new JLabel[tamanoTablero];

        for (int c = 0; c < matrizTablero.length; c++) {
            int count = 0;
            for (int f = 0; f < matrizTablero[c].length; f++) {
                if (matrizTablero[f][c].getName() == jugador) {
                    linea[count] = matrizTablero[f][c];
                    count++; // Se incrementa cuando encuentra una casilla marcada para el jugador
                }
            }
            if (count == tamanoTablero) { // Cuando completa la línea
                resaltarLinea(linea);
                return true;
            }
        }
        linea = null; // Sí no gana la linea pasa a null.
        return false;
    }

    /**
     * Verifica que el jugador complete una fila.
     * 
     * @param jugador El jugador a verificar.
     * @return True sí hay ganador. False sí no lo hay.
     */
    private boolean hayGanadorPorFila(String jugador) {
        linea = new JLabel[tamanoTablero];

        for (int f = 0; f < matrizTablero.length; f++) {
            int count = 0;
            for (int c = 0; c < matrizTablero[f].length; c++) {
                if (matrizTablero[f][c].getName() == jugador) {
                    linea[count] = matrizTablero[f][c];
                    count++; // Se incrementa cuando encuentra una casilla marcada para el jugador
                }
            }
            if (count == tamanoTablero) { // Cuando completa la línea
                resaltarLinea(linea);
                return true;
            }
        }
        linea = null; // Sí no gana la linea pasa a null.
        return false;
    }

    /**
     * Resalta la linea en el tablero.
     * 
     * @param linea Arreglo con las casillas que completan la línea.
     */
    private void resaltarLinea(JLabel[] linea) {
        for (JLabel casilla : linea) {
            casilla.setBackground(Color.ORANGE);
        }
    }

    /**
     * Verifica sí quedan casillas disponibles para marcar.
     * 
     * @return True sí hay casillas disponibles. False sí no las hay.
     */
    private boolean hayEspacio() {

        for (Component comp : tablero.getComponents()) {
            if (comp.getName() == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hace el cambio de turno entre jugadores y cambia el icono para mostrar el
     * jugador que esta marcando.
     */
    private void cambiarTurno() {
        if (turno) {
            lblTurno.setIcon(new ImageIcon(
                    new ImageIcon("res/raton.png").getImage().getScaledInstance(48, 48,
                            Image.SCALE_DEFAULT)));

        } else {
            lblTurno.setIcon(new ImageIcon(
                    new ImageIcon("res/gato.png").getImage().getScaledInstance(48, 48,
                            Image.SCALE_DEFAULT)));

        }

        turno = !turno;
    }

    /**
     * Da 5 puntos a cada jugador cuando hay empate.
     */
    private void puntuar() {
        puntosX += 5;
        lblPuntosX.setText(String.valueOf(puntosX));
        puntosO += 5;
        lblPuntosO.setText(String.valueOf(puntosO));
        jugar = false;
    }

    /**
     * Da 10 puntos al jugador que gana la partida.
     * 
     * @param turno El jugador que gana la partida. True es jugadorX (Gato) y false
     *              es jugadorO (Ratón).
     */
    private void puntuar(boolean turno) {

        if (turno) {
            puntosX += 10;
            lblPuntosX.setText(String.valueOf(puntosX)); // Gato
        } else {
            puntosO += 10;
            lblPuntosO.setText(String.valueOf(puntosO)); // Ratón
        }
        jugar = false; // Para que al puntuar ya no se puede marcar.
    }

    /**
     * Reinicia las casillas del tablero para la siguiente partida. Cambia el
     * estado, habilita que se pueda marcar y alterna el turno.
     */
    private void reiniciarTablero() {
        for (Component casilla : tablero.getComponents()) {
            javax.swing.JLabel l = (javax.swing.JLabel) casilla;
            l.setBackground(Color.GRAY);
            l.setName(null); // Limpia la casilla
            l.setIcon(null); // Limpia el icono de la casilla
        }
        lblEstado.setText("");
        jugar = true; // Para que se pueda marcar
        cambiarTurno();
    }

    /**
     * Registra las puntuaciones de ambos jugadores en un archivo de texto.
     */
    private void registrarPuntuacion() {

        Archivo archivo = new Archivo(); // Para usar el archivo
        ArrayList<Puntuacion> puntuaciones = new ArrayList<Puntuacion>(); // ArrayList de las puntuaciones
        LocalDateTime fecha = LocalDateTime.now(); // La fecha y hora de hoy

        try { // Carga todas las puntuaciones
            puntuaciones = archivo.cargarPuntuaciones();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Agrega las puntuaciones nuevas de los dos jugadores
        puntuaciones.add(new Puntuacion(jugadorX, fecha, puntosX));
        puntuaciones.add(new Puntuacion(jugadorO, fecha, puntosO));

        try { // Sobre escribe el archivo con todas las puntuaciones
            archivo.guardarPuntuaciones(puntuaciones);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaGato.class

                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGato.class

                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGato.class

                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGato.class

                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaGato dialog = new VentanaGato(new javax.swing.JFrame(), true, jugadorX, jugadorO,
                        tamanoTablero);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblJugadorX;
    private javax.swing.JLabel lblPuntosX;
    private javax.swing.JLabel lblJugadorO;
    private javax.swing.JLabel lblPuntosO;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel tablero;
    // End of variables declaration//GEN-END:variables
}