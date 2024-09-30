package laser.ui;

import laser.Bloque;
import laser.Tablero;
import laser.Juego;
import laser.Coordenada;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class VistaDelJuego {
    private GridPane grid_pane;  // Para las celdas del tablero
    private Pane laser_layer;    // Para dibujar la trayectoria del láser
    private StackPane root_pane; // Contendrá ambos, el tablero y la capa del láser
    private int tamaño_celda = 40; // Tamaño visual de cada celda en píxeles

    public VistaDelJuego() {
        grid_pane = new GridPane();  // Para dibujar el tablero
        laser_layer = new Pane();    // Para dibujar la trayectoria del láser
        root_pane = new StackPane(); // Para superponer ambas capas
        root_pane.getChildren().addAll(grid_pane, laser_layer); // Añadir capas al StackPane
    }

    // Método para crear visualmente el tablero basado en la lógica
    public void generarTableroVisual(Juego juego) {
        // Obtener el tablero
        Tablero tablero = juego.getTablero();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        juego.actualizarTablero();  // Ejecuta la lógica del juego

        // Iterar sobre las celdas del tablero lógico y generar los elementos visuales
        for (int i = 0; i <= filas*2; i++) {
            for (int j = 0; j <= columnas*2; j++) {
                Coordenada coordenada = tablero.getCoordenada(i, j);
                
                
                // Crear un rectángulo que representa la celda
                StackPane stackPane = new StackPane();
                
                
                if (coordenada.esCelda()) {
                    Rectangle rect = new Rectangle(tamaño_celda, tamaño_celda);
                    System.out.println("Coordenada: " + i + " " + j);
                    Bloque bloque = coordenada.getBloque();
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
                    stackPane.getChildren().addAll(rect);
                }
                
                

               
                

                // Añadir el StackPane al GridPane
                grid_pane.add(stackPane, j, i);
            }
        }
        // Después de generar el tablero, dibujar la trayectoria del láser
        dibujarLaser(tablero);

    }


    // Método para dibujar la trayectoria del láser
    public void dibujarLaser(Tablero tablero) {
    }

    // Obtener el GridPane que se usará en la escena principal
    public StackPane getRootPane() {
        return root_pane;
    }
}




