import javax.swing.SwingUtilities;

public class Aplicacion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            vista_Principal vista = new vista_Principal();
            new controlador_Principal(vista);
            vista.setVisible(true);
        });
    }
}

