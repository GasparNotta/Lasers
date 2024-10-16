package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;

public class BloqueFijo extends Bloque {
    public BloqueFijo() {
        super("BloqueFijo");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto) {
        laser.absorber();
    }
}
