package model;

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
        this.recorrido_laser = new ArrayList<>();
    }

    public void absorber() {
        setDireccion(" ");
    }

    public void reflejar(TipoImpacto impacto) {
        switch (direccion) {
            case "SE":
                if (impacto == TipoImpacto.DEBAJO) { setDireccion("NE");} 
                else if (impacto == TipoImpacto.COSTADO_DERECHA) { setDireccion("SW");}
                break;
            case "SW":
                if (impacto == TipoImpacto.DEBAJO) { setDireccion("NW");} 
                else if (impacto == TipoImpacto.COSTADO_IZQUIERDA) {setDireccion("SE");}
                break;
            case "NE":
                if (impacto == TipoImpacto.ARRIBA) { setDireccion("SE");} 
                else if (impacto == TipoImpacto.COSTADO_DERECHA) { setDireccion("NW");}
                break;
            case "NW":
                if (impacto == TipoImpacto.ARRIBA) {setDireccion("SW");} 
                else if (impacto == TipoImpacto.COSTADO_IZQUIERDA) { setDireccion("NE");}
                break;
        }
    }

    public void difractar(TipoImpacto posicionImpacto) {
        reflejar(posicionImpacto);
    }

    public void refractar(TipoImpacto posicionImpacto) {
        switch (posicionImpacto) {
            case ARRIBA:
                setDireccion("N");
                break;
            case DEBAJO:
                setDireccion("S");
                break;
            case COSTADO_IZQUIERDA:
                setDireccion("W");
                break;
            case COSTADO_DERECHA:
                setDireccion("E");
                break;
            case NINGUNO:
                break;
        }
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public ArrayList<String> getRecorridoLaser() {
        return recorrido_laser;
    }

    public void agregarCoordenadaRecorrido(String coordenadas_y_direccion) {
        recorrido_laser.add(coordenadas_y_direccion);
    }

    public void deleteRecorridoLaser() {
        recorrido_laser.clear();
    }
}
