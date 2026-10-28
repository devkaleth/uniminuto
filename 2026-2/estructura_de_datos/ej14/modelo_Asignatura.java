import java.util.Arrays;

public class modelo_Asignatura {
    private String id_Asig;
    private double[] calificaciones;
    private String id_Profesor;

    public modelo_Asignatura(String id_Asig, double[] calificaciones, String id_Profesor) {
        this.id_Asig = id_Asig;
        this.calificaciones = calificaciones;
        this.id_Profesor = id_Profesor;
    }

    public double promedioNotas() {
        if (calificaciones == null || calificaciones.length == 0) return 0.0;
        double suma = 0;
        for (double nota : calificaciones) {
            suma += nota;
        }
        return suma / calificaciones.length;
    }

    @Override
    public String toString() {
        return "Asignatura{Codigo='" + id_Asig + "', promedio=" + promedioNotas() + ", profesor='" + id_Profesor + "'}";
    }

    // Getters y Setters
    public String getId_Asig() { return id_Asig; }
    public void setId_Asig(String id_Asig) { this.id_Asig = id_Asig; }
    public double[] getCalificaciones() { return calificaciones; }
    public void setCalificaciones(double[] calificaciones) { this.calificaciones = calificaciones; }
    public String getId_Profesor() { return id_Profesor; }
    public void setProfesor(String id_Profesor) { this.id_Profesor = id_Profesor; }
}
