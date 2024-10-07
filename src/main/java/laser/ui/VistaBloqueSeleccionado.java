package laser.ui;

import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VistaBloqueSeleccionado {
    // Método para asignar la selección visual al rectángulo
    public void asignarSeleccion(Rectangle rectangulo) {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.LIMEGREEN);  // Color de la sombra
        innerShadow.setRadius(3);               // Radio de la sombra
        innerShadow.setChoke(1);                // Control de la intensidad
        rectangulo.setEffect(innerShadow);      // Aplicar efecto de sombra
    }

    // Método para quitar la selección visual del rectángulo
    public void quitarSeleccion(Rectangle rectangulo) {
        rectangulo.setEffect(null);  // Quitar el efecto visual del rectángulo
    }
}
