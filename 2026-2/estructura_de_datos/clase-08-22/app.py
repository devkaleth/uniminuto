NombreAlumnos = [
    "fedy",
    "carlos"
    "maria"
    "camilo"
    "bertha"
    ]

for a in NombreAlumnos :
    print(a)

calificaciones = [
    [3 , 3 , 4], 
    [4 , 5 ,4], 
    [2 , 3 , 4], 
    [1 , 1 , 2], 
    [1 , 3 , 4], 
]

for i in range(5) : 
    print(NombreAlumnos[i])
    for i in calificaciones:
        print(f"nombre : {NombreAlumnos[i]}")

