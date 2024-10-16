package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo() {
        super("BloqueEspejo");
    }

    @Override
    public void interactuarConLaser(Laser laser, String posicionImpacto ) {
        laser.reflejar(posicionImpacto);
    }
}