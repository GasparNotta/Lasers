package laser;

public class Celda {
    // Atributos
    private boolean tiene_piso;
    private Bloque bloque;

    // Constructor
    public Celda(boolean tienePiso) {
        this.tiene_piso = tienePiso;
        this.bloque = null; // Celda vacía inicialmente
    }

    // Métodos  -------------------------- REVISAR QUE SE UTILIZA------------------------------------
    
    // Método para colocar un bloque en la celda
    public boolean tienePiso() {
        return tiene_piso;
    }
 
    // Método para obtener el bloque de la celda
    public Bloque getBloque() {
        return bloque;
    }

    // Método para colocar un bloque en la celda
    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }
}

