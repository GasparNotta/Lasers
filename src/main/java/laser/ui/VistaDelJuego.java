package laser.ui;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import laser.Celda;
import laser.Tablero;

public class VistaDelJuego {
    private GridPane gridPane;
    private int tamañoCelda = 40; // Tamaño visual de cada celda en píxeles

    public VistaDelJuego() {
        gridPane = new GridPane();  // Este será el contenedor donde se dibujará el tablero
    }

    // Método para crear visualmente el tablero basado en la lógica
    public void generarTableroVisual(Tablero tablero) {
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        // Iterar sobre las celdas del tablero lógico y generar los elementos visuales
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = tablero.getCelda(i, j);  // Obtener la celda lógica

                // Crear un rectángulo que representa la celda
                Rectangle rect = new Rectangle(tamañoCelda, tamañoCelda);

                // Configurar el color del rectángulo según el tipo de celda
                if (celda.tienePiso()) {
                    if (celda.tipoDeBloque().equals("BloqueOpacoFijo")) {
                        rect.setFill(Color.DARKGRAY);  // Bloques opacos fijos en gris oscuro
                    } else if (celda.tipoDeBloque().equals("BloqueOpacoMovil")) {
                        rect.setFill(Color.GRAY);  // Bloques opacos móviles en gris
                    } else if (celda.tipoDeBloque().equals("BloqueEspejo")) {
                        rect.setFill(Color.LIGHTGRAY);  // Bloques espejo en gris claro
                    } else if (celda.tipoDeBloque().equals("BloqueVidrio")) {
                        rect.setFill(Color.LIGHTBLUE);  // Bloques vidrio en azul claro
                    } else if (celda.tipoDeBloque().equals("BloqueCristal")) {
                        rect.setFill(Color.LIGHTCYAN);  // Bloques cristal en cyan claro
                    } else {
                        rect.setFill(Color.LIGHTGRAY);  // Celdas con piso en gris claro
                    }


                    rect.setFill(Color.LIGHTGRAY);  // Celdas con piso en gris claro
                } else {
                    rect.setFill(Color.WHITESMOKE);   // Celdas sin piso en gris oscuro
                }

                // Añadir el rectángulo al GridPane en la posición correspondiente
                gridPane.add(rect, j, i);
            }
        }
    }

    // Obtener el GridPane que se usará en la escena principal
    public GridPane getGridPane() {
        return gridPane;
    }
}
