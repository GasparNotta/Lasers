package laser;

public abstract class Bloque {
    private String tipo; // Atributo para identificar el tipo de bloque

    // Constructor
    public Bloque(String tipo) {
        this.tipo = tipo;
    }

    // Método para recibir un rayo láser. Debe ser implementado por las subclases 
    public abstract void interactuarConLaser(Laser laser);

    // Método para obtener el tipo de bloque
    public String tipoDeBloque() {
        return tipo;
    }



}

