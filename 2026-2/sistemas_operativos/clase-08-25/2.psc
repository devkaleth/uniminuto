Proceso Clase
	Definir Costo , Total , Cantidad Como entero
	Escribir "Cantidad de lapises : "
    Total <- 1
	Leer Cantidad
	Si Cantidad >= 1000 Entonces
		Costo = 150
		Escribir "El precio del lapiz es de 150"
		Total = Cantidad * Costo
        Escribir Total

	SiNo 	
		Si Cantidad <= 1000 Entonces 
			Costo <- 250
			Escribir "El precio por lapiz es de 250";
			Total <- Cantidad * Costo
            Escribir "total : " , Total
        FinSi
    FinSi
FinProceso