package laser.ui.controles;

import javafx.fxml.FXML;
import javafx.scene.input.Dragboard;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.ClipboardContent;

public class ControlDelBloque {
    @FXML
    private GridPane grid_pane_celdas; // El GridPane donde se realizarán los arrastres

    @FXML
    public void initialize() {
        // Aquí puedes añadir lógica adicional si es necesario
        // Por ejemplo, puedes configurar el GridPane para manejar otros elementos
    }

    // Método para agregar Drag and Drop a un rectángulo
    public void agregarEventosDragAndDrop(Rectangle rect) {
        // Habilitar el drag
        rect.setOnDragDetected(event -> {
            Dragboard db = rect.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("rectangulo"); // Puedes almacenar más información si lo necesitas
            db.setContent(content);
            event.consume();
        });

        // Manejar el evento de arrastre sobre el GridPane
        grid_pane_celdas.setOnDragOver(event -> {
            if (event.getGestureSource() != grid_pane_celdas && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Manejar el evento de soltar
        grid_pane_celdas.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                // Obtener las coordenadas donde se suelta
                double x = event.getX();
                double y = event.getY();

                // Calcular en qué celda soltar el rectángulo
                int nuevaColumna = (int) (x / rect.getWidth());
                int nuevaFila = (int) (y / rect.getHeight());

                // Verificar límites para no salir del tablero
                if (nuevaColumna >= 0 && nuevaColumna < grid_pane_celdas.getColumnCount() &&
                    nuevaFila >= 0 && nuevaFila < grid_pane_celdas.getRowCount()) {
                    
                    // Mover el rectángulo
                    GridPane.setRowIndex(rect, nuevaFila);
                    GridPane.setColumnIndex(rect, nuevaColumna);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    // Método para configurar el GridPane si es necesario
    private void configurarDragAndDrop() {
        // Lógica adicional si es necesario
    }
}
