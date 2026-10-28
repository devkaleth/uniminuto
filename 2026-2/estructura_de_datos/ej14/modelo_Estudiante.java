/*
 * RESPONSABILIDAD:
 * Representar la información de un estudiante.
 *
 * El modelo conoce sus propios datos y
 * proporciona acceso mediante getters.
 */

import java.util.List;

public class modelo_Estudiante extends modelo_Persona {
    // Atributo que almacena una lista de Objetos Asignatura
    private List<modelo_Asignatura> asignaturas;

    // Constructor limpio que recibe directamente el listado de objetos Asignatura
    public modelo_Estudiante(String id, String nombre, String apellidos, RegistroFecha fechaNac, RegistroContacto contacto, List<modelo_Asignatura> asignaturas) {
        super(id, nombre, apellidos, fechaNac, contacto);
        this.asignaturas = asignaturas;
    }

    public double promedioNotas() {
        if (asignaturas == null || asignaturas.isEmpty()) return 0.0;
        double sumaPromedios = 0;
        for (modelo_Asignatura asig : asignaturas) {
            sumaPromedios += asig.promedioNotas();
        }
        return sumaPromedios / asignaturas.size();
    }

    @Override
    public String saludo() {
        return super.saludo() + " Soy un estudiante y mi promedio general es " + String.format("%.2f", promedioNotas()) + ".";
    }

    @Override
    public String toString() {
        return "Estudiante{" + super.toString() + ", asignaturasInscritas=" + (asignaturas != null ? asignaturas.size() : 0) + "}";
    }

    // Getters y Setters
    public List<modelo_Asignatura> getAsignaturas() { return asignaturas; }
    public void setAsignaturas(List<modelo_Asignatura> asignaturas) { this.asignaturas = asignaturas; }
}
