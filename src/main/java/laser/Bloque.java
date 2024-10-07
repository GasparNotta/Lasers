package laser;

public abstract class Bloque {
    private String tipo; 

    // Constructor
    public Bloque(String tipo) {
        this.tipo = tipo;
    }

    // Método para recibir un rayo láser. Debe ser implementado por las subclases 
    public abstract void interactuarConLaser(Laser laser, String posicionImpacto);

    public String tipoDeBloque() {
        return tipo;
    }


}

 