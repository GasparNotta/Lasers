package laser;

public class Laser {

    private String direccion;  // Dirección del rayo láser
    private final Coordenada coordenada;  // Coordenada del láser
    
    public Laser(Coordenada coordenada, String direccion) {
        this.coordenada = coordenada;
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

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public String getDireccion() {
        return direccion;
    }

}



