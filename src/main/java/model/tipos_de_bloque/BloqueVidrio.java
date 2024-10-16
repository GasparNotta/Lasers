package model.tipos_de_bloque;
import model.Laser;
import model.TipoBloque;
import model.TipoImpacto;
import model.Bloque;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio() {
        super(TipoBloque.VIDRIO);
    }

    @Override
    public void interactuarConLaser(Laser laser,TipoImpacto impacto) {
        laser.difractar(impacto);
    }
}
