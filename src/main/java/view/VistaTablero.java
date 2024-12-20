package view;

import java.util.ArrayList;

import model.Bloque;
import model.Tablero;
import model.Juego;
import model.Laser;
import model.Coordenada;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

public class VistaTablero {
    private StackPane root_pane; // Contendrá el grid_pane_celdas, el pane_elementos y el pane_trazado
    private GridPane grid_pane_celdas;  // Para las celdas del tablero
    private Pane pane_elementos = new Pane();  // Para el laser y objetivos
    private Pane pane_trazado = new Pane();  // Para el trazado del láser
    private int tamaño_celda = 40; // Tamaño visual de cada celda en píxeles
    private int tamaño_borde = 1; // Tamaño visual del borde de cada celda en píxeles
    private int radio_elemento = 4; // Radio visual de los lásers y objetivos en píxeles

    public VistaTablero() {
        root_pane = new StackPane();
        grid_pane_celdas = new GridPane();
        grid_pane_celdas.setId("grid_pane_celdas");
        pane_elementos = new GridPane(); 
        pane_trazado = new Pane();
        pane_elementos.setMouseTransparent(true); // Los clics pasarán a la capa inferior (grid_pane_celdas)
        pane_trazado.setMouseTransparent(true); // Los clics pasarán a la capa inferior (grid_pane_celdas)
        root_pane.getChildren().addAll(grid_pane_celdas, pane_elementos, pane_trazado); 
    }

    // Generar el tablero completo listo para jugar
    public void generarJuegoVisual(Juego juego) {
        generarTableroVisual(juego);
        generarTrazadoVisual(juego);
    }
    
    // Generar el tablero y los elementos visuales
    public void generarTableroVisual(Juego juego) {
        Tablero tablero = juego.getTablero();
        int filas = tablero.getFilas(), columnas = tablero.getColumnas();

        // Limpiar el tablero visual anterior
        grid_pane_celdas.getChildren().clear();
        pane_elementos.getChildren().clear(); 
        
        // Recorrer las coordenadas del tablero
        for (int fila = 0; fila <= filas; fila++) { 
            for (int columna = 0; columna <= columnas; columna++) {
                Coordenada coordenada = tablero.getCoordenada(fila, columna);

                // Crear las celdas visuales
                if (coordenada.esCelda()) {
                    Rectangle rect = new Rectangle(tamaño_celda, tamaño_celda);
                    rect.setStroke(Color.BLACK);
                    rect.setStrokeWidth(tamaño_borde);
                    rect.setFill(Color.LIGHTGRAY);
                    Bloque bloque = coordenada.getBloque();
                    if (bloque != null) {
                        rect.setFill(switch (bloque.tipoDeBloque()) {
                            case FIJO -> Color.BLACK;
                            case MOVIL -> Color.DARKGRAY;
                            case ESPEJO -> Color.DARKCYAN;
                            case VIDRIO -> Color.LIGHTCYAN;
                            case CRISTAL -> Color.LIGHTSEAGREEN;
                            default -> Color.LIGHTGRAY;
                        });
                    }
                    grid_pane_celdas.add(rect, columna, fila);
                }

                // Crear los elementos visuales
                if (coordenada.esLaser() || coordenada.esObjetivo()) {
                    int posicion_x = (tamaño_celda * columna / 2) - (radio_elemento) + (tamaño_borde * columna / 2);
                    int posicion_y = (tamaño_celda * fila / 2) - (radio_elemento) + (tamaño_borde * fila / 2);
                    Circle circ = new Circle(radio_elemento);
                    circ.translateXProperty().set(posicion_x);
                    circ.translateYProperty().set(posicion_y);
                    circ.setFill(coordenada.esLaser() ? Color.RED : (tablero.getObjetivo(fila, columna).isAlcanzado() ? Color.GREEN : Color.BLUE));
                    pane_elementos.getChildren().add(circ);
                }
            }
        }
    }

    // Generar el trazado del láser visual
    public void generarTrazadoVisual(Juego juego){
        pane_trazado.getChildren().clear();
        ArrayList<Laser> lasers = juego.getLasers();
        for (Laser laser : lasers) { // Obtener el recorrido del láser
            ArrayList<String> recorrido = laser.getRecorridoLaser();
            for (int i = 0; i < recorrido.size(); i++) {
                String[] partes = recorrido.get(i).split(" ");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                String direccion = partes[2];

                Line linea = new Line();
                linea.setStrokeWidth(3);
                linea.setStroke(Color.RED);

                // Las coordenadas iniciales de la línea en función de la celda
                linea.setStartX(tamaño_celda * columna / 2);
                linea.setStartY(tamaño_celda * fila / 2);

                // Ajustar las coordenadas finales de la línea según la dirección
                switch (direccion) {
                    case "NE":  // Noreste
                        linea.setEndX(linea.getStartX() + (tamaño_celda * Math.cos(Math.PI / 4) - (radio_elemento * 2)));
                        linea.setEndY(linea.getStartY() - (tamaño_celda * Math.sin(Math.PI / 4) - (radio_elemento * 2)));
                        break;
                    case "SE":  // Sureste
                        linea.setEndX(linea.getStartX() + (tamaño_celda * Math.cos(Math.PI / 4)- (radio_elemento * 2)));
                        linea.setEndY(linea.getStartY() + (tamaño_celda * Math.sin(Math.PI / 4)- (radio_elemento * 2)));
                        break;
                    case "SW":  // Suroeste
                        linea.setEndX(linea.getStartX() - (tamaño_celda * Math.cos(Math.PI / 4)- (radio_elemento * 2)));
                        linea.setEndY(linea.getStartY() + (tamaño_celda * Math.sin(Math.PI / 4)- (radio_elemento * 2)));
                        break;
                    case "NW":  // Noroeste
                        linea.setEndX(linea.getStartX() - (tamaño_celda * Math.cos(Math.PI / 4)- (radio_elemento * 2)));
                        linea.setEndY(linea.getStartY() - (tamaño_celda * Math.sin(Math.PI / 4)- (radio_elemento * 2)));
                        break;
                    case "N":  // Norte
                        linea.setEndX(linea.getStartX());
                        linea.setEndY(linea.getStartY() - tamaño_celda);
                        break;
                    case "S":  // Sur
                        linea.setEndX(linea.getStartX());
                        linea.setEndY(linea.getStartY() + tamaño_celda);
                        break;
                    case "E":  // Este
                        linea.setEndX(linea.getStartX() + tamaño_celda);
                        linea.setEndY(linea.getStartY());
                        break;
                    case "W":  // Oeste
                        linea.setEndX(linea.getStartX() - tamaño_celda);
                        linea.setEndY(linea.getStartY());
                        break;
                }
                pane_trazado.getChildren().add(linea);
            }
        }
    }

    // Obtener el StackPane que se usará en la escena principal
    public StackPane getRootPane() {
        return root_pane;
    }
}
