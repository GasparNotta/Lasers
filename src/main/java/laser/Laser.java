package laser;

import java.util.ArrayList;

public class Laser {

    private final String direccion_inicial;  // Dirección del rayo láser al iniciar el trazado
    private String direccion;  // Dirección del rayo láser (puede cambiar a la hora de realizar el trazado)
    private final Coordenada coordenada_inicial;  // Coordenada del láser
    private ArrayList<String> recorrido_laser; // Lista de coordenadas por las que pasa el láser
    
    public Laser(Coordenada coordenada_inicial, String direccion_inicial) {
        this.coordenada_inicial = coordenada_inicial;
        this.direccion_inicial = direccion_inicial;
        this.direccion = direccion_inicial;
    }

    public void absorber() {
        setDireccion(" ");
    }

    public void reflejar(String posicionImpacto) {
        switch (direccion) {
            case "SE":
                if (posicionImpacto.equals("debajo")) {  // Si golpea en el borde inferior del bloque
                    setDireccion("NE");
                } else if (posicionImpacto.equals("costado_derecha")) {  // Si golpea en el costado derecho del bloque
                    setDireccion("SW");
                }
                break;
    
            case "SW":
                if (posicionImpacto.equals("debajo")) {  // Si golpea en el borde inferior del bloque
                    setDireccion("NW");
                } else if (posicionImpacto.equals("costado_izquierda")) {  // Si golpea en el costado izquierdo del bloque
                    setDireccion("SE");
                }
                break;
    
            case "NE":
                if (posicionImpacto.equals("arriba")) {  // Si golpea en el borde superior del bloque
                    setDireccion("SE");
                } else if (posicionImpacto.equals("costado_derecha")) {  // Si golpea en el costado derecho del bloque
                    setDireccion("NW");
                }
                break;
    
            case "NW":
                if (posicionImpacto.equals("arriba")) {  // Si golpea en el borde superior del bloque
                    setDireccion("SW");
                } else if (posicionImpacto.equals("costado_izquierda")) {  // Si golpea en el costado izquierdo del bloque
                    setDireccion("NE");
                }
                break;
    
            default:
                System.out.println("Dirección desconocida, no se puede reflejar.");
                break;
        }
    }

    public void difractar() {
        setDireccion(" ");
    }

    public void refractar(String posicionImpacto) {
        switch (posicionImpacto) {
            case "arriba":
                setDireccion("N");
                break;
            case "debajo":
                setDireccion("S");
                break;
            case "costado_izquierda":
                setDireccion("W");
                break;
            case "costado_derecha":
                setDireccion("E");
                break;
            default:
                System.out.println("Posición de impacto desconocida, no se puede refractar.");
                break;
        }
    }

    public Coordenada getCoordenadaInicial() {
        return coordenada_inicial;
    }

    public String getDireccionInicial() {
        return direccion_inicial;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    public void deleteRecorridoLaser() {
        recorrido_laser = new ArrayList<>();
    }
    
    public ArrayList<String> getRecorridoLaser() {
        return recorrido_laser;
    }

    public void agregarCoordenadaRecorrido(String coordenadas_y_direccion) {
        recorrido_laser.add(coordenadas_y_direccion);
    }
}






