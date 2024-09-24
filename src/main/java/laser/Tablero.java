package laser;

import java.util.List;
import laser.tipos_de_bloque.*;


public class Tablero {
    private Coordenada[][] coordenadas;
    private Celda[][] celdas;
    private int filas;
    private int columnas;
    private Laser laser;
   

    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();
        this.coordenadas = new Coordenada[(filas*2)+1][(columnas*2)+1];
        this.celdas = new Celda[filas][columnas];
        inicializarTablero();
        mostrarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());



    }






    private void inicializarTablero() {
        for (int i = 0; i <= filas*2; i++) {
            for (int j = 0; j <= columnas*2; j++) {
                
                coordenadas[i][j] = new Coordenada(i, j);
                if (coordenadas[i][j].esCelda()) {
                    celdas[i / 2][j / 2] = new Celda(coordenadas[i][j]);
                }
            }
        }   
    }

    // Inicializa el tablero con las configuraciones de bloques del nivel
    private void inicializarBloques(List<String> configuracion_bloques) {
        for (int i = 0; i < configuracion_bloques.size(); i++) {
            String linea = configuracion_bloques.get(i);
            for (int j = 0; j < linea.length(); j++) {
                char caracter = linea.charAt(j);
                if (caracter != ' ') {
                    
                    celdas[i][j].establecerPiso();
                    
                    // Agregar bloque según el carácter
                    switch (caracter) {
                        case 'F':
                            celdas[i][j].establecerBloque(new BloqueFijo());
                            break;
                        case 'B':
                            celdas[i][j].establecerBloque(new BloqueMovil());
                            
                            break;
                        case 'R':
                            celdas[i][j].establecerBloque(new BloqueEspejo());
                            
                            break;
                        case 'G':
                            celdas[i][j].establecerBloque(new BloqueVidrio());
                            
                            break;
                        case 'C':
                            celdas[i][j].establecerBloque(new BloqueCristal());
                            
                            break;
                    }
                }
            }
        }
    }      

    private void inicializarElementos(List<String> configuracion_elementos) {
        for (String linea : configuracion_elementos) {
            // Dividir la línea en sus componentes
            String[] partes = linea.split(" ");
        
            char tipo = partes[0].charAt(0);  
            int columna = Integer.parseInt(partes[1]);
            int fila = Integer.parseInt(partes[2]);
        
            switch (tipo) {
                case 'E':  // Emisor
                    String direccion = partes[3];  // La dirección del emisor (ej: 'SE')

                    new Laser(coordenadas[fila][columna],direccion);
                    coordenadas[fila][columna].establecerLaser();

                    
                    break;
        
                case 'G':  
                    new Objetivo(coordenadas[fila][columna]);  
                    coordenadas[fila][columna].establecerObjetivo();
                    break;
        
            }
        }
    }
        

    private void mostrarTablero() {
        for (int i = 0; i <= filas*2; i++) {
            System.out.println();
            for (int j = 0; j <= columnas*2; j++) {
                coordenadas[i][j].imprimir();
            }
        }
        
    }


    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Coordenada getLaserCordenada() {
        return laser.getCoordenada();
    }

}