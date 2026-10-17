import java.time.LocalDate;
import java.time.Period;

public class modelo_Persona {
	private String id;
    private String nombre;
    private String apellidos;
    private RegistroFecha fechaNac;
    private RegistroContacto contacto;

    public modelo_Persona(String id, String nombre, String apellidos, RegistroFecha fechaNac, RegistroContacto contacto) {
		this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.contacto = contacto;
    }

    public int calcularEdad() {
        if (this.fechaNac == null) return 0;
        LocalDate fechaNacimiento = LocalDate.of(fechaNac.anio(), fechaNac.mes(), fechaNac.dia());
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }

    public String saludo() {
        return "Hola, mi nombre es " + nombre + " " + apellidos + ".";
    }

    @Override
    public String toString() {
        return "Persona{id='" + id + "' nombre='" + nombre + "', apellidos='" + apellidos + "', edad=" + calcularEdad() + "}";
    }

    // Getters y Setters
    public String getId() { return id; }	
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public RegistroFecha getFechaNac() { return fechaNac; }
    public void setFechaNac(RegistroFecha fechaNac) { this.fechaNac = fechaNac; }
    public RegistroContacto getContacto() { return contacto; }
    public void setContacto(RegistroContacto contacto) { this.contacto = contacto; }
}
