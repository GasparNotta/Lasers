package laser;

public class Tablero {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
    }

    // Inicializa la grilla a partir del archivo de nivel
    public void inicializarDesdeArchivo(String[] configuracion) {
        for (int i = 0; i < configuracion.length; i++) {
            String linea = configuracion[i];
            for (int j = 0; j < linea.length(); j++) {
                char caracter = linea.charAt(j);
                if (caracter == ' ') {
                    celdas[i][j] = new Celda(false); // Sin piso
                } else {
                    celdas[i][j] = new Celda(true); // Con piso
                    
                    if (caracter == 'F') {
                        celdas[i][j].setBloque(new BloqueOpacoFijo());
                    } else if (caracter == 'B') {
                        celdas[i][j].setBloque(new BloqueOpacoMovil());
                    } else if (caracter == 'R') {
                        celdas[i][j].setBloque(new BloqueEspejo());
                    } else if (caracter == 'G') {
                        celdas[i][j].setBloque(new BloqueVidrio());
                    } else if (caracter == 'C') {
                        celdas[i][j].setBloque(new BloqueCristal());
                    }
                }
            }
        }
    }

    public Celda getCelda(Coordenada coord) {
        return celdas[coord.getX()][coord.getY()];
    }
}
