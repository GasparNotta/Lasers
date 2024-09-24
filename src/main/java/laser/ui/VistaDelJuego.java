package laser.ui;

import laser.Bloque;
import laser.Celda;
import laser.Tablero;



// Circle puntoRojo = new Circle(3);  // Radio del punto
// puntoRojo.setFill(Color.RED);
// StackPane.setAlignment(puntoRojo, javafx.geometry.Pos.CENTER_LEFT);

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Circle;



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
                StackPane stackPane = new StackPane();


                if (celda.tienePiso()) {
                Bloque bloque = celda.getBloque();
                    // Configurar el borde del rectángulo
                    rect.setStroke(Color.BLACK);  // Borde Negro
                    rect.setStrokeWidth(1);  // Grosor del borde
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
                
                

                stackPane.getChildren().addAll(rect);
                

               


                // Añadir el StackPane al GridPane
                grid_pane.add(stackPane, j, i);
            }
        }

    }

    // Obtener el GridPane que se usará en la escena principal
    public GridPane getGridPane() {
        return grid_pane;
    }
}
