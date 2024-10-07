package laser;

import java.util.ArrayList;
import java.util.List;
import laser.tipos_de_bloque.*;


public class Tablero {
    private int filas;
    private int columnas;
    // Listas de lasers y objetivos
    private ArrayList<Laser> lasers;
    private ArrayList<Objetivo> objetivos;
    // Matriz de coordenadas que representan el tablero
    private Coordenada[][] coordenadas;
   
    // Constructor de la clase Tablero
    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas()*2;
        this.columnas = nivel.getColumnas()*2;
        this.coordenadas = new Coordenada[(filas)+1][(columnas)+1];
        this.lasers = new ArrayList<Laser>();
        this.objetivos = new ArrayList<Objetivo>();
        inicializarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());
    }

    // Inicializa el tablero con las coordenadas y los bordes
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
                    int filaTablero = i * 2 + 1;
                    int columnaTablero = j * 2 + 1;
                    coordenadas[filaTablero][columnaTablero].establecerCelda();
                    agregarBloque(filaTablero, columnaTablero, caracter);
                }
                
            }
        }
    }    
    
    // Agrega un bloque a la coordenada indicada
    private void agregarBloque(int fila, int columna, char tipo) {
        switch (tipo) {
            case 'F':
                coordenadas[fila][columna].establecerBloque(new BloqueFijo());
                break;
            case 'B':
                coordenadas[fila][columna].establecerBloque(new BloqueMovil());
                break;
            case 'R':
                coordenadas[fila][columna].establecerBloque(new BloqueEspejo());
                break;
            case 'G':
                coordenadas[fila][columna].establecerBloque(new BloqueVidrio());
                break;
            case 'C':
                coordenadas[fila][columna].establecerBloque(new BloqueCristal());
                break;
        }
    }

    // Inicializa el tablero con las configuraciones de elementos del nivel
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
                    lasers.add(new Laser(coordenadas[fila][columna],direccion));
                    coordenadas[fila][columna].establecerLaser();
                    break;
        
                case 'G':  
                    objetivos.add(new Objetivo(coordenadas[fila][columna]));  
                    coordenadas[fila][columna].establecerObjetivo();
                    break;
        
            }
        }
    }

    // Metodo para cambiar la posicion de un bloque
    public void cambiarBloque(Coordenada coordenada_actual, Coordenada coordenada_nueva) {
        coordenada_nueva.establecerBloque(coordenada_actual.getBloque());
        coordenada_actual.eliminarBloque();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Coordenada getCoordenada(int fila, int columna) {
        return coordenadas[fila][columna];
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }
    
    public Objetivo getObjetivo(int fila, int columna) {
        for (Objetivo objetivo : objetivos) {
            if (objetivo.getCoordenada().obtenerX() == fila && objetivo.getCoordenada().obtenerY() == columna) {
                return objetivo;
            }
        }
        return null;
    }
}



