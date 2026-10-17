import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// --- Vista Principal ---
public class vista_Principal {
    
    private controlador_Datos controlador;

    public vista_Principal(controlador_Datos controlador) {
        this.controlador = controlador;
    }

    public void iniciarMenu() {
        int opcion = -1;
        do {
            vista_ConsolaUtils.borrarPantalla();
            System.out.println("=== SISTEMA DE CALIFICACIONES - EJERCICIO 12 ===");
            System.out.println("1. Mostrar catálogo");
            System.out.println("2. Construir y mostrar listados base");
            System.out.println("3. Detalle de todos los alumnos");
            System.out.println("4. Buscar alumno por código");
            System.out.println("5. Listar y consultar docentes");
            System.out.println("6. [Lab] Ordenar listado Personas (Prompt 6)");
            System.out.println("7. [Lab] Buscar persona por nombre (Prompt 7)");
            System.out.println("8. [Lab] Operaciones List (Prompt 8)");
            System.out.println("9. [Lab] Operaciones Map (Prompt 9)");
            System.out.println("10. [Lab] Auditoría de Integridad (Prompt 10)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(vista_ConsolaUtils.teclado.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                vista_ConsolaUtils.pausar("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: mostrarCatalogo(); break;
            case 2: construirYMostrarListados(); break;
            case 3: mostrarDetalleTodosAlumnos(); break;
            case 4: buscarAlumnoPorCodigo(); break;
            case 5: gestionarDocentes(); break;
            case 6: ordenarPersonas(); break;
            case 7: buscarPersonaPorNombre(); break;
            case 8: laboratorioList(); break;
            case 9: laboratorioMap(); break;
            case 10: auditarIntegridad(); break;
            case 0: System.out.println("Saliendo..."); break;
            default: vista_ConsolaUtils.pausar("Opción no válida.");
        }
    }

    // --- MÉTODOS DEL CAPÍTULO 2 AL 4 (Se mantienen iguales) ---
    private void mostrarCatalogo() {
        vista_ConsolaUtils.borrarPantalla();
        Map<String, modelo_AsignaturaCatalogo> catalogo = controlador.getTablaAsignaturasCatalogo();
        for (Map.Entry<String, modelo_AsignaturaCatalogo> entry : catalogo.entrySet()) {
            System.out.println("Código: " + entry.getValue().getCodigo() + " | Nombre: " + entry.getValue().getNombre());
        }
        vista_ConsolaUtils.pausar(""); 
    }

    private void construirYMostrarListados() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        System.out.println("--- LISTADOS CONSTRUIDOS ---");
        vista_ConsolaUtils.pausar("");
    }

    private void mostrarDetalleTodosAlumnos() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        for (modelo_Estudiante alumno : controlador.getListadoAlumnos()) {
            System.out.println("ID: " + alumno.getId() + " | Nombre: " + alumno.getNombre());
        }
        vista_ConsolaUtils.pausar("");
    }

    private void buscarAlumnoPorCodigo() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        System.out.print("Ingrese código alumno: ");
        String cod = vista_ConsolaUtils.teclado.nextLine();
        modelo_Estudiante al = controlador.buscarAlumnoPorId(cod);
        System.out.println(al != null ? "Encontrado: " + al.getNombre() : "No existe.");
        vista_ConsolaUtils.pausar("");
    }

    private void gestionarDocentes() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        for (modelo_Docente doc : controlador.getListadoDocentes()) {
            System.out.println("ID: " + doc.getId() + " | Nombre: " + doc.getNombre());
        }
        vista_ConsolaUtils.pausar("");
    }

    // --- CAPÍTULO 5: LABORATORIO DE ESTRUCTURAS ---

    // *DIDÁCTICO (Prompt 6)*: Ordenamientos
    private void ordenarPersonas() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        System.out.println("1. Ordenar por Nombre\n2. Ordenar por Apellido");
        String op = vista_ConsolaUtils.teclado.nextLine();

        if (op.equals("1")) controlador.ordenarPersonasPorNombre();
        else if (op.equals("2")) controlador.ordenarPersonasPorApellidos();

        System.out.println("\n--- LISTADO ORDENADO ---");
        for (modelo_Persona p : controlador.getListadoPersonas()) {
            System.out.println(p.getId() + " - " + p.getNombre() + " " + p.getApellidos());
        }

        System.out.println("\n[SUSTENTACIÓN]: ¿Por qué no se ordenan listadoAlumnos ni listadoDocentes?");
        System.out.println("R/ Porque 'sort' modifica únicamente el orden de las referencias dentro de la lista 'listadoPersonas'. Las otras listas mantienen su propio orden original.");
        System.out.println("\n[SUSTENTACIÓN]: ¿Qué es una expresión Lambda o Referencia a Método?");
        System.out.println("R/ 'modelo_Persona::getNombre' es una referencia a método. Le dice a Java que use la función getNombre() para comparar y ordenar los objetos internamente de forma más limpia.");
        vista_ConsolaUtils.pausar("");
    }

    // *DIDÁCTICO (Prompt 7)*: Búsqueda por Nombre
    private void buscarPersonaPorNombre() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        
        System.out.print("Ingrese el nombre a buscar (ej. 'albert' o 'maria'): ");
        String busqueda = vista_ConsolaUtils.teclado.nextLine();

        List<modelo_Persona> resultados = controlador.buscarPersonasPorNombre(busqueda);
        
        System.out.println("\nSe encontraron " + resultados.size() + " coincidencias:");
        for (modelo_Persona p : resultados) {
            System.out.println("Tipo: " + p.getClass().getSimpleName() + " | ID: " + p.getId());
            System.out.println(p.saludo());
            System.out.println(p.toString() + "\n");
        }
        vista_ConsolaUtils.pausar("");
    }

    // *DIDÁCTICO (Prompt 8)*: Operaciones List (Trabajamos sobre una copia para no dañar el original)
    private void laboratorioList() {
        vista_ConsolaUtils.borrarPantalla();
        List<String> listaPrueba = new ArrayList<>(List.of("A-AL", "A-SI", "A-FI"));
        
        System.out.println("Lista inicial tamaño " + listaPrueba.size() + ": " + listaPrueba);

        System.out.println("\n1. Insertar con add(índice, objeto) -> Insertaremos 'NUEVO' en la posición 1");
        listaPrueba.add(1, "NUEVO"); // Desplaza los elementos a la derecha
        System.out.println("Resultado: " + listaPrueba);

        System.out.println("\n2. Reemplazar con set(índice, objeto) -> Reemplazaremos la posición 1 por 'REEMPLAZO'");
        listaPrueba.set(1, "REEMPLAZO"); // Sobrescribe el elemento
        System.out.println("Resultado: " + listaPrueba);

        System.out.println("\n3. Eliminar con remove(índice) -> Borramos el índice 0");
        listaPrueba.remove(0); // Borra y desplaza a la izquierda
        System.out.println("Resultado: " + listaPrueba);

        System.out.println("\n4. Eliminar por condición con removeIf() -> Borramos todo lo que contenga 'FI'");
        listaPrueba.removeIf(texto -> texto.contains("FI"));
        System.out.println("Resultado final tamaño " + listaPrueba.size() + ": " + listaPrueba);

        System.out.println("\n[SUSTENTACIÓN]: ¿Diferencia entre Insertar y Reemplazar?");
        System.out.println("R/ 'add(index)' hace espacio, desplazando los elementos hacia la derecha (cambian sus índices). 'set(index)' aplasta el dato existente sin mover a nadie.");
        vista_ConsolaUtils.pausar("");
    }

    // *DIDÁCTICO (Prompt 9)*: Operaciones Map (Trabajamos sobre un Map temporal)
    private void laboratorioMap() {
        vista_ConsolaUtils.borrarPantalla();
        Map<String, String> mapaPrueba = new HashMap<>();
        mapaPrueba.put("K1", "Valor 1");
        mapaPrueba.put("K2", "Valor 2");

        System.out.println("1. Recorrer con entrySet():");
        for (Map.Entry<String, String> entry : mapaPrueba.entrySet()) {
            System.out.println(" Llave: " + entry.getKey() + " -> Valor: " + entry.getValue());
        }

        System.out.println("\n2. containsKey() -> ¿Existe 'K1'? " + mapaPrueba.containsKey("K1"));
        System.out.println("3. get() -> Recuperar 'K2': " + mapaPrueba.get("K2"));

        System.out.println("\n4. put() con llave nueva vs llave existente:");
        mapaPrueba.put("K3", "Valor NUEVO"); // Inserta porque K3 no existe
        mapaPrueba.put("K1", "Valor ACTUALIZADO"); // Actualiza porque K1 ya existe
        System.out.println("Mapa después de put: " + mapaPrueba);

        System.out.println("\n5. remove() -> Borrando 'K2'");
        mapaPrueba.remove("K2");
        System.out.println("Mapa final: " + mapaPrueba);

        System.out.println("\n[SUSTENTACIÓN]: ¿Qué ocurre si hacemos put() de una llave existente?");
        System.out.println("R/ El Map no admite llaves duplicadas. Si usas put() con una llave que ya existe, simplemente reemplaza el valor anterior por el nuevo.");
        vista_ConsolaUtils.pausar("");
    }

    // *DIDÁCTICO (Prompt 10)*: Auditoría
    private void auditarIntegridad() {
        vista_ConsolaUtils.borrarPantalla();
        controlador.construirListados();
        
        System.out.println("=== AUDITORÍA INICIAL ===");
        List<String> reporte = controlador.auditarIntegridad();
        if (reporte.isEmpty()) System.out.println("OK: No se encontraron errores de referencias.");
        else for (String err : reporte) System.out.println(err);

        System.out.println("\n=== INYECTANDO ERROR TEMPORAL ===");
        controlador.inyectarErrorTemporal(); // Le metemos una materia "A-FAKE" al E001
        
        reporte = controlador.auditarIntegridad();
        for (String err : reporte) System.out.println(err); // Aquí debería saltar el error

        System.out.println("\nRestaurando datos...");
        controlador.removerErrorTemporal();
        System.out.println("[SUSTENTACIÓN]: Map facilita consultar la materia rápido por su código, pero no garantiza por sí solo que el código que le pases exista. Por eso es vital la auditoría.");
        vista_ConsolaUtils.pausar("");
    }
}