
public class vista_MenuPrincipal {

	// --- 1. FUNCIÓN PARA PINTAR EL MENÚ ---
	public static int pintarMenu() {
		ConsolaUtils.borrarPantalla();	   
		System.out.println("\n====================================");
		System.out.println("   Menu Sistema Calificaciones   ");
		System.out.println("====================================");
		System.out.println("1 - Cargar datos de prueba");
		System.out.println("2 - Mostrar listado de alumnos");
		System.out.println("3 - Insertar un alumno desde teclado");
		System.out.println("4 - Mostrar datos de UN Alumno (por índice)");
		System.out.println("5 - Mostrar Tabla Resumen de Calificaciones");
		System.out.println("6 - Exportar datos a archivo CSV");	   
		System.out.println("7 - Importar datos desde archivo CSV");			
		System.out.println("9 - Terminar");
		System.out.print("Elija una opcion: ");
		int opcionElegida = ConsolaUtils.teclado.nextInt();
		ConsolaUtils.teclado.nextLine(); 
	   return opcionElegida;
	}
}