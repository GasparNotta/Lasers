package laser;

import java.util.List;
import laser.tipos_de_bloque.*;


public class Tablero {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    //------
    private Coordenada[][] coordenadas;
    //------

    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();


        //------
        this.coordenadas = new Coordenada[filas*2][columnas*2];
        //------


        this.celdas = new Celda[filas][columnas];
        inicializarTablero();
        inicializarBloques(nivel.getConfiguracionBloques());
        inicializarElementos(nivel.getConfiguracionElementos());



        mostrarTablero();
    }






    private void inicializarTablero() {
        System.out.println(); 
        for (int i = 0; i < filas*2; i++) {
            System.err.println();
            for (int j = 0; j < columnas*2; j++) {
                
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
        System.out.println("Inicializando elementos");
        System.out.println();
        
        for (String linea : configuracion_elementos) {
            System.out.println("Procesando línea: " + linea);
        
            // Dividir la línea en sus componentes
            String[] partes = linea.split(" ");
        
            char tipo = partes[0].charAt(0);  
            int fila = Integer.parseInt(partes[1]);
            int columna = Integer.parseInt(partes[2]);
        
            switch (tipo) {
                case 'E':  // Emisor
                    String direccion = partes[3];  // La dirección del emisor (ej: 'SE')
                    System.out.println("Emisor encontrado en la posición [" + fila + ", " + columna + "] con dirección " + direccion);
                    break;
        
                case 'G':  // Objetivo
                    System.out.println("Objetivo encontrado en la posición [" + fila + ", " + columna + "]");
                    //Objetivo objetivo = new Objetivo();  
                    //celdas[fila][columna].setObjetivo(objetivo);
                    break;
        
            }
        }
    }

    private void mostrarTablero() {
        System.out.println("Mostrando tablero");
        System.out.println();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = celdas[i][j];
                celda.getCoordenada().imprimir();
            }
            System.out.println();
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
}