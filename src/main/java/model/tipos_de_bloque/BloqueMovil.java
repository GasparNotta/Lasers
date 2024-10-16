package model.tipos_de_bloque;
import model.Laser;
import model.TipoBloque;
import model.TipoImpacto;
import model.Bloque;

public class BloqueMovil extends Bloque {
    public BloqueMovil() {
        super(TipoBloque.MOVIL);
    }

    @Override
    public void interactuarConLaser(Laser laser) {
        laser.absorber();
    }

    @Override
    public void interactuarConLaser(Laser laser, TipoImpacto impacto) {
        // Implementación vacía o lanzar excepción si no es aplicable
        throw new UnsupportedOperationException("BloqueMovil no puede interactuar con el láser de esta manera.");
    }
}
