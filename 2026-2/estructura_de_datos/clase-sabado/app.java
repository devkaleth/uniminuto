
package ejercicio_repaso_01;

/**     este es un comentario de varias lineas  (empieza con /* y finaliza con  lo contrario)
 *  ------------------------------
 *  EJERCICIO DE REPASO DE ESTRUCTURAS DE PROGRAMACION**
 *  Aqui realizamos un ejerciciobásico de operaciones matemáticas para repasar el concepto de 
 *  variables y operadores. Entradas, Procesos y Salidas de datos.
 * 
 * @author URIEL CASTAÑEDA SIERRA
 */
public class Ejercicio_Repaso_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     
        // Esta es la Versión #1 con datos asignados directamente a las variables
        int Num1 = 25;
        int Num2 = 30;

        int suma = Num1 + Num2;
        System.out.println("la suma es: " + suma);

        int resta = Num2 - Num1;
        System.out.println("la resta es: " + resta);

        int multiplicacion = Num1 * Num2;
        System.out.println("la multiplicación es: " + multiplicacion);

        // Se usa double para que la división tenga decimales correctos
        double division = (double) Num2 / Num1;
        System.out.println("la división es: " + division);
    }
}