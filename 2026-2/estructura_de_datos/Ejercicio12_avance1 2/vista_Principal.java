/*
 * RESPONSABILIDAD:
 * Interactuar con el usuario.
 *
 * La vista muestra menús, recibe opciones
 */
 
import java.util.Map;

public class vista_Principal {

    private controlador_Datos controlador;

    public vista_Principal(controlador_Datos controlador) {

        this.controlador = controlador;
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = Integer.parseInt(
                    vista_ConsolaUtils.teclado.nextLine());

            switch (opcion) {

                case 1:

                    presentarCatalogoAsignaturas();

                    vista_ConsolaUtils.pausar(
                            "listado impreso correctamente...");
                    break;

				case 2:

					controlador.construirDatos();

					presentarPersonas();
					presentarAlumnos();
					presentarDocentes();

					vista_ConsolaUtils.pausar(
						"Presione ENTER para continuar...");
					break;

				case 3:

					// Garantiza que las estructuras estén cargadas.
					controlador.construirDatos();

					mostrarInformacionDetalladaAlumnos();

					vista_ConsolaUtils.pausar(
							"Presione ENTER para continuar...");

					break;
					
				case 4:

					// Aseguramos que los datos estén cargados.
					controlador.construirDatos();

					buscarAlumnoPorCodigo();

					vista_ConsolaUtils.pausar(
							"Presione ENTER para continuar...");

					break;					

                case 0:

                    System.out.println(
                            "Programa finalizado.");
                    break;

                default:

                    System.out.println(
                            "Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        vista_ConsolaUtils.borrarPantalla();

        System.out.println("=================================");
        System.out.println("           MENÚ");
        System.out.println("=================================");
        System.out.println("1. Mostrar catálogo");
		System.out.println("2. Construir y mostrar datos");		
        System.out.println("3. Mostrar información detallada de todos los alumnos");	
		System.out.println("4. Buscar alumno por identificador");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    private void presentarCatalogoAsignaturas() {

        Map<String, modelo_AsignaturaCatalogo> tabla =
                controlador.getTablaAsignaturasCatalogo();

        System.out.println();
        System.out.println("CATALOGO DE ASIGNATURAS");
        System.out.println("--------------------------------");

        for (modelo_AsignaturaCatalogo asignatura
                : tabla.values()) {

            System.out.println(
                    asignatura.getCodigo()
                            + " - "
                            + asignatura.getNombre());
        }

        System.out.println("--------------------------------");
    }
	
	// ==========================================
	private void presentarPersonas() {

		System.out.println();
		System.out.println("LISTADO DE PERSONAS");
		System.out.println();

		for (modelo_Persona persona :
				controlador.getListadoPersonas()) {

			System.out.println(persona.saludo());

			System.out.println(persona);

			System.out.println();
		}
	}	
	
	// =====================================
	private void presentarAlumnos() {

		System.out.println();
		System.out.println("LISTADO DE ALUMNOS");
		System.out.println();

		for (modelo_Estudiante alumno :
				controlador.getListadoAlumnos()) {

			System.out.println(alumno.saludo());

			System.out.println(alumno);

			for (modelo_Asignatura asignatura :
					alumno.getAsignaturas()) {

				modelo_AsignaturaCatalogo catalogo =
						controlador
							.getTablaAsignaturasCatalogo()
							.get(asignatura.getId_Asig());

				System.out.println(
					"Asignatura: "
					+ asignatura.getId_Asig()
					+ " - "
					+ catalogo.getNombre());

				System.out.println(
					"Docente: "
					+ asignatura.getId_Profesor());

				double[] notas =
						asignatura.getCalificaciones();

				System.out.println(
					"Notas: "
					+ notas[0]
					+ ", "
					+ notas[1]
					+ ", "
					+ notas[2]);
			}

			System.out.println();
		}
	}	
	
	// ======================================
	private void presentarDocentes() {

		System.out.println();
		System.out.println("LISTADO DE DOCENTES");
		System.out.println();

		for (modelo_Docente docente :
				controlador.getListadoDocentes()) {

			System.out.println(docente.saludo());

			System.out.println(docente);

			System.out.println("Asignaturas impartidas:");

			for (String codigo :
					docente.getAsignaturasImpartidas()) {

				modelo_AsignaturaCatalogo catalogo =
						controlador
							.getTablaAsignaturasCatalogo()
							.get(codigo);

				System.out.println(
					codigo
					+ " - "
					+ catalogo.getNombre());
			}

			System.out.println();
		}
	}	
	
		
	/**
	 * Muestra la información detallada de todos los alumnos.
	 *
	 * La vista solicita el listado completo al controlador
	 * y reutiliza el método encargado de presentar el detalle
	 * individual de un estudiante.
	 *
	 * Esta estrategia evita duplicar código y facilita futuros
	 * cambios en el formato de presentación.
	 */
	private void mostrarInformacionDetalladaAlumnos() {

		System.out.println();
		System.out.println(
				"=================================================");
		System.out.println(
				"INFORMACIÓN DETALLADA DE TODOS LOS ALUMNOS");
		System.out.println(
				"=================================================");

		// La vista solicita al controlador la colección
		// completa de estudiantes.
		for (modelo_Estudiante alumno :
				controlador.getListadoAlumnos()) {

			// Se reutiliza el mismo método empleado
			// por la búsqueda individual de alumnos.
			mostrarDetalleAlumno(alumno);
		}
	}	
	
	
	/**
	 * Solicita un código al usuario y realiza
	 * la búsqueda del alumno correspondiente.
	 */
	private void buscarAlumnoPorCodigo() {

		System.out.println();
		System.out.print(
				"Ingrese el código del alumno: ");

		String codigo =
				vista_ConsolaUtils.teclado
						.nextLine();

		// La vista delega la búsqueda
		// al controlador.
		modelo_Estudiante alumno =
				controlador.buscarAlumnoPorCodigo(
						codigo);

		// Validación simple solicitada
		// por el enunciado.
		if (alumno == null) {

			System.out.println();
			System.out.println(
					"No existe un alumno con código "
							+ codigo);

			return;
		}

		System.out.println();

		mostrarDetalleAlumno(alumno);
	}	
	

	/**
	 * Presenta la ficha completa de un alumno.
	 *
	 * Este método es reutilizado por:
	 *
	 * - Opción 3 (todos los alumnos)
	 * - Opción 4 (un alumno específico)
	 *
	 * De esta forma evitamos duplicar código.
	 */
	private void mostrarDetalleAlumno(
			modelo_Estudiante alumno) {

		System.out.println();
		System.out.println(
				"=========================================================");
		System.out.println(
				"DATOS DEL ALUMNO");
		System.out.println(
				"=========================================================");

		System.out.println(
				"Nombre             : "
						+ alumno.getNombre()
						+ " "
						+ alumno.getApellidos());

		System.out.println(
				"Código             : "
						+ alumno.getId());

		System.out.println(
				"Fecha Nacimiento   : "
						+ String.format(
								"%02d/%02d/%04d",
								alumno.getFechaNac().dia(),
								alumno.getFechaNac().mes(),
								alumno.getFechaNac().anio()));

		System.out.println(
				"Edad               : "
						+ alumno.calcularEdad()
						+ " años");

		System.out.println(
				"Email              : "
						+ alumno.getContacto().email());

		System.out.println(
				"Teléfono           : "
						+ alumno.getContacto().telefono());

		// Se convierte el valor booleano
		// en una salida más legible.
		System.out.println(
				"Usa WhatsApp       : "
						+ (alumno.getContacto().usaWhatsApp()
							? "SI"
							: "NO"));

		System.out.println();

		System.out.println(
				"ASIGNATURAS CURSADAS");

		double sumaPromedios = 0;

		int cantidadAsignaturas = 0;

		// Se recorren todas las asignaturas
		// matriculadas por el estudiante.
		for (modelo_Asignatura asignatura :
				alumno.getAsignaturas()) {

			modelo_AsignaturaCatalogo catalogo =
					controlador
							.getTablaAsignaturasCatalogo()
							.get(
									asignatura.getId_Asig());

			// Recuperamos el docente responsable
			// mediante el controlador.
			modelo_Docente docente =
					controlador.buscarDocentePorCodigo(
							asignatura.getId_Profesor());

			System.out.println();
			System.out.println(
					"------------------------------------------------");

			System.out.println(
					"Asignatura         : "
							+ asignatura.getId_Asig()
							+ " - "
							+ catalogo.getNombre());

			// Integridad referencial:
			// del código almacenado en la asignatura
			// obtenemos el objeto docente completo.
			if (docente != null) {

				System.out.println(
						"Profesor           : ("
								+ docente.getId()
								+ ") "
								+ docente.getNombre()
								+ " "
								+ docente.getApellidos());
			}

			double[] notas =
					asignatura.getCalificaciones();

			System.out.println(
					"Nota 1             : "
							+ notas[0]);

			System.out.println(
					"Nota 2             : "
							+ notas[1]);

			System.out.println(
					"Nota 3             : "
							+ notas[2]);

			// Cálculo del promedio individual.
			double promedioAsignatura =
					(notas[0]
					+ notas[1]
					+ notas[2]) / 3.0;

			System.out.println(
					"Promedio Materia   : "
							+ String.format(
									"%.2f",
									promedioAsignatura));

			// Se acumulan los promedios
			// para el cálculo general.
			sumaPromedios += promedioAsignatura;

			cantidadAsignaturas++;
		}

		double promedioGeneral = 0;

		if (cantidadAsignaturas > 0) {

			// Promedio final del estudiante.
			promedioGeneral =
					sumaPromedios
							/ cantidadAsignaturas;
		}

		System.out.println();
		System.out.println(
				"PROMEDIO GENERAL   : "
						+ String.format(
								"%.2f",
								promedioGeneral));

		System.out.println(
				"=========================================================");
	}
	
}