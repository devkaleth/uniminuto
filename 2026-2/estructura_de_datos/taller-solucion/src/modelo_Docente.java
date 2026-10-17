import java.util.List;

public class modelo_Docente extends modelo_Persona {
    private List<String> asignaturasImpartidas;

    public modelo_Docente(String id, String nombre, String apellidos, RegistroFecha fechaNac, RegistroContacto contacto, List<String> asignaturasImpartidas) {
        super(id, nombre, apellidos, fechaNac, contacto);
        this.asignaturasImpartidas = asignaturasImpartidas;
    }

    @Override
    public String saludo() {
        return super.saludo() + " Soy docente e imparto las asignaturas: " + String.join(", ", asignaturasImpartidas) + ".";
    }

    @Override
    public String toString() {
        return "Docente{" + super.toString() + ", asignaturasImpartidas=" + asignaturasImpartidas + "}";
    }

    // Getters y Setters
    public List<String> getAsignaturasImpartidas() { return asignaturasImpartidas; }
    public void setAsignaturasImpartidas(List<String> asignaturasImpartidas) { this.asignaturasImpartidas = asignaturasImpartidas; }
}
