Algoritmo tarea
	Definir Intenet Como Logico;
	Intenet <- falso;
	Escribir "Es estudiante preparando sus materiales";
	Esperar 2 Segundos;
	Escribir "El estudiante empieza a trabajar ";
	Esperar 2 Segundos;
	Escribir "Ay intenet ?? (falso / verdadero)";
	Esperar 2 Segundos;
	Leer Intenet;
	Mientras Intenet = falso Hacer	
		Escribir "El estudiante no puede realizar tu tarea";	
	Esperar 2 Segundos;
		Escribir "Ay intenet ?? (falso / verdadero)";
		Leer Intenet;
	FinMientras
	Si Intenet = verdadero Entonces 
		Escribir "El estudiante puede hacer la tarea";	
		Esperar 2 Segundos;
	FinSi
	Escribir "El estudiante continua su tarea"
	Esperar 2 Segundos;
	Escribir "El estudiante finalizo la tarea ..."

FinAlgoritmo