package controller.controles;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import view.VistaBloqueSeleccionado;
import view.VistaTablero;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Juego;


public class ControlDelJuego {
    private Rectangle primerRectanguloSeleccionado = null;
    private Pane juego_pane;
    private Juego juego;

    // Instancias de las clases Juego y VistaTablero
    VistaTablero vistaTablero = new VistaTablero();
    VistaBloqueSeleccionado vistaBloqueSeleccionado = new VistaBloqueSeleccionado();
    
    public void iniciarJuego(String numero_nivel, Pane juego_pane) {
        this.juego_pane = juego_pane;
        juego = new Juego(numero_nivel);
        juego_pane.setStyle("-fx-background-color: transparent;");
        actualizarTablero();
    }

    private void actualizarTablero () {
        juego.actualizar();
        vistaTablero.generarJuegoVisual(juego);
        // Limpiar el panel antes de añadir el nuevo tablero
        juego_pane.getChildren().clear();
        juego_pane.getChildren().add(vistaTablero.getRootPane());
        if (juego.nivelCompletado()) {
            juego_pane.setStyle("-fx-background-color: lightgreen;");
        }else{
            manejarClicRectangulo();
        }
    }

    // Método para escuchar el clic en los rectángulos
    private void manejarClicRectangulo() {
        GridPane grid_pane_celdas = (GridPane) juego_pane.lookup("#grid_pane_celdas");
        for (Node gridNode : grid_pane_celdas.getChildren()) {
            if (gridNode instanceof Rectangle) {
                Rectangle rect = (Rectangle) gridNode;
                // Agregar un evento de clic al rectángulo
                rect.setOnMouseClicked(event -> {
                    moverRectangulo(rect);
                });
            }    
        }
    }

    // Metodo que manejar el clic en los rectángulos
    private void moverRectangulo(Rectangle rect) {
        // Si se hace clic en el mismo rectángulo, deseleccionarlo
        if (primerRectanguloSeleccionado != null && primerRectanguloSeleccionado == rect) {
            vistaBloqueSeleccionado.quitarSeleccion(primerRectanguloSeleccionado);
            primerRectanguloSeleccionado = null;
            return;
        }
        // Si no hay primer rectángulo seleccionado, seleccionar el rectángulo
        if (primerRectanguloSeleccionado == null) {
            primerRectanguloSeleccionado = rect;
            vistaBloqueSeleccionado.asignarSeleccion(primerRectanguloSeleccionado);
        // Si ya hay un rectángulo seleccionado, cambiar el bloque
        } else {
            cambiarBloque(primerRectanguloSeleccionado, rect);
            vistaBloqueSeleccionado.quitarSeleccion(primerRectanguloSeleccionado);
            primerRectanguloSeleccionado = null;
        }
    }

    // Método para cambiar bloques
    private void cambiarBloque(Rectangle rect_a_mover, Rectangle rect_de_destino) {
        // Guardar las posiciones originales
        int fila_rect_a_mover = GridPane.getRowIndex(rect_a_mover);
        int col_rect_a_mover = GridPane.getColumnIndex(rect_a_mover);
        // Guardar las posiciones de destino
        int fila_rect_de_destino = GridPane.getRowIndex(rect_de_destino);
        int col_rect_de_destino = GridPane.getColumnIndex(rect_de_destino);
        // Cambiar los bloques en el tablero
        juego.cambiarBloque(fila_rect_a_mover, col_rect_a_mover, fila_rect_de_destino, col_rect_de_destino);
        actualizarTablero();
    }
}

