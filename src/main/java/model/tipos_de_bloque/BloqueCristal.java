package model.tipos_de_bloque;
import model.Laser;
import model.TipoBloque;
import model.TipoImpacto;
import model.Bloque;

public class BloqueCristal extends Bloque {
    public BloqueCristal() {
        super(TipoBloque.CRISTAL);
    }

    @Override
    public void interactuarConLaser(Laser laser,TipoImpacto impacto) {
        laser.refractar( impacto );
    }
}
