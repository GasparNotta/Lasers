package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;

public class BloqueMovil extends Bloque {
    public BloqueMovil() {
        super("BloqueMovil");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto) {
        laser.absorber();
        }
}
