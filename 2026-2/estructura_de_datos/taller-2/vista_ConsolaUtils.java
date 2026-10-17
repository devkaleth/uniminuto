// ------ Proyecto Sistema de Calificaciones --------------------------------
// Archivo: vista_ConsolaUtils.java
// Contenido: Utilidades generales para la interacción con la consola
// -------------------------------------------------------------------------

import java.util.Scanner;

public class vista_ConsolaUtils {

    // Instancia única de Scanner (TECLADO) para evitar abrir múltiples flujos de entrada
    public static final Scanner teclado = new Scanner(System.in);
	
    // Referencia al controlador especializado en la administración de errores.
//    private static controlador_Errores controladorERR;
		
	/**
	 * Configura el controlador de errores que utilizarán
	 * los métodos auxiliares de la consola.
	 *
	 * Este método se ejecuta una sola vez al iniciar
	 * la aplicación.
	 */
//	public static void configurarControladorErrores(controlador_Errores controladorERR) {
//		vista_ConsolaUtils.controladorERR = controladorERR;
//	}	


	/**
	 * Presenta en consola el último error registrado por el sistema.
	 *
	 * La vista que detecta la situación solamente necesita invocar este método,
	 * quien es la que internamente mantiene el contacto con el controlador de errores.
	 * La vista que detecta NO necesita conocer ni dialogar con el controlador de errores.
	 */
/*	public static void mostrarError() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("** ERROR **");
		System.out.println("---------------------------------------------");

		// --------------------------------------------------
		// Consultamos el último error registrado.
		// -------------------------------
	 	RegistroError error = controladorERR.obtenerUltimoError();

		// --------------------------------------------------
		// Si encontramos el error en el diccionario,
		// presentamos su código y mensaje.
		// --------------------------------------------------
		if (error != null) { 
	 		System.out.println("(cod " + error.codError() + ") " + error.msjError() );
			} else {
				// ------------------------------------------------
				// E000:
				// La aplicación detectó una situación de error,
				// pero el código todavía no está catalogado.
				// ------------------------------------------------

			System.out.println("(cod E000) Error no especificado" );
		}
		System.out.println("---------------------------------------------" );

		// Después de presentar el error realizamos una pausa sin agregar
		// un mensaje adicional. Así dejamos que el usuario pueda leer
		// claramente la información antes de continuar con el proceso.
		pausar("");
	}
*/

    /*** Realiza una pausa en la aplicación de consola mostrando un mensaje personalizado.
     * @param mensaje El texto que se mostrará al usuario antes de la pausa.
     *
     * Si recibimos una cadena vacía, realizamos únicamente la pausa y
     * evitamos presentar una línea adicional en pantalla. De esta forma
     * podemos reutilizar este método después de cualquier presentación
     * que ya tenga su propio mensaje.
     */
    public static void pausar(String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            System.out.println("\n" + mensaje);
        }

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
