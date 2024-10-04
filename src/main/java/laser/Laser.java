package laser;

public class Laser {

    private String direccion;  // Dirección del rayo láser
    private final Coordenada coordenada_inicial;  // Coordenada del láser
    
    public Laser(Coordenada coordenada_inicial, String direccion) {
        this.coordenada_inicial = coordenada_inicial;
        this.direccion = direccion;
    }

    public void absorber() {
        setDireccion(" ");
    }

    public void reflejar(String posicionImpacto) {
        switch (direccion) {
            case "SE":
                if (posicionImpacto.equals("debajo")) {  // Si golpea en el borde inferior del bloque
                    setDireccion("NE");
                } else if (posicionImpacto.equals("costado")) {  // Si golpea en el costado derecho del bloque
                    setDireccion("SW");
                }
                break;
    
            case "SW":
                if (posicionImpacto.equals("debajo")) {  // Si golpea en el borde inferior del bloque
                    setDireccion("NW");
                } else if (posicionImpacto.equals("costado")) {  // Si golpea en el costado izquierdo del bloque
                    setDireccion("SE");
                }
                break;
    
            case "NE":
                if (posicionImpacto.equals("arriba")) {  // Si golpea en el borde superior del bloque
                    setDireccion("SE");
                } else if (posicionImpacto.equals("costado")) {  // Si golpea en el costado derecho del bloque
                    setDireccion("NW");
                }
                break;
    
            case "NW":
                if (posicionImpacto.equals("arriba")) {  // Si golpea en el borde superior del bloque
                    setDireccion("SW");
                } else if (posicionImpacto.equals("costado")) {  // Si golpea en el costado izquierdo del bloque
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

    public void refractar() {
        setDireccion(" ");
    }

    public Coordenada getCoordenadaInicial() {
        return coordenada_inicial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}






