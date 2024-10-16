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
    public void interactuarConLaser(Laser laser) {
        // Implementación vacía o lanzar excepción si no es aplicable
        throw new UnsupportedOperationException("BloqueVidrio no puede interactuar con el láser de esta manera.");
    }

    @Override
    public void interactuarConLaser(Laser laser,TipoImpacto impacto) {
        laser.difractar(impacto);
    }
}
