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
        this.filas = nivel.getFilas()*2;
        this.columnas = nivel.getColumnas()*2;
        this.coordenadas = new Coordenada[(filas)+1][(columnas)+1];
        inicializarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());
    }

    private void inicializarTablero() {
        for (int fila = 0; fila <= filas; fila++) {
            for (int columna = 0; columna <= columnas; columna++) {
                coordenadas[fila][columna] = new Coordenada(fila, columna);
                if (fila == 0 || fila == filas || columna == 0 || columna == columnas) {
                    coordenadas[fila][columna].establecerBorde(true);
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
                    coordenadas[fila][columna].establecerBorde(false);
                    break;
        
                case 'G':  
                    objetivo = new Objetivo(coordenadas[fila][columna]);  
                    coordenadas[fila][columna].establecerObjetivo();
                    break;
        
            }
        }
    }

    public void cambiarBloque(Coordenada coordenada_actual, Coordenada coordenada_nueva) {
        System.out.println(coordenada_actual.imprimir());
        System.out.println(coordenada_nueva.imprimir());
        coordenada_nueva.establecerBloque(coordenada_actual.getBloque());
        coordenada_actual.eliminarBloque();
        System.out.println(coordenada_actual.getBloque());
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

    public void imprimirTablero(){
        for (int fila = 0; fila <= filas; fila++) {
            for (int columna = 0; columna <= columnas; columna++) {
                if (coordenadas[fila][columna].esLaser()) {
                    System.out.print("L ");
                } else if (coordenadas[fila][columna].esObjetivo()) {
                    System.out.print("O ");
                } else if (coordenadas[fila][columna].esBorde()) {
                    System.out.print("/ ");
                } else if (coordenadas[fila][columna].esCelda()) {
                    Bloque bloque = coordenadas[fila][columna].getBloque();
                    if (bloque == null) {
                        System.out.print("C ");
                    } else if(bloque.tipoDeBloque().equals("BloqueFijo")){
                        System.out.print("F ");
                    } else if(bloque.tipoDeBloque().equals("BloqueMovil")){
                        System.out.print("B ");
                    } else if(bloque.tipoDeBloque().equals("BloqueEspejo")){
                        System.out.print("R ");
                    } else if(bloque.tipoDeBloque().equals("BloqueVidrio")){
                        System.out.print("G ");
                    } else if(bloque.tipoDeBloque().equals("BloqueCristal")){
                        System.out.print("C ");
                    }
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }


}


