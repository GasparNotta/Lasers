package laser.tipos_de_bloque;
import laser.Laser;
import laser.Bloque;

public class BloqueVidrio extends Bloque {
    public BloqueVidrio() {
        super("BloqueVidrio"); // Tipo de bloque
    }

    @Override
    public void interactuarConLaser(Laser laser,String posicionImpacto) {
        // Difracta el rayo en 2
        laser.difractar();
        System.out.println("Bloque de Vidrio: El rayo se ha difractado.");
    }
}
