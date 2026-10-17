import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// --- Controlador de Datos ---
public class controlador_Datos {
    
    private Map<String, modelo_AsignaturaCatalogo> tablaAsignaturasCatalogo;
    private List<modelo_Persona> listadoPersonas;
    private List<modelo_Estudiante> listadoAlumnos;
    private List<modelo_Docente> listadoDocentes;
    private boolean listadosConstruidos = false;

    public controlador_Datos() {
        tablaAsignaturasCatalogo = new HashMap<>();
        cargarCatalogoInicial();
    }

    private void cargarCatalogoInicial() {
        tablaAsignaturasCatalogo.put("A-AL", new modelo_AsignaturaCatalogo("A-AL", "Algebra"));
        tablaAsignaturasCatalogo.put("A-SI", new modelo_AsignaturaCatalogo("A-SI", "Sistemas"));
        tablaAsignaturasCatalogo.put("A-FI", new modelo_AsignaturaCatalogo("A-FI", "Física"));
        tablaAsignaturasCatalogo.put("A-IN", new modelo_AsignaturaCatalogo("A-IN", "Investigación"));
        tablaAsignaturasCatalogo.put("A-ED", new modelo_AsignaturaCatalogo("A-ED", "Estructuras de Datos"));
        tablaAsignaturasCatalogo.put("A-PR", new modelo_AsignaturaCatalogo("A-PR", "Programación"));
    }

    public void construirListados() {
        if (listadosConstruidos) return;

        listadoPersonas = new ArrayList<>();
        listadoAlumnos = new ArrayList<>();
        listadoDocentes = new ArrayList<>();

        listadoDocentes.add(new modelo_Docente("D001", "Albert", "Einstein", new RegistroFecha((byte)14, (byte)3, (short)1879), new RegistroContacto("a@uni.edu", "111", true), new ArrayList<>(List.of("A-FI", "A-AL"))));
        listadoDocentes.add(new modelo_Docente("D002", "Ada", "Lovelace", new RegistroFecha((byte)10, (byte)12, (short)1815), new RegistroContacto("ada@uni.edu", "222", true), new ArrayList<>(List.of("A-PR", "A-ED"))));
        listadoDocentes.add(new modelo_Docente("D003", "Alan", "Turing", new RegistroFecha((byte)23, (byte)6, (short)1912), new RegistroContacto("alan@uni.edu", "333", false), new ArrayList<>(List.of("A-SI", "A-IN"))));

        listadoAlumnos.add(crearAlumno("E001", "Juan", "Perez", "A-FI", "D001", "A-PR", "D002"));
        listadoAlumnos.add(crearAlumno("E002", "Maria", "Gomez", "A-AL", "D001", "A-ED", "D002"));
        listadoAlumnos.add(crearAlumno("E003", "Luis", "Ramirez", "A-SI", "D003", "A-IN", "D003"));
        listadoAlumnos.add(crearAlumno("E004", "Ana", "Torres", "A-FI", "D001", "A-IN", "D003"));
        listadoAlumnos.add(crearAlumno("E005", "Pedro", "Ruiz", "A-PR", "D002", "A-AL", "D001"));

        listadoPersonas.add(new modelo_Persona("P001", "Carlos", "Admin", new RegistroFecha((byte)1, (byte)1, (short)1980), new RegistroContacto("c@uni.edu", "444", true)));
        listadoPersonas.add(new modelo_Persona("P002", "Laura", "Secretaria", new RegistroFecha((byte)2, (byte)2, (short)1985), new RegistroContacto("l@uni.edu", "555", false)));
        listadoPersonas.add(new modelo_Persona("P003", "Jose", "Rector", new RegistroFecha((byte)3, (byte)3, (short)1970), new RegistroContacto("j@uni.edu", "666", true)));

        listadoPersonas.addAll(listadoDocentes);
        listadoPersonas.addAll(listadoAlumnos);

        listadosConstruidos = true;
    }

    private modelo_Estudiante crearAlumno(String id, String nom, String ape, String asig1, String prof1, String asig2, String prof2) {
        List<modelo_Asignatura> asigs = new ArrayList<>();
        asigs.add(new modelo_Asignatura(asig1, new double[]{4.0, 4.5, 3.8}, prof1));
        asigs.add(new modelo_Asignatura(asig2, new double[]{3.5, 4.0, 4.2}, prof2));
        return new modelo_Estudiante(id, nom, ape, new RegistroFecha((byte)1, (byte)1, (short)2000), new RegistroContacto(nom.toLowerCase()+"@uni.edu", "000", true), asigs);
    }

    public modelo_Docente buscarDocentePorId(String idProfesor) {
        if (listadoDocentes == null) return null;
        for (modelo_Docente docente : listadoDocentes) {
            if (docente.getId().equalsIgnoreCase(idProfesor)) { return docente; }
        }
        return null;
    }

    public modelo_Estudiante buscarAlumnoPorId(String idAlumno) {
        if (listadoAlumnos == null) return null;
        for (modelo_Estudiante alumno : listadoAlumnos) {
            if (alumno.getId().equalsIgnoreCase(idAlumno)) { return alumno; }
        }
        return null;
    }

    // *DIDÁCTICO (Prompt 6)*: Ordenar usando List.sort() y Referencias a Métodos
    public void ordenarPersonasPorNombre() {
        listadoPersonas.sort(Comparator.comparing(modelo_Persona::getNombre));
    }

    public void ordenarPersonasPorApellidos() {
        listadoPersonas.sort(Comparator.comparing(modelo_Persona::getApellidos));
    }

    // *DIDÁCTICO (Prompt 7)*: Búsqueda por nombre ignorando mayúsculas/minúsculas
    public List<modelo_Persona> buscarPersonasPorNombre(String textoBusqueda) {
        List<modelo_Persona> resultados = new ArrayList<>();
        if (listadoPersonas == null) return resultados;
        
        String textoLower = textoBusqueda.toLowerCase();
        for (modelo_Persona p : listadoPersonas) {
            if (p.getNombre().toLowerCase().contains(textoLower)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    // *DIDÁCTICO (Prompt 10)*: Auditoría de Integridad Referencial
    // Verifica que los códigos foráneos (docente y asignatura) existan en las listas oficiales.
    public List<String> auditarIntegridad() {
        List<String> reporte = new ArrayList<>();
        
        for (modelo_Estudiante alumno : listadoAlumnos) {
            for (modelo_Asignatura asig : alumno.getAsignaturas()) {
                if (!tablaAsignaturasCatalogo.containsKey(asig.getId_Asig())) {
                    reporte.add("[ERROR ALUMNO] " + alumno.getId() + " cursa asignatura inexistente: " + asig.getId_Asig());
                }
                if (buscarDocentePorId(asig.getId_Profesor()) == null) {
                    reporte.add("[ERROR ALUMNO] " + alumno.getId() + " tiene profesor inexistente: " + asig.getId_Profesor());
                }
            }
        }
        
        for (modelo_Docente docente : listadoDocentes) {
            for (String codAsig : docente.getAsignaturasImpartidas()) {
                if (!tablaAsignaturasCatalogo.containsKey(codAsig)) {
                    reporte.add("[ERROR DOCENTE] " + docente.getId() + " imparte asignatura inexistente: " + codAsig);
                }
            }
        }
        return reporte;
    }

    // Método para inyectar datos inválidos temporalmente y probar la auditoría
    public void inyectarErrorTemporal() {
        listadoAlumnos.get(0).getAsignaturas().add(new modelo_Asignatura("A-FAKE", new double[]{0,0,0}, "D-FAKE"));
    }

    // Método para remover el error inyectado
    public void removerErrorTemporal() {
        List<modelo_Asignatura> asigs = listadoAlumnos.get(0).getAsignaturas();
        asigs.removeIf(a -> a.getId_Asig().equals("A-FAKE"));
    }

    public Map<String, modelo_AsignaturaCatalogo> getTablaAsignaturasCatalogo() { return tablaAsignaturasCatalogo; }
    public List<modelo_Persona> getListadoPersonas() { return listadoPersonas; }
    public List<modelo_Estudiante> getListadoAlumnos() { return listadoAlumnos; }
    public List<modelo_Docente> getListadoDocentes() { return listadoDocentes; }
}