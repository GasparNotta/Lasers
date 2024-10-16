package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;

public class BloqueCristal extends Bloque {
    public BloqueCristal() {
        super("BloqueCristal");
    }

    @Override
    public void interactuarConLaser(Laser laser,String posicionImpacto) {
        laser.refractar( posicionImpacto );
    }
}
