#include <iostream>


using namespace std;

int main() {
    time_t tiempo_actual = time(nullptr);
    tm* fecha_local = localtime(&tiempo_actual);
    int ano_actual = fecha_local->tm_year + 1900;

    int num = 0;
    cout<< "ingrese el ano de nacimiento" << endl;
    cin >> num;

    int resultado = num - ano_actual;
    cout<< "La edad es: " << abs(resultado);


    return 0;

}