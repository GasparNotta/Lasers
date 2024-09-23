package laser;

public class Laser {

    // Falta terminar de integrar



    private int x;  // Coordenada X
    private int y;  // Coordenada Y
    private int direccion;  // Dirección del rayo láser

    public Laser(int x, int y, int direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
    }

    
    public void reflejar() {
        // Lógica de reflexión
    }

    public void difractar() {
        // Lógica de difracción
    }

    public void refractar() {
        // Lógica de refracción
    }






    // Getters y setters de las coordenadas
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters y setters de la dirección
    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
}
