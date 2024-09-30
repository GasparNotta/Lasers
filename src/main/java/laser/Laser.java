package laser;

public class Laser {

    private String direccion;  // Dirección del rayo láser
    private final Coordenada coordenada_inicial;  // Coordenada del láser
    
    public Laser(Coordenada coordenada_inicial, String direccion) {
        this.coordenada_inicial = coordenada_inicial;
        this.direccion = direccion;
    }

    public void absorber() {
        // Lógica de absorción
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

    public Coordenada getCoordenadaInicial() {
        return coordenada_inicial;
    }

    public String getDireccion() {
        return direccion;
    }

}






