public class Ejercicio12 {

    public static void main(String[] args) {

        controlador_Datos controlador = new controlador_Datos();

        vista_Principal vista = new vista_Principal(controlador);

        vista.iniciar();
    }
}