#include <iostream>

using namespace std;

int main() {
    int a, b, c;

    // Pedir los tres números
    cout << "Ingresa el primer número: ";
    cin >> a;

    cout << "Ingresa el segundo número: ";
    cin >> b;

    cout << "Ingresa el tercer número: ";
    cin >> c;

    cout << "\nLos números ordenados de mayor a menor son: ";

    // Comparaciones para ordenar de mayor a menor
    if (a >= b && a >= c) {
        if (b >= c) {
            cout << a << ", " << b << ", " << c;
        } else {
            cout << a << ", " << c << ", " << b;
        }
    } else if (b >= a && b >= c) {
        if (a >= c) {
            cout << b << ", " << a << ", " << c;
        } else {
            cout << b << ", " << c << ", " << a;
        }
    } else {
        if (a >= b) {
            cout << c << ", " << a << ", " << b;
        } else {
            cout << c << ", " << b << ", " << a;
        }
    }

    cout << endl;

    return 0;
}