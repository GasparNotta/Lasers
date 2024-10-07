package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueFijo extends Bloque {
    public BloqueFijo() {
        super("BloqueFijo");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto) {
        laser.absorber();
    }
}
