// ------ Proyecto Sistema de Calificaciones --------------------------------
// Archivo: Registros.java
// Contenido: Definición de las estructuras de datos (Records) del sistema
// -------------------------------------------------------------------------

// --- DEFINICIÓN DE LOS RECORDS ---

// Record para el manejo de fechas
record RegistroFecha(
    byte dia, 
    byte mes, 
    short anio
) {}

// Record para la información de contacto
record RegistroContacto (
    String email,
    String telefono,
    boolean usaWhatsApp
) {}


/**
*  record RegistroError
 * Representa una entrada del diccionario de errores del sistema.
 *
 * codError  : código único que identifica el error.
 * msjError  : mensaje explicativo asociado al error.
 * ubicacion : componente o método donde se detectó el error.
 *
 * El campo ubicacion será especialmente útil si posteriormente
 * se implementa un mecanismo de LOG o auditoría del sistema.
 */
record RegistroError(
    String codError,
    String msjError,
    String ubicacion
) {}