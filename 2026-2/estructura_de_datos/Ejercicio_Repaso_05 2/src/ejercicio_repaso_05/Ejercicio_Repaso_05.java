

package ejercicio_repaso_05;

/**
 *
 * @author RGH-LS24-21
 */
public class Ejercicio_Repaso_05 {


    // Definición del Registro (Record) 
    public record RegistroAlumno(
            String nombre,
            String lasname,
            registerFecha fechaNac,
            double[] calificaciones) {}    
    public record registerFecha (
            byte dia,
            byte mes,
            short anio
    ){}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Inicialización del conjunto de datos usando el Record
        RegistroAlumno[] ListadoAlumnos = {
            new RegistroAlumno("Fredy","a" ,  new registerFecha((byte)8 , (byte)12 , (short)2028),new double[]{3.5, 2.0, 4.5}),
            new RegistroAlumno("Carlos", "b",new registerFecha((byte)1 , (byte)12 , (short)2021),new double[]{2.9, 3.3, 4.5}),
            new RegistroAlumno("Maria", "c" ,new registerFecha((byte)7 , (byte)12 , (short)2021),new double[]{1.0, 2.7, 5.0}),
            new RegistroAlumno("Camilo", "d" ,new registerFecha((byte)6 , (byte)12 , (byte)2006),new double[]{3.3, 2.2, 4.4}),
            new RegistroAlumno("Bertha","e", new registerFecha((byte)6 , (byte)12 , (byte)2006),new double[]{1.0, 2.0, 3.0})
        };        
        
  // Bucle for-each para recorrer e imprimir el listado
        for (RegistroAlumno alumno : ListadoAlumnos) {
            double suma = 0;
            
            // Los records generan automáticamente métodos de acceso con el mismo nombre del componente
            System.out.println(alumno.nombre()); 
            System.out.println("-------------------------");
            
            for (int j = 0; j < alumno.calificaciones().length; j++) {
                System.out.println(alumno.calificaciones()[j]);
                suma += alumno.calificaciones()[j];
            }
            
            double promedio = suma / alumno.calificaciones().length;
            System.out.printf("el promedio es: %.2f%n", promedio);            
            System.out.println("-------------------------");
        }           
    }
    
}
