package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueMovil extends Bloque {
    public BloqueMovil() {
        super("B"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Absorbe rayos láser, no hace nada
        System.out.println("Bloque Opaco Móvil: El rayo ha sido absorbido.");
    }
}
