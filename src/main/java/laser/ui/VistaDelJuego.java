package laser.ui;

import laser.Bloque;
import laser.Tablero;
import laser.Juego;
import laser.Coordenada;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaDelJuego {
    private StackPane root_pane; // Contendrá ambos, el tablero y la capa del láser

    private GridPane grid_pane_celdas;  // Para las celdas del tablero
    private Pane pane_elementos = new Pane();  // Para el láser
    private int tamaño_celda = 40; // Tamaño visual de cada celda en píxeles
    private int tamaño_borde = 1; // Tamaño visual del borde en píxeles
    private int radio_elemento = 5; // Radio visual del láser en píxeles

    public VistaDelJuego() {
        root_pane = new StackPane(); // Para superponer ambas capas

        grid_pane_celdas = new GridPane();  // Para dibujar el tablero
        pane_elementos = new GridPane();  // Para dibujar el láser
        

        root_pane.getChildren().addAll(grid_pane_celdas, pane_elementos);
    }






    // Método para crear visualmente el tablero basado en la lógica
    public void generarTableroVisual(Juego juego) {
        juego.jugar();  // Ejecuta la lógica del juego

        // Obtener el tablero
        Tablero tablero = juego.getTablero();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        // Limpiar el GridPane y el Path del láser antes de dibujar
        grid_pane_celdas.getChildren().clear(); // Limpiar celdas visuales anteriores
        pane_elementos.getChildren().clear(); // Limpiar láser visual anterior
        
        // Iterar sobre las celdas del tablero lógico y generar los elementos visuales
        for (int i = 0; i <= filas; i++) {  // Cambiar a i < filas
            for (int j = 0; j <= columnas; j++) {  // Cambiar a j < columnas
                Coordenada coordenada = tablero.getCoordenada(i, j);
                
                if (coordenada.esCelda()) {
                    Rectangle rect = new Rectangle(tamaño_celda, tamaño_celda);
                    rect.setStroke(Color.BLACK);  // Borde Negro
                    rect.setStrokeWidth(tamaño_borde);  // Grosor del borde
                    rect.setFill(Color.LIGHTGRAY);  // Celdas vacías
                    
                    Bloque bloque = coordenada.getBloque();
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
                        }
                    }
                    grid_pane_celdas.add(rect,j,i);
                }

                if(coordenada.esLaser()){
                    int posicion_x = ((tamaño_celda*j/2) - (radio_elemento) + (tamaño_borde*j/2));
                    int posicion_y = ((tamaño_celda*i/2) - (radio_elemento) + (tamaño_borde*i/2));
                    Circle circ = new Circle(radio_elemento);
                    circ.translateXProperty().set(posicion_x);
                    circ.translateYProperty().set(posicion_y);
                    circ.setFill(Color.RED);
                    
                    pane_elementos.getChildren().addAll(circ);
                } else if (coordenada.esObjetivo()) {
                    int posicion_x = ((tamaño_celda*j/2) - (radio_elemento) + (tamaño_borde*j/2));
                    int posicion_y = ((tamaño_celda*i/2) - (radio_elemento) + (tamaño_borde*i/2));
                    Circle circ = new Circle(radio_elemento);
                    circ.translateXProperty().set(posicion_x);
                    circ.translateYProperty().set(posicion_y);
                    circ.setFill(Color.BLUE);
                    
                    pane_elementos.getChildren().addAll(circ);
                }
            }
        }
    }
    // Obtener el StackPane que se usará en la escena principal
    public StackPane getRootPane() {
        return root_pane;
    }
}
