
public class modelo_AsignaturaCatalogo {

    // Atributos de la asignatura
    private String codigo;
    private String nombre;

    // Constructor
    public modelo_AsignaturaCatalogo(
            String codigo, String nombre) {

        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Representación textual del objeto
    @Override
    public String toString() {
        return "AsignaturaCatalogo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
