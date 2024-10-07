# Lasers
Trabajo Practico 1, Paradigmas de la programacion, Facultad de Ingenieria de la Universidad de Buenos Aires. Gaspar Notta 111389
    
Como jugar: 
Lasers es un juego de estilo puzzle, que se desarrolla por turnos hasta que el jugador encuentra la solución del problema moviendo bloques. En cada turno, el jugador puede mover un Bloque (que no sea fijo) a cualquier celda que esté libre.
El nivel es completado cuando todos los Objetivos (circulos azules) son alcanzados por al menos un rayo laser.
    
Tipos de bloques:
Bloque opaco fijo: Absorbe rayos laser. No se puede mover.

![F (1)](https://github.com/user-attachments/assets/2a044fc4-2592-48be-afec-656f1acf8628)

Bloque opaco móvil: Absorbe rayos laser.
        
![B](https://github.com/user-attachments/assets/98fa713b-0899-4512-8000-aee45c84d870)

Bloque espejo: Refleja los rayos laser.
        
![R](https://github.com/user-attachments/assets/a8450bfd-2643-4a55-b709-ac5992ba9d13)

        
Bloque de vidrio: Al ser alcanzado por un rayo, el rayo se difracta en 2. Un rayo es reflejado (al igual que el bloque espejo), y el otro continúa con la misma dirección (como si no hubiera ningún bloque).
        
 ![G](https://github.com/user-attachments/assets/e8e50ceb-97a2-4c4c-a8e2-867bd902e74b)

        
Bloque de cristal: Al ser alcanzado por un rayo, el rayo se refracta, continuando en línea recta y saliendo por el extremo opuesto del bloque, con la misma dirección de origen.

![C](https://github.com/user-attachments/assets/acb2529e-027b-4304-90df-36bc9e5198e4)
