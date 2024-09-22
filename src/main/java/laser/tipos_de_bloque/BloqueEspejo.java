package laser.tipos_de_bloque;

public class BloqueEspejo {
    // Atributos
    private boolean reflejaLaser;

    // Constructor
    public BloqueEspejo() {
        this.reflejaLaser = true;
    }

    // Métodos
    public boolean reflejaLaser() {
        return reflejaLaser;
    }

    @Override
    public String toString() {
        return "R";
    }
    
}
