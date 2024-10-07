package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueCristal extends Bloque {
    public BloqueCristal() {
        super("BloqueCristal");
    }

    @Override
    public void interactuarConLaser(Laser laser,String posicionImpacto) {
        laser.refractar( posicionImpacto );
    }
}
