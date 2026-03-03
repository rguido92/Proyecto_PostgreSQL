import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.*;

public class Frontend {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField nombreEquipoField;
    private JTextField ciudadEquipoField;
    private JTextField entrenadorNombreField;
    private JTextField entrenadorEdadField;

    private JTextField listarEquipoIdField;

    // Jugadores
    private JTextField jugadorNombreField;
    private JTextField jugadorEdadField;
    private JTextField jugadorDorsalField;
    private JTextField jugadorPosicionField;
    private JTextField jugadorAlturaField;
    private JTextField jugadorEquipoIdField;

    private JTextField jugadorIdModificarField;

    // Partidos
    private JTextField partidoFechaField; // formato yyyy-MM-dd
    private JTextField partidoLocalIdField;
    private JTextField partidoVisitanteIdField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frontend ui = new Frontend();
            ui.initConsoleRedirect();
            ui.createAndShowGUI();
        });
    }

    private void initConsoleRedirect() {
        outputArea = new JTextArea(15, 60);
        outputArea.setEditable(false);
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> {
                    outputArea.append(String.valueOf((char) b));
                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                });
            }
        }, true);
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Frontend Futbol (PostgreSQL)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Equipos", createEquiposPanel());
        tabs.addTab("Jugadores", createJugadoresPanel());
        tabs.addTab("Partidos", createPartidosPanel());
        tabs.addTab("Consultas", createConsultasPanel());

        mainPanel.add(tabs, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createEquiposPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        nombreEquipoField = new JTextField(15);
        ciudadEquipoField = new JTextField(15);
        entrenadorNombreField = new JTextField(15);
        entrenadorEdadField = new JTextField(5);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Nombre equipo:"), c);
        c.gridx = 1;
        panel.add(nombreEquipoField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Ciudad:"), c);
        c.gridx = 1;
        panel.add(ciudadEquipoField, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Nombre entrenador:"), c);
        c.gridx = 1;
        panel.add(entrenadorNombreField, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Edad entrenador:"), c);
        c.gridx = 1;
        panel.add(entrenadorEdadField, c);

        JButton insertarBtn = new JButton("Insertar equipo");
        insertarBtn.addActionListener(this::onInsertarEquipo);

        JButton listarBtn = new JButton("Listar todos los equipos");
        listarBtn.addActionListener(this::onListarEquipos);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(insertarBtn);
        botones.add(listarBtn);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        panel.add(botones, c);

        return panel;
    }

    private JPanel createConsultasPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        listarEquipoIdField = new JTextField(10);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("ID equipo:"), c);
        c.gridx = 1;
        panel.add(listarEquipoIdField, c);

        JButton buscarBtn = new JButton("Listar equipo por ID");
        buscarBtn.addActionListener(this::onListarEquipoPorId);

        JButton listarTodosBtn = new JButton("Listar todos los equipos");
        listarTodosBtn.addActionListener(this::onListarEquipos);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(buscarBtn);
        botones.add(listarTodosBtn);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(botones, c);

        return panel;
    }

    private JPanel createJugadoresPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        jugadorNombreField = new JTextField(15);
        jugadorEdadField = new JTextField(5);
        jugadorDorsalField = new JTextField(5);
        jugadorPosicionField = new JTextField(10);
        jugadorAlturaField = new JTextField(5);
        jugadorEquipoIdField = new JTextField(5);
        jugadorIdModificarField = new JTextField(5);

        int row = 0;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Nombre jugador:"), c);
        c.gridx = 1;
        panel.add(jugadorNombreField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Edad:"), c);
        c.gridx = 1;
        panel.add(jugadorEdadField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Dorsal:"), c);
        c.gridx = 1;
        panel.add(jugadorDorsalField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Posición:"), c);
        c.gridx = 1;
        panel.add(jugadorPosicionField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Altura (m):"), c);
        c.gridx = 1;
        panel.add(jugadorAlturaField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("ID equipo:"), c);
        c.gridx = 1;
        panel.add(jugadorEquipoIdField, c);

        JButton insertarJugadorBtn = new JButton("Insertar jugador");
        insertarJugadorBtn.addActionListener(this::onInsertarJugador);

        JButton listarJugadoresPosBtn = new JButton("Jugadores por posición");
        listarJugadoresPosBtn.addActionListener(this::onJugadoresPorPosicion);

        JButton listarJugadoresDorsalBtn = new JButton("Jugadores por dorsal");
        listarJugadoresDorsalBtn.addActionListener(this::onJugadoresPorDorsal);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(insertarJugadorBtn);
        botones.add(listarJugadoresPosBtn);
        botones.add(listarJugadoresDorsalBtn);

        row++;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        panel.add(botones, c);

        return panel;
    }

    private JPanel createPartidosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        partidoFechaField = new JTextField(10);
        partidoLocalIdField = new JTextField(5);
        partidoVisitanteIdField = new JTextField(5);

        int row = 0;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("Fecha (yyyy-MM-dd):"), c);
        c.gridx = 1;
        panel.add(partidoFechaField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("ID equipo local:"), c);
        c.gridx = 1;
        panel.add(partidoLocalIdField, c);

        row++;
        c.gridx = 0;
        c.gridy = row;
        panel.add(new JLabel("ID equipo visitante:"), c);
        c.gridx = 1;
        panel.add(partidoVisitanteIdField, c);

        JButton insertarPartidoBtn = new JButton("Insertar partido");
        insertarPartidoBtn.addActionListener(this::onInsertarPartido);

        JButton partidosLocalBtn = new JButton("Partidos como local");
        partidosLocalBtn.addActionListener(this::onBuscarPartidosLocal);

        JButton partidosVisitanteBtn = new JButton("Partidos como visitante");
        partidosVisitanteBtn.addActionListener(this::onBuscarPartidosVisitante);

        JButton partidosFechaBtn = new JButton("Partidos por fecha");
        partidosFechaBtn.addActionListener(this::onPartidosPorFecha);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(insertarPartidoBtn);
        botones.add(partidosLocalBtn);
        botones.add(partidosVisitanteBtn);
        botones.add(partidosFechaBtn);

        row++;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        panel.add(botones, c);

        return panel;
    }

    private void onInsertarEquipo(ActionEvent e) {
        String nombre = nombreEquipoField.getText().trim();
        String ciudad = ciudadEquipoField.getText().trim();
        String entrenador = entrenadorNombreField.getText().trim();
        String edadStr = entrenadorEdadField.getText().trim();

        if (nombre.isEmpty() || ciudad.isEmpty() || entrenador.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Rellena todos los campos", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            App.conectarBD();
            App.insertarEquipo(nombre, ciudad, entrenador, edad);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "La edad debe ser un número entero", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al insertar equipo: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onListarEquipos(ActionEvent e) {
        try {
            App.conectarBD();
            App.listarTodosEquipos();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al listar equipos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onListarEquipoPorId(ActionEvent e) {
        String idStr = listarEquipoIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Introduce un ID de equipo", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            App.conectarBD();
            App.listarEquipoPorId(id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El ID debe ser un número entero", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar equipo: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onInsertarJugador(ActionEvent e) {
        String nombre = jugadorNombreField.getText().trim();
        String edadStr = jugadorEdadField.getText().trim();
        String dorsalStr = jugadorDorsalField.getText().trim();
        String posicion = jugadorPosicionField.getText().trim();
        String alturaStr = jugadorAlturaField.getText().trim();
        String equipoIdStr = jugadorEquipoIdField.getText().trim();

        if (nombre.isEmpty() || edadStr.isEmpty() || dorsalStr.isEmpty() || posicion.isEmpty()
                || alturaStr.isEmpty() || equipoIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Rellena todos los campos del jugador", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            int dorsal = Integer.parseInt(dorsalStr);
            float altura = Float.parseFloat(alturaStr.replace(",", "."));
            int equipoId = Integer.parseInt(equipoIdStr);

            App.conectarBD();
            App.insertarJugador(nombre, edad, dorsal, posicion, altura, equipoId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Edad, dorsal, altura y ID de equipo deben ser numéricos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al insertar jugador: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onJugadoresPorPosicion(ActionEvent e) {
        String posicion = JOptionPane.showInputDialog(frame, "Introduce la posición (ej. DEL, MED, DEF, POR):");
        if (posicion == null || posicion.trim().isEmpty()) {
            return;
        }
        try {
            App.conectarBD();
            App.obtenerJugadoresPorPosicion(posicion.trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar jugadores: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onJugadoresPorDorsal(ActionEvent e) {
        String dorsalStr = JOptionPane.showInputDialog(frame, "Introduce el dorsal:");
        if (dorsalStr == null || dorsalStr.trim().isEmpty()) {
            return;
        }
        try {
            int dorsal = Integer.parseInt(dorsalStr.trim());
            App.conectarBD();
            App.obtenerJugadoresPorDorsal(dorsal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El dorsal debe ser un número entero", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar jugadores: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onInsertarPartido(ActionEvent e) {
        String fechaStr = partidoFechaField.getText().trim();
        String localStr = partidoLocalIdField.getText().trim();
        String visitanteStr = partidoVisitanteIdField.getText().trim();

        if (fechaStr.isEmpty() || localStr.isEmpty() || visitanteStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Rellena fecha, equipo local y visitante", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
            int idLocal = Integer.parseInt(localStr);
            int idVisitante = Integer.parseInt(visitanteStr);

            App.conectarBD();
            // método insertarPartido es package-private; llamamos usando una pequeña envoltura
            insertarPartidoWrapper(fecha, idLocal, idVisitante);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, "Fecha con formato incorrecto, usa yyyy-MM-dd", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al insertar partido: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Usa reflexión para llamar a App.insertarPartido(Date,int,int) que es package-private
    private void insertarPartidoWrapper(java.sql.Date fecha, int idLocal, int idVisitante) throws Exception {
        java.lang.reflect.Method m = App.class.getDeclaredMethod("insertarPartido",
                java.sql.Date.class, int.class, int.class);
        m.setAccessible(true);
        m.invoke(null, fecha, idLocal, idVisitante);
    }

    private void onBuscarPartidosLocal(ActionEvent e) {
        String idStr = JOptionPane.showInputDialog(frame, "ID equipo local:");
        if (idStr == null || idStr.trim().isEmpty()) {
            return;
        }
        try {
            int id = Integer.parseInt(idStr.trim());
            App.conectarBD();
            App.buscarPartidosLocal(id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El ID debe ser entero", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar partidos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onBuscarPartidosVisitante(ActionEvent e) {
        String idStr = JOptionPane.showInputDialog(frame, "ID equipo visitante:");
        if (idStr == null || idStr.trim().isEmpty()) {
            return;
        }
        try {
            int id = Integer.parseInt(idStr.trim());
            App.conectarBD();
            App.buscarPartidosVisitante(id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El ID debe ser entero", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar partidos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onPartidosPorFecha(ActionEvent e) {
        String fechaStr = JOptionPane.showInputDialog(frame, "Fecha (yyyy-MM-dd):");
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return;
        }
        try {
            java.sql.Date fecha = java.sql.Date.valueOf(fechaStr.trim());
            App.conectarBD();
            App.obtenerPartidosPorFecha(fecha);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, "Fecha con formato incorrecto, usa yyyy-MM-dd", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al buscar partidos: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
