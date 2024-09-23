package laser;

public class Celda {
    // Atributos
    private boolean tienePiso;
    private Bloque bloque;

    // Constructor
    public Celda(boolean tienePiso) {
        this.tienePiso = tienePiso;
        this.bloque = null; // Celda vacía inicialmente
    }

    // Métodos  -------------------------- REVISAR QUE SE UTILIZA------------------------------------
    // Método para colocar un bloque en la celda
    public boolean tienePiso() {
        return tienePiso;
    }
 
    // Método para obtener el bloque de la celda
    public Bloque getBloque() {
        return bloque;
    }

    // Método para colocar un bloque en la celda
    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    // Método para quitar un bloque de la celda
    public boolean estaVacia() {
        return tienePiso && bloque == null;
    }

    // Método toString
    @Override
    public String toString() {
        return tienePiso ? (bloque != null ? bloque.toString() : ".") : " ";
    }
}

