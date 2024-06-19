package org.example;

public class Celda {
    private boolean esCamino;
    private boolean esBase;
    private boolean esVacio = false;
    private Tower torre;

    public Celda(boolean esCamino, boolean esBase) {
        this.esCamino = esCamino;
        this.esBase = esBase;
        this.torre = null;
        if (!esCamino && !esBase) this.esVacio = true;
    }

    public boolean esCamino() {
        return esCamino;
    }

    public void setEsCamino(boolean esCamino) {
        this.esVacio = false;
        this.esCamino = esCamino;
    }

    public boolean esBase() {
        return esBase;
    }

    public void setEsBase(boolean esBase) {
        this.esVacio = false;
        this.esBase = esBase;
    }

    public Tower getTorre() {
        return torre;
    }

    public void setTorre(Tower torre) {
        this.esVacio = false;
        this.torre = torre;
    }

    public boolean esTorre(){
        return this.torre == null;
    }

    public boolean esVacio() {
        return esVacio;
    }

    public void setEsVacio(boolean esVacio) {
        this.esVacio = esVacio;
    }
}
