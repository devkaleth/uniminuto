
// ------ Proyecto Sistema de Calificaciones --------------------------------
// Archivo: PersistenciaCSV.java
// Contenido: Módulo con las herramientas que prestan los servicios necesarios
// para almacenamiento y recuperación de datos en archivos CSV
//-----------------------------------------------------------------------------
// --- Carga de librerías de java necesarias para operaciones con archivos ---
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

// ------------------------------------------------------------------------
// CLASE PersistenciaCSV
// Este módulo del sistema se encarga de prestar los servicios necesarios
// para almacenamiento y recuperación de datos en archivos con formato CSV
// (archivos de texto con datos separados por comas)
// ------------------------------------------------------------------------
public class controlador_PersistenciaCSV {
	// --- Definimos los atributos que servirán como parámetros de operación de la persistencia.
    // Ruta donde se encuentran los archivos CSV.
    // Vacía significa: utilizar la carpeta de ejecución.
    private String rutaArchivo;

    // Nombre lógico de cada archivo de persistencia.
    private String archivoAlumnos;
    private String archivoAsignaturas;

    // Separador utilizado para construir y leer cada registro.
    private String separador;

	// --- aquí definimos los CONSTRUCTORES de los objetos PersistenciaCSV con sus diferentes
	// modalidades de sobrecargo
	
	// ------ Constructor Básico con valores por defecto
	public controlador_PersistenciaCSV() {
		// Configuración predeterminada para el taller.
		rutaArchivo = "";
		archivoAlumnos = "alumnos.csv";
		archivoAsignaturas = "asignaturas.csv";
		separador = ";";
	}

	// ------ Constructor con SOBRECARGO para transferir la ruta de ubicación de archivos
	public controlador_PersistenciaCSV(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
		this.archivoAlumnos = "alumnos.csv";
		this.archivoAsignaturas = "asignaturas.csv";
		this.separador = ";";
	}

	// ------ Constructor con SOBRECARGO COMPLETO: Se deben suministrar todos los parámetros
	// de operación exigidos por la clase
	public controlador_PersistenciaCSV(
			String rutaArchivo,
			String archivoAlumnos,
			String archivoAsignaturas,
			String separador) {
		this.rutaArchivo = rutaArchivo;
		this.archivoAlumnos = archivoAlumnos;
		this.archivoAsignaturas = archivoAsignaturas;
		this.separador = separador;
	}


	// --- aquí definimos los METODOS de los objetos PersistenciaCSV con sus diferentes
	// modalidades de sobrecargo

	// ------ Retorna la ruta física de ubicación de los archivos que requieren ser accesados
	// por los procesos de Persistencia de datos
	private String obtenerRutaCompleta(String nombreArchivo) {

		// Si no se especifica una ruta, se utiliza
		// directamente el nombre del archivo.
		if (rutaArchivo == null || rutaArchivo.isBlank()) {
			return nombreArchivo;
		}
		
		// File.separator permite trabajar con el separador
		// apropiado para el sistema operativo.
		return rutaArchivo + File.separator + nombreArchivo;
	}

	// ------ construye el archivo alumnos.csv. 
	//        recorre el listado de alumnos y escribe únicamente los atributos generales de RegistroEstudiante. 
	//        aquí no se escriben  las asignaturas porque estas serán tomadas al escribir la segunda entidad.
	public void exportarAlumnos( List<Ejercicio11.RegistroEstudiante> alumnos) {

		String rutaCompleta =
			obtenerRutaCompleta(archivoAlumnos);

		// --  se realiza el intento con control de errores para asegurar 
		//     que el programa  continúe en caso de alguna falla
		try (BufferedWriter salida =
				new BufferedWriter(
					new FileWriter(rutaCompleta))) {

			// Encabezado del archivo.
			salida.write(
				"nombre" + separador +
				"apellidos" + separador +
				"dia" + separador +
				"mes" + separador +
				"anio" + separador +
				"email" + separador +
				"telefono" + separador +
				"usaWhatsApp");
			salida.newLine();

			// Cada estudiante ocupa un registro.
			for (Ejercicio11.RegistroEstudiante alumno : alumnos) {

				salida.write(
					alumno.nombre() + separador +
					alumno.apellidos() + separador +
					alumno.fechaNac().dia() + separador +
					alumno.fechaNac().mes() + separador +
					alumno.fechaNac().anio() + separador +
					alumno.contacto().email() + separador +
					alumno.contacto().telefono() + separador +
					alumno.contacto().usaWhatsApp());

				salida.newLine();
			}

        //  --- En caso de alguna falla se muestra la situación ocurrida
		} catch (IOException e) {
			System.out.println(
				"Error al exportar alumnos: "
				+ e.getMessage());
		}
	}

	// ------ construye el archivo asignaturas.csv. 
	//    Por cada estudiante se recorre su listado de asignaturas 
	//    y se genera un registro independiente para cada una.
	//    En cada asignatura se agrega el nombre del estudiante
	//    para identificar a quien pertenece
	public void exportarAsignaturas( List<Ejercicio11.RegistroEstudiante> alumnos) {

		String rutaCompleta =
			obtenerRutaCompleta(archivoAsignaturas);

		// Realizamos cada intento de escritura con control de errores
		// para permitir  identificar cualquier error que se pueda presentar 
		try (BufferedWriter salida =
				new BufferedWriter(
					new FileWriter(rutaCompleta))) {

			// Encabezado del archivo académico.
			salida.write(
				"nombre" + separador +
				"nombreAsig" + separador +
				"cal1" + separador +
				"cal2" + separador +
				"cal3" + separador +
				"profesor");
			salida.newLine();

			// Recorremos primero los estudiantes.
			for (Ejercicio11.RegistroEstudiante alumno : alumnos) {

				// Cada estudiante puede tener varias asignaturas.
				for (Ejercicio11.RegistroAsignatura asignatura
						: alumno.asignaturas()) {

					salida.write(
						alumno.nombre() + separador +
						asignatura.nombreAsig() + separador +
						asignatura.calificaciones()[0] + separador +
						asignatura.calificaciones()[1] + separador +
						asignatura.calificaciones()[2] + separador +
						asignatura.profesor());

					salida.newLine();
				}
			}

        //  --- En caso de alguna falla se muestra la situación ocurrida
		} catch (IOException e) {
			System.out.println(
				"Error al exportar asignaturas: "
				+ e.getMessage());
		}
	}

    // ----- Método que controla la exportación en archivos separados
	//       Lanza primero la exportación e datos de Alumnos y luego las Asignaturas
	public void exportar_csv(
			List<Ejercicio11.RegistroEstudiante> alumnos) {

		// La exportación se divide en dos procesos:
		// uno para alumnos y otro para asignaturas.
		exportarAlumnos(alumnos);
		exportarAsignaturas(alumnos);
	}

	// ----  Importa Los datos almacenados en el archivo alumnos.csv
	//       por cada estudiante se crea un nuevo registro en el listadoAlumnos
	//       al final retorna el nuevo listado de alumnos  importados
	private List<Ejercicio11.RegistroEstudiante> importarAlumnos() {

		List<Ejercicio11.RegistroEstudiante> alumnos =
			new ArrayList<>();

		String rutaCompleta =
			obtenerRutaCompleta(archivoAlumnos);

		try (BufferedReader entrada =
				new BufferedReader(
					new FileReader(rutaCompleta))) {

			// La primera línea contiene los encabezados.
			String linea = entrada.readLine();

			while ((linea = entrada.readLine()) != null) {

				String[] datos =
					linea.split(separador, -1);

				String nombre = datos[0];
				String apellidos = datos[1];

				byte dia =
					Byte.parseByte(datos[2]);

				byte mes =
					Byte.parseByte(datos[3]);

				short anio =
					Short.parseShort(datos[4]);

				String email = datos[5];

				long telefono =
					Long.parseLong(datos[6]);

				boolean usaWhatsApp =
					Boolean.parseBoolean(datos[7]);

				Ejercicio11.RegistroFecha fecha =
					new Ejercicio11.RegistroFecha(dia, mes, anio);

				Ejercicio11.RegistroContacto contacto =
					new Ejercicio11.RegistroContacto(
						email,
						telefono,
						usaWhatsApp);

				alumnos.add(
					new Ejercicio11.RegistroEstudiante(
						nombre,
						apellidos,
						new ArrayList<>(),
						fecha,
						contacto
					)
				);
			}

		} catch (IOException e) {
			System.out.println(
				"Error al importar alumnos: "
				+ e.getMessage());
		}

		return alumnos;
	}

	// ----  Importa Los datos almacenados en el archivo asignaturas.csv
	//       por cada asignatura se crea un nuevo registro adicionandolo al 
	//       listado  de asignaturas que posee cada estudiante
	private void importarAsignaturas(
			List<Ejercicio11.RegistroEstudiante> alumnos) {

		String rutaCompleta =
			obtenerRutaCompleta(archivoAsignaturas);

		try (BufferedReader entrada =
				new BufferedReader(
					new FileReader(rutaCompleta))) {

			// Se descarta la línea de encabezados.
			String linea = entrada.readLine();

			while ((linea = entrada.readLine()) != null) {

				String[] datos =
					linea.split(separador, -1);

				String nombreAlumno = datos[0];
				String nombreAsig = datos[1];

				double[] calificaciones = {
					Double.parseDouble(datos[2]),
					Double.parseDouble(datos[3]),
					Double.parseDouble(datos[4])
				};

				String profesor = datos[5];

				// Buscamos el estudiante cuyo nombre coincide
				// con el nombre almacenado en el archivo.
				for (Ejercicio11.RegistroEstudiante alumno
						: alumnos) {

					if (alumno.nombre()
							.equalsIgnoreCase(nombreAlumno)) {

						alumno.asignaturas().add(
							new Ejercicio11.RegistroAsignatura(
								nombreAsig,
								calificaciones,
								profesor
							)
						);

						// El registro ya fue asociado.
						break;
					}
				}
			}

		} catch (IOException e) {
			System.out.println(
				"Error al importar asignaturas: "
				+ e.getMessage());
		}
	}

    // ----- Método que controla la IMPORTACION en archivos separados
	//       Lanza primero la exportación e datos de Alumnos y luego las Asignaturas
	public List<Ejercicio11.RegistroEstudiante>
	importar_csv() {

		// Primero se recuperan los datos generales.
		List<Ejercicio11.RegistroEstudiante> alumnos =
			importarAlumnos();

		// Después se relacionan las asignaturas.
		importarAsignaturas(alumnos);

		return alumnos;
	}

}