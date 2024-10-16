package model.tipos_de_bloque;
import model.Laser;
import model.Bloque;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio() {
        super("BloqueVidrio");
    }

    @Override
    public void interactuarConLaser(Laser laser,String posicionImpacto) {
        laser.difractar(posicionImpacto);
    }
}
