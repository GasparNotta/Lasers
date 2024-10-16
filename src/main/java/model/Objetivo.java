package model;

public class Objetivo {
    private final Coordenada coordenada;  // Coordenada del objetivo
    private boolean alcanzado;  // Indica si el objetivo fue alcanzado
    
    public Objetivo(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.alcanzado = false;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public boolean isAlcanzado() {
        return alcanzado;
    }

    public void setAlcanzado(boolean alcanzado) {
        this.alcanzado = alcanzado;
    }
}