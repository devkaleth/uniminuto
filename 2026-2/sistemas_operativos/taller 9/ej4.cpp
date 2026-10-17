#include <iostream>
#include <iomanip>
using namespace std;
int main(void)
{
    float n1, n2;
    float suma, resta, mult;
    cout << "\nIntroduzca un numero: ";
    cin >> n1 ;
    cout << "\nIntroduzca otro numero: ";
    cin >> n2;
    suma = n1 + n2;
    resta = n1 - n2;
    mult = n1 * n2;
    cout << fixed << setprecision(2);
    cout << "\nsuma: " << fixed << setprecision(2) << suma;
    cout << "\nresta: " << resta;
    cout << "\nmultiplicacion: " << mult;
    return 0;
}