package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo() {
        super("R"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Refleja los rayos láser
        laser.reflejar();
        System.out.println("Bloque Espejo: El rayo ha sido reflejado.");
    }
}