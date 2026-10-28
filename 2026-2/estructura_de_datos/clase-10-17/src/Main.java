import javax.swing.*;
import java.awt.*;

public class vista_Principal extends JFrame {
    private JButton btnRegistrar;
    private JButton btnAtender;
    private JButton btnMostrar;
    private JButton btnAnimar;
    private JButton btnSalir;

    public vista_Principal() {
        setTitle("Sistema de atención de estudiantes");
        setSize(760, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel(
                "Sistema de atención de estudiantes",
                SwingConstants.CENTER
        );
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 6, 6));
        panelBotones.setBorder(
                BorderFactory.createTitledBorder("Opciones")
        );

        btnRegistrar = new JButton("Registrar estudiante");
        btnAtender = new JButton("Atender siguiente");
        btnMostrar = new JButton("Mostrar lista");
        btnAnimar = new JButton("Animar recorrido");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtender);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnAnimar);
        panelBotones.add(btnSalir);

        JPanel panelTrabajo = new JPanel(new BorderLayout());
        panelTrabajo.setBorder(
                BorderFactory.createTitledBorder("Área de trabajo")
        );
        JLabel mensaje = new JLabel(
                "Aquí se visualizará la lista de estudiantes",
                SwingConstants.CENTER
        );
        panelTrabajo.add(mensaje, BorderLayout.CENTER);

        add(panelBotones, BorderLayout.WEST);
        add(panelTrabajo, BorderLayout.CENTER);
    }

    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnAtender() { return btnAtender; }
    public JButton getBtnMostrar() { return btnMostrar; }
    public JButton getBtnAnimar() { return btnAnimar; }
    public JButton getBtnSalir() { return btnSalir; }
}

