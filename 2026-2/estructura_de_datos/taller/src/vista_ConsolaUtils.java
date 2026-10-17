import java.util.Scanner;

public class vista_ConsolaUtils {
    // Instancia única de Scanner (TECLADO) para evitar abrir múltiples flujos de entrada
    public static final Scanner teclado = new Scanner(System.in);
     /*** Realiza una pausa en la aplicación de consola mostrando un mensaje personalizado.
     * @param mensaje El texto que se mostrará al usuario antes de la pausa.
     */
    public static void pausar(String mensaje) {
        System.out.println("\n" + mensaje);
        System.out.println("Presione Enter para continuar...");
        teclado.nextLine(); // Espera el salto de línea
    }
     /*** Limpia por completo la pantalla de la consola y reposiciona el cursor al inicio.
     */
	public static void borrarPantalla() {
		try {
			String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				// Ejecuta el comando 'cls' de Windows a través del proceso del sistema
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				// Usa los códigos ANSI para Linux y macOS
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			// Si falla, al menos imprime líneas en blanco como respaldo
			for (int i = 0; i < 50; i++) System.out.println();
		}
	}

}