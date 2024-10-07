package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueMovil extends Bloque {
    public BloqueMovil() {
        super("BloqueMovil");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto) {
        laser.absorber();
        }
}
