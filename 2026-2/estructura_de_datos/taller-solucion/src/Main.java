// --- Clase Principal (Punto de entrada) ---
// RESPONSABILIDAD: Iniciar la aplicación y conectar los componentes del patrón MVC.
public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos el Controlador (donde vive la lógica y las estructuras de datos).
        // Al instanciarse, su constructor cargará el catálogo de asignaturas internamente.
        controlador_Datos controlador = new controlador_Datos();

        // 2. Instanciamos la Vista (interfaz de usuario) y le pasamos el controlador.
        // Esto se llama "Inyección de dependencias". La vista ahora conoce al controlador para pedirle datos.
        vista_Principal vista = new vista_Principal(controlador);

        // 3. Le decimos a la vista que inicie la interacción con el usuario.
        vista.iniciarMenu();
    }
}
