package laser.ui.controles;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.shape.Rectangle;
import laser.ui.VistaTablero;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import laser.Juego;


public class ControlDelJuego {
    Juego juego = new Juego();
    VistaTablero vistaTablero = new VistaTablero();
    private Rectangle primerRectanguloSeleccionado = null; // Almacena el primer rectángulo seleccionado
    private Pane juego_pane;


    public void jugar(String numero_nivel, Pane juego_pane) {
        this.juego_pane = juego_pane;
        juego.cargarNivel(numero_nivel);
        juego_pane.setStyle("-fx-background-color: transparent;");
        actualizarTablero();
    }

    private void actualizarTablero () {
        vistaTablero.generarTableroVisual(juego);
            
        // Limpiar el panel antes de añadir el nuevo tablero
        juego_pane.getChildren().clear();
        juego_pane.getChildren().add(vistaTablero.getRootPane());

        if (juego.nivelCompletado()) {
            juego_pane.setStyle("-fx-background-color: lightgreen;");
        }else{
            manejarClicRectangulo();
        }

        
    }

    private void manejarClicRectangulo() {
        GridPane grid_pane_celdas = (GridPane) juego_pane.lookup("#grid_pane_celdas");

        // Primero, encontrar el GridPane dentro del juego_pane
        for (Node gridNode : grid_pane_celdas.getChildren()) {
            // Verificar si el nodo es un rectángulo
            if (gridNode instanceof Rectangle) {
                Rectangle rect = (Rectangle) gridNode;
                
                // Agregar un evento de clic al rectángulo
                rect.setOnMouseClicked(event -> {
                    moverRectangulo(rect);
                });
            }    
        }
    }

    // Maneja el clic en los rectángulos
    private void moverRectangulo(Rectangle rect) {
        // Si se hace clic en el mismo rectángulo, deseleccionarlo
        
        if (primerRectanguloSeleccionado != null && primerRectanguloSeleccionado == rect) {
            primerRectanguloSeleccionado.setEffect(null); // Quitar el efecto de sombra
            primerRectanguloSeleccionado = null; // Resetear la selección
            return;
        }
    
        if (primerRectanguloSeleccionado == null) {
            primerRectanguloSeleccionado = rect;
    
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setColor(Color.LIMEGREEN); // Color de la sombra
            innerShadow.setRadius(3); // Radio de la sombra
            innerShadow.setChoke(1); // Controla el "agrandamiento" de la sombra
            primerRectanguloSeleccionado.setEffect(innerShadow); // Aplicar efecto al rectángulo
    
        } else {
            cambiarBloque(primerRectanguloSeleccionado, rect);
            primerRectanguloSeleccionado.setEffect(null);
            primerRectanguloSeleccionado = null;
        }
    }

    // Método para cambiar bloques
    private void cambiarBloque(Rectangle rect_a_mover, Rectangle rect_de_destino) {
        
        // Guardar las posiciones originales
        int fila_rect_a_mover = GridPane.getRowIndex(rect_a_mover);
        int col_rect_a_mover = GridPane.getColumnIndex(rect_a_mover);

        int fila_rect_de_destino = GridPane.getRowIndex(rect_de_destino);
        int col_rect_de_destino = GridPane.getColumnIndex(rect_de_destino);

        
        juego.cambiarBloque(fila_rect_a_mover, col_rect_a_mover, fila_rect_de_destino, col_rect_de_destino);
        actualizarTablero();
        
       
    }
}

