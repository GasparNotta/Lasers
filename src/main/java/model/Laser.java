package model;

import java.util.ArrayList;

public class Laser {
    private final TipoDireccion direccion_inicial;  // Dirección del rayo láser al iniciar el trazado
    private TipoDireccion direccion;  // Dirección del rayo láser (puede cambiar a la hora de realizar el trazado)
    private final Coordenada coordenada_inicial;  // Coordenada del láser
    private ArrayList<String> recorrido_laser; // Lista de coordenadas por las que pasa el láser
    
    public Laser(Coordenada coordenada_inicial, TipoDireccion direccion_inicial) {
        this.coordenada_inicial = coordenada_inicial;
        this.direccion_inicial = direccion_inicial;
        this.direccion = direccion_inicial;
        this.recorrido_laser = new ArrayList<>();
    }

    public void absorber() {
        setDireccion(TipoDireccion.SIN_DIRECCION);
    }

    public void reflejar(TipoImpacto impacto) {
        switch (direccion) {
            case SE: setDireccion(impacto == TipoImpacto.DEBAJO ? TipoDireccion.NE : TipoDireccion.SW); break;
            case SW: setDireccion(impacto == TipoImpacto.DEBAJO ? TipoDireccion.NW : TipoDireccion.SE); break;
            case NE: setDireccion(impacto == TipoImpacto.ARRIBA ? TipoDireccion.SE : TipoDireccion.NW); break;
            case NW: setDireccion(impacto == TipoImpacto.ARRIBA ? TipoDireccion.SW : TipoDireccion.NE); break;
            case N: case S: case E: case W: case SIN_DIRECCION: break;
        }
    }

    public void difractar(TipoImpacto posicionImpacto) {
        reflejar(posicionImpacto);
    }

    public void refractar(TipoImpacto posicionImpacto) {
        switch (posicionImpacto) {
            case ARRIBA: setDireccion(TipoDireccion.N); break;
            case DEBAJO: setDireccion(TipoDireccion.S); break;
            case COSTADO_IZQUIERDA: setDireccion(TipoDireccion.W); break;
            case COSTADO_DERECHA: setDireccion(TipoDireccion.E); break;
            case NINGUNO: break;
        }
    }

    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

    public Coordenada getCoordenadaInicial() {
        return coordenada_inicial;
    }

    public TipoDireccion getDireccionInicial() {
        return direccion_inicial;
    }
    public TipoDireccion getDireccion() {
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
