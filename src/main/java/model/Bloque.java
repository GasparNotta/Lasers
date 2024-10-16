package model;

public abstract class Bloque {
    private TipoBloque tipo; 

    // Constructor
    public Bloque(TipoBloque tipo) {
        this.tipo = tipo;
    }

    // Método abstracto para recibir un rayo láser con un solo parámetro
    public abstract void interactuarConLaser(Laser laser);
    
    // Método abstracto para recibir un rayo láser con dos parámetros
    public abstract void interactuarConLaser(Laser laser, TipoImpacto impacto);


    public TipoBloque tipoDeBloque() {
        return tipo;
    }

}

 