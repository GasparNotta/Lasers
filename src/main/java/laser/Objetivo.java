package laser;

public class Objetivo {
    private final Coordenada coordenada;  // Coordenada del láser
    private boolean alcanzado;  // Indica si el objetivo fue alcanzado
    
    public Objetivo(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.alcanzado = false;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setAlcanzado(boolean alcanzado) {
        this.alcanzado = alcanzado;
    }

    public boolean getAlcanzado() {
        return alcanzado;
    }



}
