package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;
import model.TipoBloque;
import model.TipoImpacto;

public class BloqueEspejo extends Bloque {
    public BloqueEspejo() {
        super(TipoBloque.ESPEJO);
    }

    @Override
    public void interactuarConLaser(Laser laser, TipoImpacto impacto ) {
        laser.reflejar(impacto);
    }
}