package laser;

import java.util.List;
import laser.tipos_de_bloque.*;


public class Tablero {
    private Coordenada[][] coordenadas;
    
    private int filas;
    private int columnas;
    
    private Laser laser;
    private Objetivo objetivo;
   

    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();
        this.coordenadas = new Coordenada[(filas*2)+1][(columnas*2)+1];
        inicializarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());

        // mostrarTablero();
        mostrarTablero();
    }






    private void inicializarTablero() {
        for (int fila = 0; fila <= filas*2; fila++) {
            for (int columna = 0; columna <= columnas*2; columna++) {
                coordenadas[fila][columna] = new Coordenada(fila, columna);
                if (fila == 0 || fila == filas*2 || columna == 0 || columna == columnas*2) {
                    coordenadas[fila][columna].establecerBorde();
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
                    coordenadas[i*2+1][j*2+1].establecerCelda();
                    // Agregar bloque según el carácter
                    switch (caracter) {
                        case 'F':
                            coordenadas[i*2+1][j*2+1].establecerBloque(new BloqueFijo());;
                            break;
                        case 'B':
                            coordenadas[i*2+1][j*2+1].establecerBloque(new BloqueMovil());
                            break;
                        case 'R':
                            coordenadas[i*2+1][j*2+1].establecerBloque(new BloqueEspejo());
                            break;
                        case 'G':
                            coordenadas[i*2+1][j*2+1].establecerBloque(new BloqueVidrio());
                            break;
                        case 'C':
                            coordenadas[i*2+1][j*2+1].establecerBloque(new BloqueCristal());
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
                    System.out.println("Celda en:");
                    coordenadas[i][j].imprimir();
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


