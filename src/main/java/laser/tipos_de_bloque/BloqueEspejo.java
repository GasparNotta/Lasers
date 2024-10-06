package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo() {
        super("BloqueEspejo"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto ) {
        // Refleja los rayos láser
        laser.reflejar(posicionImpacto);
    }
}