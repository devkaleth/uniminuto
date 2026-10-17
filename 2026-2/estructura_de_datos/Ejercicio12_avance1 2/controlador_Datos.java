/*
 * RESPONSABILIDAD:
 * Mantener y suministrar los datos del sistema.
 *
 * El controlador NO muestra reportes ni menús.
 * Simplemente construye los objetos y los devuelve
 * a quien los solicite
¨*/

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class controlador_Datos {

    private Map<String, modelo_AsignaturaCatalogo> tablaAsignaturasCatalogo;

	private List<modelo_Persona> listadoPersonas;
	private List<modelo_Estudiante> listadoAlumnos;
	private List<modelo_Docente> listadoDocentes;

	private boolean datosConstruidos;



    public controlador_Datos() {

		tablaAsignaturasCatalogo = new LinkedHashMap<>();

		listadoPersonas = new ArrayList<>();
		listadoAlumnos = new ArrayList<>();
		listadoDocentes = new ArrayList<>();

		datosConstruidos = false;

		cargarCatalogo();
    }

    private void cargarCatalogo() {

        tablaAsignaturasCatalogo.put(
                "A-AL",
                new modelo_AsignaturaCatalogo(
                        "A-AL",
                        "Algebra"));

        tablaAsignaturasCatalogo.put(
                "A-SI",
                new modelo_AsignaturaCatalogo(
                        "A-SI",
                        "Sistemas"));

        tablaAsignaturasCatalogo.put(
                "A-FI",
                new modelo_AsignaturaCatalogo(
                        "A-FI",
                        "Física"));

        tablaAsignaturasCatalogo.put(
                "A-IN",
                new modelo_AsignaturaCatalogo(
                        "A-IN",
                        "Investigación"));

        tablaAsignaturasCatalogo.put(
                "A-ED",
                new modelo_AsignaturaCatalogo(
                        "A-ED",
                        "Estructuras de Datos"));

        tablaAsignaturasCatalogo.put(
                "A-PR",
                new modelo_AsignaturaCatalogo(
                        "A-PR",
                        "Programación"));
    }


    public Map<String, modelo_AsignaturaCatalogo> getTablaAsignaturasCatalogo() {

        return tablaAsignaturasCatalogo;
    }
	
	/**
	 * Construye toda la información del ejercicio.
	 *
	 * La bandera datosConstruidos evita crear los mismos
	 * registros múltiples veces.
	 */
	public void construirDatos() {

		if (datosConstruidos) {
			return;
		}

		construirPersonas();
		construirDocentes();
		construirAlumnos();

		datosConstruidos = true;
	}	
	
	// ----------------------------------------------
	private void construirPersonas() {

		listadoPersonas.add(
			new modelo_Persona(
				"P001",
				"Andres",
				"Mendoza",
				new RegistroFecha((byte)12, (byte)4, (short)1985),
				new RegistroContacto(
					"andres.mendoza@abc.com",
					"3105551001",
					true)));

		listadoPersonas.add(
			new modelo_Persona(
				"P002",
				"Beatriz",
				"Ramirez",
				new RegistroFecha((byte)23, (byte)9, (short)1990),
				new RegistroContacto(
					"beatriz.ramirez@abc.com",
					"3105551002",
					false)));

		listadoPersonas.add(
			new modelo_Persona(
				"P003",
				"Carlos",
				"Vargas",
				new RegistroFecha((byte)7, (byte)2, (short)1988),
				new RegistroContacto(
					"carlos.vargas@abc.com",
					"3105551003",
					true)));
	}	
	
	//------------------------------------------
	private void construirDocentes() {

		listadoDocentes.add(
			new modelo_Docente(
				"D001",
				"Uriel",
				"Castaneda",
				new RegistroFecha((byte)15, (byte)6, (short)1980),
				new RegistroContacto(
					"uriel@universidad.edu.co",
					"3105552001",
					true),
				new ArrayList<>(List.of(
					"A-SI",
					"A-AL"
				))
			));

		listadoDocentes.add(
			new modelo_Docente(
				"D002",
				"Gloria",
				"Martinez",
				new RegistroFecha((byte)9, (byte)11, (short)1982),
				new RegistroContacto(
					"gloria@universidad.edu.co",
					"3105552002",
					false),
				new ArrayList<>(List.of(
					"A-AL",
					"A-IN"
				))
			));

		listadoDocentes.add(
			new modelo_Docente(
				"D003",
				"Jorge",
				"Rodriguez",
				new RegistroFecha((byte)28, (byte)2, (short)1978),
				new RegistroContacto(
					"jorge@universidad.edu.co",
					"3105552003",
					true),
				new ArrayList<>(List.of(
					"A-FI",
					"A-ED"
				))
			));
	}	
	
	//---------------------------------------
	private void construirAlumnos() {

		List<modelo_Asignatura> materiasE001 = new ArrayList<>();

		materiasE001.add(
			new modelo_Asignatura(
				"A-SI",
				new double[]{3.5, 2.0, 4.5},
				"D001"));

		materiasE001.add(
			new modelo_Asignatura(
				"A-AL",
				new double[]{4.5, 2.5, 3.5},
				"D002"));

		listadoAlumnos.add(
			new modelo_Estudiante(
				"E001",
				"Fredy",
				"Gomez",
				new RegistroFecha((byte)14, (byte)3, (short)2005),
				new RegistroContacto(
					"email1@abc.com",
					"31223344",
					true),
				materiasE001));



		List<modelo_Asignatura> materiasE002 = new ArrayList<>();

		materiasE002.add(
			new modelo_Asignatura(
				"A-AL",
				new double[]{2.5, 3.8, 4.1},
				"D002"));

		materiasE002.add(
			new modelo_Asignatura(
				"A-FI",
				new double[]{5.0, 2.0, 1.0},
				"D003"));

		listadoAlumnos.add(
			new modelo_Estudiante(
				"E002",
				"Carlos2",
				"Perez",
				new RegistroFecha((byte)22, (byte)8, (short)2006),
				new RegistroContacto(
					"email2@xyz.com",
					"31666344",
					false),
				materiasE002));



		List<modelo_Asignatura> materiasE003 = new ArrayList<>();

		materiasE003.add(
			new modelo_Asignatura(
				"A-IN",
				new double[]{3.5, 4.0, 2.9},
				"D002"));

		materiasE003.add(
			new modelo_Asignatura(
				"A-ED",
				new double[]{3.8, 4.2, 4.0},
				"D003"));

		listadoAlumnos.add(
			new modelo_Estudiante(
				"E003",
				"Maria",
				"Lopez",
				new RegistroFecha((byte)5, (byte)12, (short)2005),
				new RegistroContacto(
					"email3@ijk.com",
					"33555443",
					true),
				materiasE003));
	}	
	
	// --------------------------------------------- GETTERS ------
	public List<modelo_Persona> getListadoPersonas() {
		return listadoPersonas;
	}



	/*
	 * Entrega el listado completo de estudiantes.
	 *
	 * La vista utilizará esta información para
	 * construir el reporte detallado.
	 */
	public List<modelo_Estudiante> getListadoAlumnos() {
		return listadoAlumnos;
	}


	public List<modelo_Docente> getListadoDocentes() {
		return listadoDocentes;
	}
		
			
	/**
	 * Busca un docente utilizando su identificador.
	 *
	 * La vista necesita conocer los datos del profesor
	 * que imparte cada asignatura, pero la búsqueda
	 * debe realizarse en el controlador para respetar MVC.
	 *
	 * @param codigoDocente Código del docente buscado.
	 * @return Objeto docente encontrado o null.
	 */
	public modelo_Docente buscarDocentePorCodigo(
			String codigoDocente) {

		// Recorremos el listado completo de docentes.
		for (modelo_Docente docente : listadoDocentes) {

			// Se compara el código recibido
			// con el código almacenado en el objeto.
			if (docente.getId()
					.equals(codigoDocente)) {

				return docente;
			}
		}

		// Si no existe coincidencia se retorna null.
		return null;
	}		
		
	/**
	 * Busca un estudiante por su código.
	 *
	 * La responsabilidad de localizar objetos
	 * corresponde al controlador.
	 *
	 * @param codigo Código del alumno.
	 * @return Alumno encontrado o null.
	 */
	public modelo_Estudiante buscarAlumnoPorCodigo(
			String codigo) {

		// Se recorre el listado de alumnos.
		for (modelo_Estudiante alumno : listadoAlumnos) {

			// Se compara el código recibido
			// con el identificador del estudiante.
			if (alumno.getId()
					.equalsIgnoreCase(codigo)) {

				return alumno;
			}
		}

		// Si no existe coincidencia,
		// se retorna null.
		return null;
	}


	
}