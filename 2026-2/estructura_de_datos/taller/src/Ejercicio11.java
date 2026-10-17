
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList; // Importación para la implementación ArrayList
import java.util.List;      // Importación para la interfaz List


 
public class Ejercicio11 {
   // --- DEFINICIÓN DE LOS RECORDS ---
   public record RegistroEstudiante(
           String nombre, 
           String apellidos, 
           List<RegistroAsignatura> asignaturas,
           RegistroFecha fechaNac,
		   RegistroContacto contacto
			) {}   
  
   public record RegistroAsignatura (
			String nombreAsig,
			double[] calificaciones,
			String profesor
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


			
    // --- VARIABLES GLOBALES (ESTATICAS) ---
    // Se reemplaza el arreglo estático por una lista dinámica (TDA List)
    public static List<RegistroEstudiante> ListadoAlumnos = new ArrayList<>();

	// --- GESTOR DE PERSISTENCIA ---
	// Referencia al objeto que administrará la lectura y escritura
	// de los archivos CSV.
	public static controlador_PersistenciaCSV gestorPersistencia;



   // --- MÉTODO PRINCIPAL (MAIN) ---
   public static void main(String[] args) {

		// Creamos el objeto que administrará la persistencia.
		// El constructor básico asigna la configuración
		// predeterminada de los archivos CSV.
		gestorPersistencia = new controlador_PersistenciaCSV();	   
		   
        int opcion;
        do {
           opcion = vista_MenuPrincipal.pintarMenu();
           GestionarOpcionesMenu(opcion);
        } while (opcion != 9); 
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
			case 6:
				exportarDatosCSV();
				ConsolaUtils.pausar("Proceso 6 finalizado...");	
				break;
			case 7:
				importarDatosCSV();
				ConsolaUtils.pausar("Proceso 7 finalizado...");	
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
 
       // --- ASIGNATURAS PRIMER ESTUDIANTE ---
       List<RegistroAsignatura> listadoAsignaturas = new ArrayList<>();
       listadoAsignaturas.add(new RegistroAsignatura("Sistemas", new double[]{3.5, 2.0, 4.5}, "Uriel"));
       listadoAsignaturas.add(new RegistroAsignatura("Algebra", new double[]{4.5, 2.5, 3.5}, "Gloria"));
 
       ListadoAlumnos.add(new RegistroEstudiante("Fredy", "Gomez",
			listadoAsignaturas,
			new RegistroFecha((byte)14, (byte)3, (short)2005), 
			new RegistroContacto("email1@abc.com",(long) 31223344,true)));
 

       // --- ASIGNATURAS SEGUNDO ESTUDIANTE (CREAMOS UNA NUEVA INSTANCIA) ---
       listadoAsignaturas = new ArrayList<>(); // <--- AQUI ESTA CREANDO UN NUEVO OBJETO VACIO
       listadoAsignaturas.add(new RegistroAsignatura("Algebra", new double[]{2.5, 3.8, 4.1}, "Gloria"));
       listadoAsignaturas.add(new RegistroAsignatura("Fisica", new double[]{5.0, 2.0, 1.0}, "Jorge"));
       listadoAsignaturas.add(new RegistroAsignatura("Sistemas", new double[]{4.8, 2.8, 1.8}, "Uriel"));
 
       ListadoAlumnos.add(new RegistroEstudiante("Carlos", "Perez",
			listadoAsignaturas,
			new RegistroFecha((byte)22, (byte)8, (short)2006), 
			new RegistroContacto("email2@xyz.com",(long)31666344,false)));
 
 
 
       // --- RESTO DE ESTUDIANTES ----
       listadoAsignaturas = new ArrayList<>(); // <--- AQUI ESTA CREANDO UN NUEVO OBJETO VACIO
       listadoAsignaturas.add(new RegistroAsignatura("Investigación", 
	   new double[]{3.5, 4.0, 2.9}, "Sandra"));	
  
       ListadoAlumnos.add(new RegistroEstudiante("Maria", "Lopez",
			listadoAsignaturas,
			new RegistroFecha((byte)5, (byte)12, (short)2005), 
			new RegistroContacto("email3@ijk.com",(long)33555443,true)));
		// ListadoAlumnos.get(ListadoAlumnos.size()).asignaturas().add(new RegistroAsignatura("Investigación", new double[]{3.5, 4.0, 2.9}, "Sandra"));	
			
       ListadoAlumnos.add(new RegistroEstudiante("Camilo", "Rojas", 
			new ArrayList<>(),	   
			new RegistroFecha((byte)30, (byte)1, (short)2006), 
			new RegistroContacto("email4@lmn.com",(long)77763344,false)));
			
       ListadoAlumnos.add(new RegistroEstudiante("Bertha", "Tinoco",
			new ArrayList<>(),
			new RegistroFecha((byte)18, (byte)7, (short)2005), 
			new RegistroContacto("email5@opq.com",(long)39923344,true)));
       System.out.println("Datos de prueba cargados correctamente.");
   }   

   // --- MODULO: MOSTRAR DATOS DE UN ALUMNO (OPCIÓN 4) --------
   // solicita  por teclado ingresar un numero que
   // identifica el alumno dentro de un listado
   // restricciones:   - debe validarse que El contador de Alumnos > 0
   //                  - que el indice seleccionado No sea mayor que la
   //                  - cantidad de alumnos ya gistrados. 
   //
   // 1- Validamos que hayan datos de alumnos (Cantidad de alumnos sea > 0)
   // si no se cumple, se muestra un mensaje y se termina la ejecucion de 
   // este modulo   
   public static void mostrarUnAlumno() {
       if (ListadoAlumnos.isEmpty()) {
           System.out.println("El listado está vacío. No hay alumnos para mostrar.");
           return;
       }
   // 2- leemos el índice del alumno requerido
       System.out.print("Ingrese el índice del alumno a mostrar...  ");
       int indiceAlumno = ConsolaUtils.teclado.nextInt();   
       ConsolaUtils.teclado.nextLine(); // Limpiar residuo numérico del buffer antes de salir
 
   // 3- verificamos que el valor del índice sea un dato correcto, que no supere
   // la catidad de alumnos existentes  y que sea >= 0
       // Se valida contra el tamaño actual de la lista usando .size()
       if (indiceAlumno < 1 || indiceAlumno > ListadoAlumnos.size()) {  
           System.out.println("Indice ingresado está fuera del rango");
           return;           
       }
	   
   // 4- Acesar los datos de alumno de ese ínice y mostrarlos en pantalla
   // Se obtiene el objeto de la lista usando .get(index)
       ImprimirDatosAlumno(ListadoAlumnos.get(indiceAlumno - 1), indiceAlumno - 1); 
   }

   // ----------------------------------------------------------
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

   // ----------------------------------------------------------
	// Imprime el listado resumen de todas las calificaciones por alumno
	// opción # 5 del menú
	public static void mostrarTablaResumenCalificaciones() {

	   ConsolaUtils.pausar("opcion temporalmente fuera de servicio.");		
	   /*		
		
		// Construiremos un reporte horizontal, 
		// con totalización en la última columna 
		// y total general al final (Promedio general del curso)

		System.out.println("\n--- RESUMEN DE CALIFICACIONES ---\n");

		// 1. Definimos la plantilla de formato para los datos del alumno:
		// %-20s = Texto alineado a la izquierda, ancho de 20
		// %7.1f = Número con 1 decimal alineado a la derecha, ancho de 7
		// %n    = Salto de línea automático
		// Nota: El consecutivo (#) se maneja manualmente centrado antes de este formato.
		String formatoDatos = "%-4d%-20s%-20s%7.1f%7.1f%7.1f%7.1f%n";

		// 2. Imprime los encabezados de las columnas 
		System.out.printf("%-5s%-20s%-20s%7s%7s%7s%7s%n","#Est.","NOMBRE", "APELLIDOS", "CAL1", "CAL2", "CAL3", "Def.");
		
		// Línea separadora ajustada al ancho total de la tabla (73 caracteres)
		System.out.println("-------------------------------------------------------------------------");

		// Variables para calcular el promedio general del curso al final
		float sumaPromediosCurso = 0;

		// 3. Recorremos el listado de alumnos
		for (int i = 0; i < contadorAlumnos; i++) {
			RegistroEstudiante alumno = ListadoAlumnos[i];

			// 4. Por cada alumno calculamos el promedio de las 3 calificaciones (Corregido el ';')
			double promedio = (
				alumno.calificaciones()[0] +
				alumno.calificaciones()[1] +  
				alumno.calificaciones()[2] 
			) / 3.0f;
			
			sumaPromediosCurso += promedio;

			// 5. Realiza la impresión de los datos del alumno actual
			// Luego el resto de las columnas con el printf
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
		
		// Cierre de la tabla
		System.out.println("-------------------------------------------------------------------------");
		
		// 6. Imprimir el Promedio General del Curso 
		if (contadorAlumnos > 0) {
			float promedioGeneral = sumaPromediosCurso / contadorAlumnos;
			System.out.printf("%52s%21.1f%n", "PROMEDIO GENERAL DEL CURSO:", promedioGeneral);
		}
		
		*/
	} 
      
   // ---- imprime los datos de un solo alumno....
   public static void ImprimirDatosAlumno (RegistroEstudiante alumno, int i) {

		   // ------------
		   // INICIA con la impresión de los datos básicos del registro
		   // ------------
           System.out.println("Estudiante: (" + String.valueOf(i+1) + ") " +
		                      alumno.nombre() + " " + 
							  alumno.apellidos());
           
           // ------------------
           // Imprime el contenido del registro tipo Fecha, 
		   // junto con el llamado al método que calcula y retorna la edad 
           // ------------------   		   
           System.out.printf("Fecha de Nacimiento: %02d/%02d/%04d\n", 
               alumno.fechaNac().dia(),
			   alumno.fechaNac().mes(), 
			   alumno.fechaNac().anio() );
		   System.out.printf("Edad:  %d años%n", calcularEdad(alumno.fechaNac()));	   
		   
		   // ---------------------
		   // INCORPORA LOS DATOS Del registro tipo CONTACTO
		   // Utiliza "operador Ternario"  
		   // para mostrar el "Si" o el "No" del dato booleano usaWhatsApp 
		   // ---------
           System.out.println("email: " + alumno.contacto().email());
		   System.out.println("teléfono: " + String.valueOf(alumno.contacto().telefono()));   
		   System.out.println("usa WhatsApp: " + (alumno.contacto().usaWhatsApp() ? "SI" : "NO"));

           // --------------
           // Continúa con la impresión de notas  
		   // Recorre con ub bucle el listado de notas
		   // calculando el promedio de las calificaciones acumuladas
           // --------------		   
           System.out.println("------------------------------------");   
           System.out.println("Asignaturas y Calificaciones: ");
		   
		   // Recorremos el listado de asignaturas que tiene registras ese alumno
			for (int a = 0; a < alumno.asignaturas.size(); a++) {
			    RegistroAsignatura asignatura = alumno.asignaturas.get(a); 
				double suma = 0;
				
				System.out.print(asignatura.nombreAsig + "  (profesor: " +
				                 asignatura.profesor + ") ");
				for (int j = 0; j < asignatura.calificaciones().length; j++) {
					   System.out.print(asignatura.calificaciones()[j] + "  ");
					   suma += asignatura.calificaciones()[j];
				    }
				double promedio = suma / asignatura.calificaciones().length;
				System.out.printf("  prom: %.2f%n", promedio);   					

			}   
  
           System.out.println("------------------------------------");	   
   }


   // --- MÓDULO: INSERTAR DESDE TECLADO (OPCIÓN 3) ---
   public static void insertarAlumnoTeclado() {
	   
	   ConsolaUtils.pausar("opcion temporalmente fuera de servicio.");		
	   /*
	   
       // Validar si el arreglo ya está lleno
       if (contadorAlumnos >= ListadoAlumnos.length) {
           System.out.println("Error: Memoria llena. No se pueden registrar más alumnos.");
           return;
       }

       System.out.println("\n--- REGISTRO DE NUEVO ALUMNO ---");
       System.out.print("Nombre: ");
       String nombre = ConsolaUtils.teclado.nextLine();
       System.out.print("Apellidos: ");
       String apellidos = ConsolaUtils.teclado.nextLine();
       // Captura de la fecha de nacimiento
       System.out.print("Día de nacimiento (dd): ");
       byte dia = ConsolaUtils.teclado.nextByte();
       System.out.print("Mes de nacimiento (mm): ");
       byte mes = ConsolaUtils.teclado.nextByte();
       System.out.print("Año de nacimiento (aaaa): ");
       short anio = ConsolaUtils.teclado.nextShort();
	   
       // Captura de las 3 calificaciones obligatorias
       double[] calificaciones = new double[3];
       System.out.println("Ingrese las 3 calificaciones:");
       for (int j = 0; j < calificaciones.length; j++) {
           System.out.print("Nota " + (j + 1) + ": ");
           calificaciones[j] = ConsolaUtils.teclado.nextDouble();
       }
       ConsolaUtils.teclado.nextLine(); // Limpiar residuo numérico del buffer antes de salir

       // AQUI capturo por teclado los datos de contacto
	   // -----------------------------------------------
        System.out.print("Introduce el email: ");
        String email = ConsolaUtils.teclado.nextLine();

        System.out.print("Introduce el teléfono: ");
        long telefono = ConsolaUtils.teclado.nextLong();
        ConsolaUtils.teclado.nextLine(); // Limpiar el buffer del teclado

        System.out.print("¿Usa WhatsApp? (SI/NO): ");
        String respuestaWhatsApp = ConsolaUtils.teclado.nextLine();

        // Operador ternario para convertir "SI" en true y cualquier otra cosa en false
        // .equalsIgnoreCase permite aceptar "si", "Si", "SI" o "sI"
        boolean usaWhatsApp = respuestaWhatsApp.equalsIgnoreCase("SI") ? true : false;

       // Crear el nuevo objeto Record y guardarlo en la posición actual del contador
       ListadoAlumnos[contadorAlumnos] = new RegistroEstudiante(
           nombre, 
           apellidos, 
           calificaciones, 
           new RegistroFecha(dia, mes, anio),
		   new RegistroContacto(email, telefono, usaWhatsApp)
       );
	   
       // Incrementar el contador para el próximo alumno
       contadorAlumnos++;
       System.out.println("¡Alumno registrado con éxito!");
	   
	   */
	   
   }

	public static int calcularEdad(RegistroFecha fechaNac) {
		LocalDate fechaNacimiento = LocalDate.of(fechaNac.anio(), fechaNac.mes(), fechaNac.dia());
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fechaNacimiento, fechaActual).getYears();
	} 

   
	// --- MÓDULO: EXPORTAR DATOS (OPCIÓN 6) ---
	// Solicita al gestor de persistencia que tome la información 
	// actualmente almacenada en memoria y la escriba en los archivos CSV.
	public static void exportarDatosCSV() {

		gestorPersistencia.exportar_csv(ListadoAlumnos);

		System.out.println(
			"Datos exportados correctamente.");
	}

	// --- MÓDULO: IMPOTAR DATOS (OPCIÓN 7) ---
	// Solicita al gestor de persistencia la lectura y carga de datos
	// externos, residentes en los archivos CSV  manejados por el sistema
	public static void importarDatosCSV() {

		// Solicitamos al gestor la reconstrucción
		// completa de la información.
		List<RegistroEstudiante> datosImportados =
			gestorPersistencia.importar_csv();

		if (datosImportados.isEmpty()) {
			System.out.println(
				"No se encontraron datos para importar.");
			return;
		}

		// Reemplazamos la información actual
		// por la información recuperada desde los archivos.
		ListadoAlumnos.clear();
		ListadoAlumnos.addAll(datosImportados);

		System.out.println(
			"Datos importados correctamente.");
	}

	
}
