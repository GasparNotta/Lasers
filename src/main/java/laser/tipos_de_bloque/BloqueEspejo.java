package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo() {
        super("BloqueEspejo");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto ) {
        laser.reflejar(posicionImpacto);
    }
}