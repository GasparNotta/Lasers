package model;

public abstract class Bloque {
    private TipoBloque tipo; 

    // Constructor
    public Bloque(TipoBloque tipo) {
        this.tipo = tipo;
    }

    // Método para recibir un rayo láser. Debe ser implementado por las subclases 
    public abstract void interactuarConLaser(Laser laser, TipoImpacto Impacto);

    public TipoBloque tipoDeBloque() {
        return tipo;
    }


}

 