import javax.swing.*;

public class controlador_Principal {
    private final vista_Principal vista;

    public controlador_Principal(vista_Principal vista) {
        this.vista = vista;
        configurarEventos();
    }

    private void configurarEventos() {
        vista.getBtnRegistrar().addActionListener(e ->
            mostrarEnConstruccion("Registrar estudiante")
        );

        vista.getBtnAtender().addActionListener(e ->
            mostrarEnConstruccion("Atender siguiente")
        );

        vista.getBtnMostrar().addActionListener(e ->
            mostrarEnConstruccion("Mostrar lista")
        );

        vista.getBtnAnimar().addActionListener(e ->
            mostrarEnConstruccion("Animar recorrido")
        );

        vista.getBtnSalir().addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea salir de la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                vista.dispose();
            }
        });
    }

    private void mostrarEnConstruccion(String funcionalidad) {
        JOptionPane.showMessageDialog(
            vista,
            funcionalidad + ": funcionalidad en construcción.",
            "En construcción",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

