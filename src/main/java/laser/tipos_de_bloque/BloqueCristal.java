package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueCristal extends Bloque {
    public BloqueCristal() {
        super("C"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        // Refracta el rayo, continuando en línea recta
        laser.refractar();
        System.out.println("Bloque de Cristal: El rayo se ha refractado.");
    }
}
