package laser.ui;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import laser.Bloque;
import laser.Celda;
import laser.Tablero;

public class VistaDelJuego {
    private GridPane grid_pane;
    private int tamaño_celda = 40; // Tamaño visual de cada celda en píxeles

    public VistaDelJuego() {
        grid_pane = new GridPane();  // Este será el contenedor donde se dibujará el tablero
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
                Rectangle rect = new Rectangle(tamaño_celda, tamaño_celda);

                // Configurar el borde del rectángulo
                rect.setStroke(Color.BLACK);  // Borde Negro
                rect.setStrokeWidth(1);  // Grosor del borde

                if (celda.tienePiso()) {
                Bloque bloque = celda.getBloque();
                
                    if (bloque != null) {
                        switch (bloque.tipoDeBloque()) {
                            case "BloqueFijo":
                                rect.setFill(Color.BLACK);  // Bloques opacos fijos en negro
                                break;
                            case "BloqueMovil":
                                rect.setFill(Color.DARKGRAY);  // Bloques opacos móviles en gris oscuro
                                break;
                            case "BloqueEspejo":
                                rect.setFill(Color.LIGHTSKYBLUE);  // Bloques espejo en azul cielo claro
                                break;
                            case "BloqueVidrio":
                                rect.setFill(Color.LIGHTBLUE);  // Bloques vidrio en azul claro
                                break;
                            case "BloqueCristal":
                                rect.setFill(Color.LIGHTCYAN);  // Bloques cristal en cyan claro
                                break;
                            default:
                                rect.setFill(Color.LIGHTGRAY);  // Cualquier otro bloque
                                break;
                        }
                    } else {
                        rect.setFill(Color.LIGHTGRAY);  // Celdas con piso pero sin bloque
                    }
                } else {
                    rect.setFill(Color.WHITESMOKE);  // Celdas sin piso
                }
                

                // Añadir el rectángulo al GridPane en la posición correspondiente
                grid_pane.add(rect, j, i);
            }
        }
    }

    // Obtener el GridPane que se usará en la escena principal
    public GridPane getGridPane() {
        return grid_pane;
    }
}
