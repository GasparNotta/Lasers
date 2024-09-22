package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueFijo extends Bloque {
    public BloqueFijo() {
        super("F"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Absorbe rayos láser, no hace nada
        System.out.println("Bloque Opaco Fijo: El rayo ha sido absorbido.");
    }
}
