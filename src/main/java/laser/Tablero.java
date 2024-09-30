package laser;

import java.util.List;
import laser.tipos_de_bloque.*;


public class Tablero {
    private Coordenada[][] coordenadas;
    
    private int filas;
    private int columnas;
    
    private Celda[][] celdas;
    private Laser laser;
    private Objetivo objetivo;
   

    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();
        this.coordenadas = new Coordenada[(filas*2)+1][(columnas*2)+1];
        this.celdas = new Celda[filas][columnas];
        inicializarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());

        // mostrarTablero();
        mostrarTablero();
    }






    private void inicializarTablero() {
        for (int i = 0; i <= filas*2; i++) {
            for (int j = 0; j <= columnas*2; j++) {
                coordenadas[i][j] = new Coordenada(i, j);
                if (coordenadas[i][j].esCelda()) {
                    celdas[i / 2][j / 2] = new Celda(coordenadas[i][j]);
                }
                if (i == 0 || i == filas*2 || j == 0 || j == columnas*2) {
                    coordenadas[i][j].establecerBorde();
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

                    laser = new Laser(coordenadas[fila][columna],direccion);
                    coordenadas[fila][columna].establecerLaser();
                    coordenadas[fila][columna].establecerNoBorde();

                    
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
            for (int j = 0; j <= columnas*2; j++) {
                if (coordenadas[i][j].esCelda()) {
                    celdas[i / 2][j / 2].imprimir();
                } else if (coordenadas[i][j].esLaser()) {
                    System.out.println("Laser en:");
                    coordenadas[i][j].imprimir();
                } else if (coordenadas[i][j].esObjetivo()) {
                    System.out.println("Objetivo en:");
                    coordenadas[i][j].imprimir();
                } else if(coordenadas[i][j].pasaLaser()){
                    System.out.println("Pasa laser en:");
                    coordenadas[i][j].imprimir();
                } else if(coordenadas[i][j].esBorde()){
                    System.out.println("Borde en:");
                    coordenadas[i][j].imprimir();
                } else {
                    coordenadas[i][j].imprimir();
                }
            }
            System.out.println();
        }
        
    }


    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }

    public Coordenada getCoordenada(int fila, int columna) {
        return coordenadas[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Laser getLaser() {
        return laser;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public Coordenada getLaserCordenadaInicial() {
        return laser.getCoordenadaInicial();
    }

    public Coordenada getObjetivoCordenada() {
        return objetivo.getCoordenada();
    }

}