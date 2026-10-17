#include <iostream>
#include <vector>
#include <string>
#include <limits>
using namespace std;

struct Proceso {
    int id;
    string nombre;
    int memoria;
    bool activo;
};
int main() {
    vector<Proceso> procesos; // crea el tipo de dato
    const int memoriaTotal = 1024;
    int memoriaUtilizada = 0;
    int siguienteID = 1;
    int opcion;
    do {
        cout <<
        "\n================================\n";
        cout << " SIMULADOR DE GESTION DE MEMORIA\n";
        cout <<"================================\n";
        cout << "1. Registrar proceso\n";
        cout << "2. Consultar memoria\n";
        cout << "3. Mostart procesos\n";
        cout << "4. Liberar memoria\n";
        cout << "5. Salir\n";
        cout << "Seleccione una opcion: ";
        if (!(cin >> opcion)) {
            cout << "Entrada no valida.\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(),'\n'); // parta la memoria
            continue;
        }
        switch (opcion) {
            // OPCION 1: REGISTRAR PROCESO
            case 1: {
                Proceso p;
                cout << "\nNombre del proceso: ";
                cin >> p.nombre;
                cout << "Memoria solicitada (MB): ";
                if (!(cin >> p.memoria)) {
                    cout << "Debe ingresar un numer entero.\n";
                    cin.clear();
                    cin.ignore(numeric_limits<streamsize>::max(), // aqui tambien
                    '\n' );
                    break;
                }
                // Validar que la memoria sea positiva
                if (p.memoria <= 0) {
                    cout << "Error: la memoria debe ser mayor que cero.\n";
                    break;
                }
                // Validar que exista memoria disponible
                int disponible = memoriaTotal - memoriaUtilizada;
                if (p.memoria > disponible) {
                    cout << "Error: memoria insuficiente.\n";
                    cout << "Memoria disponible: " << disponible << " MB\n";
                    break;
                }
                p.id = siguienteID++;
                p.activo = true;
                procesos.push_back(p);
                memoriaUtilizada += p.memoria;
                cout << "\nProceso registrado correctamente.\n";
                cout << "ID: " << p.id << "\n";
                cout << "Memoria asignada: " << p.memoria << " MB\n";
                break;
            }
            // OPCION 2: CONSULTAR MEMORIA
            case 2: {
                cout << "\n--- ESTADO DE LA MEMORIA ---\n";
                cout << "Memoria total: " << memoriaTotal << " MB\n";
                cout << "Memoria utilizada: " << memoriaUtilizada << " MB\n";
                cout << "Memoria disponible: " << memoriaTotal - memoriaUtilizada
                << " MB\n";
                break;
            }
            // OPCION 3: MOSTRAR PROCESOS
            case 3: {
                cout << "\n--- LISTADO DE PROCESOS ---\n";
                if (procesos.empty()) {
                    cout << "No hay procesos registrados.\n";
                    } else {
                        for (const Proceso &p : procesos) {
                            cout << "\nID: " << p.id;
                            cout << "\nNombre: " << p.nombre;
                            cout << "\nMemoria: " << p.memoria << " MB";
                            cout << "\nEstado: " << (p.activo ? "ACTIVO" : "FINALIZADO");
                            cout << "\n------------------------\n";
                        }
                    }
                break;
            }
            // OPCION 4: LIBERAR MEMORIA
            case 4: {
                int idBuscar;
                cout << "\nIngrese el ID del proceso: ";
                if (!(cin >> idBuscar)) {
                    cout << "ID no valido.\n";
                    cin.clear();
                    cin.ignore(
                    numeric_limits<streamsize>::max(),
                    '\n' );
                    break;
                }
                bool encontrado = false;
                for (Proceso &p : procesos) {
                    if (p.id == idBuscar) {
                        encontrado = true;
                        if (p.activo) {
                            memoriaUtilizada -= p.memoria;
                            p.activo = false;
                            cout << "Proceso finalizado correctamente.\n";
                            cout << "Memoria liberada: " << p.memoria << " MB\n";
                        } else {
                            cout << "El proceso ya fue finalizado.\n";
                            cout << "No se libera memoria nuevamente.\n";
                            }
                        break;
                    }
                }
                if (!encontrado) {
                    cout << "No existe un proceso con eseID.\n";
                    }
                break;
            }
            // OPCION 5: SALIR
            case 5:
                cout << "\nCerrando el simulador...\n";
                break;
            default:
                cout << "Opcion incorrecta. Intente nuevamente.\n";
                }
    } while (opcion !=5);
        return  0;
}