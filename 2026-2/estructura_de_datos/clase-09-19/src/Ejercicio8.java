import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList; // Importación para la implementación ArrayList
import java.util.List;      // Importación para la interfaz List


class ConsolaUtils {
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
 
public class
Ejercicio8 {
   // --- DEFINICIÓN DE LOS RECORDS ---
   public record RegistroEstudiante(
           String nombre, 
           String apellidos, 
           double[] calificaciones,
           RegistroFecha fechaNac
			) {}   
   
   public record RegistroFecha(
          byte  dia, 
		  byte  mes, 
		  short anio
		  ) {}

   public record RegistroContacto (
			String email,
			long   telefono,
			boolean usaWhatsApp
			) {}


   // --- VARIABLES GLOBALES (ESTÁTICAS) ---
   // Se reemplaza el arreglo estático por una lista dinámica (TDA List)
   public static List<RegistroEstudiante> ListadoAlumnos = new ArrayList<>();

   // --- MÉTODO PRINCIPAL (MAIN) ---
   public static void main(String[] args) {
       int opcion;
       do {
           opcion = pintarMenu();
           GestionarOpcionesMenu(opcion);
       } while (opcion != 9); 
   }

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
       System.out.println("9 - Terminar");
       System.out.print("Elija una opcion: ");
       int opcionElegida = ConsolaUtils.teclado.nextInt();
       ConsolaUtils.teclado.nextLine(); 
       return opcionElegida;
   }

   // --- 2. FUNCIÓN PARA CONTROLAR EL FLUJO (SWITCH) ---
   public static void GestionarOpcionesMenu(int opcionMenu) {
       switch (opcionMenu) {
           case 1:
                cargarDatosPrueba();
				ConsolaUtils.pausar("Proceso 1 completado con éxito.");			   
               break;
           case 2:
               mostrarListadoAlumnos();
				ConsolaUtils.pausar("Proceso 2 completado con éxito.");			   
               break;
           case 3:
               insertarAlumnoTeclado();
               break;
		   case 4: 
				mostrarUnAlumno();
				ConsolaUtils.pausar("Proceso 4 completado con éxito.");					
				break;
		   case 5: 
			   mostrarTablaResumenCalificaciones();
			   ConsolaUtils.pausar("Proceso 5 completado con éxito.");					
               break;
           case 9:
               System.out.println("Saliendo del sistema. ¡Hasta luego!");
               break;
           default:
               System.out.println("Opción no válida. Intente nuevamente.");
       }
   }

   // --- MÓDULO: CARGAR DATOS DE PRUEBA (OPCIÓN 1) ---
   public static void cargarDatosPrueba() {
       // Evaluamos si la lista ya tiene elementos usando .isEmpty()
       if (!ListadoAlumnos.isEmpty()) {
           System.out.println("Ya existen datos en el sistema. No se cargaron los de prueba.");
           return;
       }

       // Añadimos los elementos directamente usando el método .add()
       ListadoAlumnos.add(new RegistroEstudiante("Fredy", "Gomez", new double[]{3.5, 2.0, 4.5}, new RegistroFecha((byte)14, (byte)3, (short)2005)));
       ListadoAlumnos.add(new RegistroEstudiante("Carlos", "Perez", new double[]{2.9, 3.3, 4.5}, new RegistroFecha((byte)22, (byte)8, (short)2006)));
       ListadoAlumnos.add(new RegistroEstudiante("Maria", "Lopez", new double[]{1.0, 2.7, 5.0}, new RegistroFecha((byte)5, (byte)12, (short)2005)));
       ListadoAlumnos.add(new RegistroEstudiante("Camilo", "Rojas", new double[]{3.3, 2.2, 4.4}, new RegistroFecha((byte)30, (byte)1, (short)2006)));
       ListadoAlumnos.add(new RegistroEstudiante("Bertha", "Diaz", new double[]{1.0, 2.0, 3.0}, new RegistroFecha((byte)18, (byte)7, (short)2005)));
       
       System.out.println("¡Datos de prueba cargados con éxito! (5 alumnos añadidos).");
   }

   // --- MODULO: MOSTRAR DATOS DE UN ALUMNO (OPCIÓN 4) --------
   public static void mostrarUnAlumno() {
       if (ListadoAlumnos.isEmpty()) {
           System.out.println("El listado está vacío. No hay alumnos para mostrar.");
           return;
       }
       System.out.print("Ingrese el índice del alumno a mostrar...  ");
       int indiceAlumno = ConsolaUtils.teclado.nextInt();   
       ConsolaUtils.teclado.nextLine(); 
 
       // Se valida contra el tamaño actual de la lista usando .size()
       if (indiceAlumno < 1 || indiceAlumno > ListadoAlumnos.size()) {  
           System.out.println("Indice ingresado está fuera del rango");
           return;           
       }
       // Se obtiene el objeto de la lista usando .get(index)
       ImprimirDatosAlumno(ListadoAlumnos.get(indiceAlumno - 1), indiceAlumno - 1); 
   }


   // --- MÓDULO: MOSTRAR LISTADO (OPCIÓN 2) ---
   public static void mostrarListadoAlumnos() {
       if (ListadoAlumnos.isEmpty()) {
           System.out.println("El listado está vacío. No hay alumnos para mostrar.");
           return;
       }

       System.out.println("\n====== LISTADO DE ESTUDIANTES ======");
       // Recorremos usando .size()
       for (int i = 0; i < ListadoAlumnos.size(); i++) {
           RegistroEstudiante alumno = ListadoAlumnos.get(i); // Extraemos el alumno con .get(i)
           ImprimirDatosAlumno(alumno, i); 
       }           
   }

	// Imprime el listado resumen de todas las calificaciones por alumno
	public static void mostrarTablaResumenCalificaciones() {
		System.out.println("\n--- RESUMEN DE CALIFICACIONES ---\n");

		String formatoDatos = "%-4d%-20s%-20s%7.1f%7.1f%7.1f%7.1f%n";

		System.out.printf("%-5s%-20s%-20s%7s%7s%7s%7s%n","#Est.","NOMBRE", "APELLIDOS", "CAL1", "CAL2", "CAL3", "Def.");
		System.out.println("-------------------------------------------------------------------------");

		float sumaPromediosCurso = 0;

		// El ciclo ahora recorre hasta ListadoAlumnos.size()
		for (int i = 0; i < ListadoAlumnos.size(); i++) {
			RegistroEstudiante alumno = ListadoAlumnos.get(i); 

			// Corregido: Se mantiene el acceso al arreglo interno del record de forma explícita
			double promedio = (
				alumno.calificaciones()[0] +
				alumno.calificaciones()[1] +  
				alumno.calificaciones()[2] 
			) / 3.0f;
			
			sumaPromediosCurso += promedio;

			System.out.printf(formatoDatos, 
			    (i + 1),
				alumno.nombre(),  
				alumno.apellidos(),
				alumno.calificaciones()[0],
				alumno.calificaciones()[1],      
				alumno.calificaciones()[2],
				promedio
			);   
		}
		System.out.println("-------------------------------------------------------------------------");
		
		if (!ListadoAlumnos.isEmpty()) {
			float promedioGeneral = sumaPromediosCurso / ListadoAlumnos.size();
			System.out.printf("%52s%21.1f%n", "PROMEDIO GENERAL DEL CURSO:", promedioGeneral);
		}
	} 
      
   // ---- imprime los datos de un solo alumno....
   public static void ImprimirDatosAlumno (RegistroEstudiante alumno, int i) {
           double suma = 0;
           System.out.println("Estudiante: (" + String.valueOf(i+1) + ") " +
		                      alumno.nombre() + " " + 
							  alumno.apellidos()); 
           System.out.printf("Fecha de Nacimiento: %02d/%02d/%04d\n", 
               alumno.fechaNac().dia(),
			   alumno.fechaNac().mes(), 
			   alumno.fechaNac().anio() );
		   System.out.printf("Edad:  %d años%n", calcularEdad(alumno.fechaNac()));	   
           System.out.println("------------------------------------");
           System.out.print("Notas: ");
           for (int j = 0; j < alumno.calificaciones().length; j++) {
               System.out.print(alumno.calificaciones()[j] + "  ");
               suma += alumno.calificaciones()[j];
           }
           System.out.println(); 
           double promedio = suma / alumno.calificaciones().length;
           System.out.printf("El promedio es: %.2f%n", promedio);            
           System.out.println("------------------------------------");	   
   }


   // --- MÓDULO: INSERTAR DESDE TECLADO (OPCIÓN 3) ---
   public static void insertarAlumnoTeclado() {
       System.out.println("\n--- REGISTRO DE NUEVO ALUMNO ---");
       System.out.print("Nombre: ");
       String nombre = ConsolaUtils.teclado.nextLine();
       System.out.print("Apellidos: ");
       String apellidos = ConsolaUtils.teclado.nextLine();
       System.out.print("Día de nacimiento (dd): ");
       byte dia = ConsolaUtils.teclado.nextByte();
       System.out.print("Mes de nacimiento (mm): ");
       byte mes = ConsolaUtils.teclado.nextByte();
       System.out.print("Año de nacimiento (aaaa): ");
       short anio = ConsolaUtils.teclado.nextShort();
       
       double[] calificaciones = new double[3];
       System.out.println("Ingrese las 3 calificaciones:");
       for (int j = 0; j < calificaciones.length; j++) {
           System.out.print("Nota " + (j + 1) + ": ");
           calificaciones[j] = ConsolaUtils.teclado.nextDouble();
       }
       ConsolaUtils.teclado.nextLine(); 

       // Construimos el Record y lo insertamos directamente con .add()
       ListadoAlumnos.add(new RegistroEstudiante(
           nombre, 
           apellidos, 
           calificaciones, 
           new RegistroFecha(dia, mes, anio)
       ));
       
       System.out.println("¡Alumno registrado con éxito!");
   }

	public static int calcularEdad(RegistroFecha fechaNac) {
		LocalDate fechaNacimiento = LocalDate.of(fechaNac.anio(), fechaNac.mes(), fechaNac.dia());
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fechaNacimiento, fechaActual).getYears();
	}   
}
