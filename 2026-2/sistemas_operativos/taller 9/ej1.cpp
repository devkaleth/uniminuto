#include <iostream>
#include <string>

using namespace std;

int main() {
    string binario;

    cout << "Ingresa un número binario: ";
    cin >> binario;

    try {
        // std::stoi convierte la cadena a entero.
        // El tercer argumento (2) especifica que la base es binaria.
        int decimal = stoi(binario, nullptr, 2);

        cout << "El número en decimal es: " << decimal << endl;
    }
    catch (invalid_argument const& e) {
        cout << "Error: La cadena ingresada no es un número binario válido." << endl;
    }

    return 0;
}