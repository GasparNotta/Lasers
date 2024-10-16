package model.tipos_de_bloque;
import model.Laser;
import model.TipoBloque;
import model.TipoImpacto;
import model.Bloque;

public class BloqueFijo extends Bloque {
    public BloqueFijo() {
        super(TipoBloque.FIJO);
    }

    @Override
    public void interactuarConLaser(Laser laser, TipoImpacto impacto) {
        laser.absorber();
    }
}
