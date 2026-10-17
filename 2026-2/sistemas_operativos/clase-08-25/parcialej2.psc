Algoritmo parcial
	Definir agua Como Logico;
	Escribir "preparando la ropa";
	Esperar 2 Segundos;
	Escribir "iniciando la lavadora";
	Esperar 2 Segundos;
	Escribir "Ay agua ? ( verdadero / falso)";
	Leer agua;
	Mientras agua = falso Hacer
		Escribir "no se puede lavar la ropa"
		Escribir "esperando agua"
		Esperar 2 Segundos;
		Escribir "Ay agua ? ( verdadero / falso)";
		Leer agua;
	FinMientras
	Si agua = verdadero Entonces
		Escribir "puede seguir lavando ropa";
		Esperar 2 Segundos;
	FinSi
	Escribir "Finalizado lavado"
FinAlgoritmo