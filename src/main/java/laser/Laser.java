package laser;

public class Laser {

    private String direccion;  // Dirección del rayo láser
    
    public Laser(String direccion) {
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






    // Getters y setters de la dirección
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
