package laser;

import java.util.List;

public class Tablero {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    public Tablero(Nivel nivel) {
        this.filas = nivel.getFilas();
        this.columnas = nivel.getColumnas();
        this.celdas = new Celda[filas][columnas];

        inicializarCeldas(nivel.getConfiguracionBloques());
    }

    // Inicializa el tablero con las configuraciones de bloques del nivel
    private void inicializarCeldas(List<String> configuracionBloques) {
        for (int i = 0; i < configuracionBloques.size(); i++) {
            String linea = configuracionBloques.get(i);
            for (int j = 0; j < linea.length(); j++) {
                char caracter = linea.charAt(j);
                if (caracter == ' ') {
                    celdas[i][j] = new Celda(false); // Sin piso
                } else {
                    celdas[i][j] = new Celda(true);  // Con piso
                    // Agregar bloque según el carácter
                    switch (caracter) {
                        case 'F':
                            //celdas[i][j].setBloque(new BloqueOpacoFijo());
                            celdas[i][j] = new Celda(true);
                            break;
                        case 'B':
                            //celdas[i][j].setBloque(new BloqueOpacoMovil());
                            celdas[i][j] = new Celda(true);
                            break;
                        case 'R':
                            //celdas[i][j].setBloque(new BloqueEspejo());
                            celdas[i][j] = new Celda(true);
                            break;
                        case 'G':
                            //celdas[i][j].setBloque(new BloqueVidrio());
                            celdas[i][j] = new Celda(true);
                            break;
                        case 'C':
                            //celdas[i][j].setBloque(new BloqueCristal());
                            celdas[i][j] = new Celda(true);
                            break;
                        default:
                            // No poner bloque si el carácter es '.'
                            break;
                    }
                }
            }
        }
    }

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }

    public void mostrarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(celdas[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}