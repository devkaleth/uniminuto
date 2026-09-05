import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

class ConsolUtil {
    public static final Scanner  teclado = new Scanner(System.in);
    public static void pausar (String mensaje) {
        System.out.println(mensaje);
        System.out.println("Precione Enter para continuar");
        teclado.nextLine();
    }
    public static void borrarPantalla(){
        System.out.print("\033[H\033[2j");
        System.out.flush();
    }
}

public class Ejercicio8 {
    // --- DEFINICIÓN DE LOS RECORDS ---
    public record RegistroEstudiante(
            String nombre,
            String apellidos,
            double[] calificaciones,
            RegistroFecha fechaNac
    ) {}


    public record RegistroFecha(byte dia, byte mes, short anio) {}


    // --- VARIABLES GLOBALES (ESTÁTICAS) ---
    // El arreglo inicia "vacío" con capacidad para un máximo de 50 alumnos
    public static RegistroEstudiante[] ListadoAlumnos = new RegistroEstudiante[50];
    public static int contadorAlumnos = 0;
    // Escáner global para leer datos del teclado
    //public static Scanner teclado = new Scanner(System.in);


    // ---MÉTODO PRINCIPAL (MAIN) ---
    public static void main(String[] args) {
        int opcion;
        do {
            // 1. La función pinta el menú y retorna la opción elegida
            opcion = pintarMenu();
            // 2. El flujo pasa la opción a la función encargada de gestionarla
            GestionarOpcionesMenu(opcion);
        } while (opcion != 9); // Se repite hasta que el usuario decida terminar
    }


    // --- 1. FUNCIÓN PARA PINTAR EL MENÚ ---
    public static int pintarMenu() {
        ConsolUtil.borrarPantalla();
        System.out.println("\n====================================");
        System.out.println("   Menu Sistema Calificaciones   ");
        System.out.println("====================================");
        System.out.println("1 - Cargar datos de prueba");
        System.out.println("2 - Mostrar listado de alumnos");
        System.out.println("3 - Insertar un alumno desde teclado");
        System.out.println("4 - busca a un estudiante");
        System.out.println("9 - Terminar");
        System.out.print("Elija una opcion: ");
        int opcionElegida = ConsolUtil.teclado.nextInt();
        ConsolUtil.teclado.nextLine(); // Limpiar el buffer del escáner
        return opcionElegida;
    }


    // --- 2. FUNCIÓN PARA CONTROLAR EL FLUJO (SWITCH) ---
    public static void GestionarOpcionesMenu(int opcionMenu) {
        switch (opcionMenu) {
            case 1:
                cargarDatosPrueba();
                ConsolUtil.pausar("proceso #1 finalizado");
                break;
            case 2:
                mostrarListadoAlumnos();
                ConsolUtil.pausar("proceso #2 finalizado");
                break;
            case 3:
                insertarAlumnoTeclado();
                ConsolUtil.pausar("proceso #3 finalizado");
                break;
            case 4 :
                strudenBy();
                ConsolUtil.pausar("proceso #4 finalizado");
                break;
            case 9:
                System.out.println("Saliendo del sistema. ¡Hasta luego!");
                ConsolUtil.pausar("proceso #9 finalizado");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
    // funcion para mostrar los datos de un solo estudiante
    public static void strudenBy (){
        System.out.print("Ingrese el indece del estudiante : ");
        int by = ConsolUtil.teclado.nextInt();
        if (contadorAlumnos == 0 ){
            System.out.println("No ay datos en el sistema \n Cargue datos primero");
            return;
        }
        if(by < ListadoAlumnos.length){
            System.out.println("el estudiante no existe");
            return;
        }
        double suma = 0;
        RegistroEstudiante alumno = ListadoAlumnos[by];
        System.out.println("Estudiante: (" + by + ")" + alumno.nombre() + " " + alumno.apellidos());
        System.out.printf("Fecha de Nacimiento: %02d/%02d/%04d\n",
                alumno.fechaNac().dia(), alumno.fechaNac().mes(), alumno.fechaNac().anio()
        );
        System.out.println("Ayer : " + (alumno.fechaNac)); // nuevo  print year studens
        System.out.println("------------------------------------");
        System.out.print("Notas: ");
        for (int j = 0; j < alumno.calificaciones().length; j++) {
            System.out.print(alumno.calificaciones()[j] + "  ");
            suma += alumno.calificaciones()[j];
        }
        System.out.println();
        double promedio = suma / alumno.calificaciones().length;
        System.out.printf("El promedio es: %.2f%n", promedio);
        System.out.println("------------------------------------");
    }

    // --- MÓDULO: CARGAR DATOS DE PRUEBA (OPCIÓN 1) ---
    public static void cargarDatosPrueba() {
        // Evitamos duplicar datos si ya se habían cargado antes
        if (contadorAlumnos > 0) {
            System.out.println("Ya existen datos en el sistema. No se cargaron los de prueba.");
            return;
        }
        ListadoAlumnos[0] = new RegistroEstudiante("Fredy", "Gomez", new double[]{3.5, 2.0, 4.5}, new RegistroFecha((byte)14, (byte)3, (short)2005));
        ListadoAlumnos[1] = new RegistroEstudiante("Carlos", "Perez", new double[]{2.9, 3.3, 4.5}, new RegistroFecha((byte)22, (byte)8, (short)2006));
        ListadoAlumnos[2] = new RegistroEstudiante("Maria", "Lopez", new double[]{1.0, 2.7, 5.0}, new RegistroFecha((byte)5, (byte)12, (short)2005));
        ListadoAlumnos[3] = new RegistroEstudiante("Camilo", "Rojas", new double[]{3.3, 2.2, 4.4}, new RegistroFecha((byte)30, (byte)1, (short)2006));
        ListadoAlumnos[4] = new RegistroEstudiante("Bertha", "Diaz", new double[]{1.0, 2.0, 3.0}, new RegistroFecha((byte)18, (byte)7, (short)2005));
        contadorAlumnos = 5; // Ahora tenemos 5 alumnos registrados
        System.out.println("¡Datos de prueba cargados con éxito! (5 alumnos añadidos).");
    }


    // --- MÓDULO: MOSTRAR LISTADO (OPCIÓN 2) ---
    public static void mostrarListadoAlumnos() {
        if (contadorAlumnos == 0) {
            System.out.println("El listado está vacío. No hay alumnos para mostrar.");
            return;
        }


        System.out.println("\n====== LISTADO DE ESTUDIANTES ======");
        // Recorremos solo las posiciones ocupadas usando el contadorAlumnos
        for (int i = 0; i < contadorAlumnos; i++) {
            RegistroEstudiante alumno = ListadoAlumnos[i];
            double suma = 0;
            System.out.println("Estudiante: " + alumno.nombre() + " " + alumno.apellidos() + "(" + (i + 1) + ")");
            System.out.printf("Fecha de Nacimiento: %02d/%02d/%04d\n",
                    alumno.fechaNac().dia(), alumno.fechaNac().mes(), alumno.fechaNac().anio()
            );
            System.out.println("Ayer : " + calcularEdad(alumno.fechaNac)); // nuevo  print year studens
            System.out.println("------------------------------------");
            System.out.print("Notas: ");
            for (int j = 0; j < alumno.calificaciones().length; j++) {
                System.out.print(alumno.calificaciones()[j] + "  ");
                suma += alumno.calificaciones()[j];
            }
            System.out.println();
            double promedio = suma / alumno.calificaciones().length;
            System.out.printf("El promedio es: %.2f%n", promedio);
            System.out.println("------------------------------------");
        }
    }


    // --- MÓDULO: INSERTAR DESDE TECLADO (OPCIÓN 3) ---
    public static void insertarAlumnoTeclado() {
        // Validar si el arreglo ya está lleno
        if (contadorAlumnos >= ListadoAlumnos.length) {
            System.out.println("Error: Memoria llena. No se pueden registrar más alumnos.");
            return;
        }


        System.out.println("\n--- REGISTRO DE NUEVO ALUMNO ---");
        System.out.print("Nombre: ");
        String nombre = ConsolUtil.teclado.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = ConsolUtil.teclado.nextLine();
        // Captura de la fecha de nacimiento
        System.out.print("Día de nacimiento (dd): ");
        byte dia = ConsolUtil.teclado.nextByte();
        System.out.print("Mes de nacimiento (mm): ");
        byte mes = ConsolUtil.teclado.nextByte();
        System.out.print("Año de nacimiento (aaaa): ");
        short anio = ConsolUtil.teclado.nextShort();
        // Captura de las 3 calificaciones obligatorias
        double[] calificaciones = new double[3];
        System.out.println("Ingrese las 3 calificaciones:");
        for (int j = 0; j < calificaciones.length; j++) {
            System.out.print("Nota " + (j + 1) + ": ");
            calificaciones[j] = ConsolUtil.teclado.nextDouble();
        }
        ConsolUtil.teclado.nextLine(); // Limpiar residuo numérico del buffer antes de salir


        // Crear el nuevo objeto Record y guardarlo en la posición actual del contador
        ListadoAlumnos[contadorAlumnos] = new RegistroEstudiante(
                nombre,
                apellidos,
                calificaciones,
                new RegistroFecha(dia, mes, anio)
        );
        // Incrementar el contador para el próximo alumno
        contadorAlumnos++;
        System.out.println("¡Alumno registrado con éxito!");
    }
    public static int calcularEdad(RegistroFecha fechaNac) {
        LocalDate fechaNacimiento = LocalDate.of(fechaNac.anio(), fechaNac.mes(), fechaNac.dia());
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
}
