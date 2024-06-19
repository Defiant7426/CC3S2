package org.example;

import java.util.ArrayList;

public class Mapa {
    private Celda[][] mapa;
    private int tamano;

    public Mapa(int tamano) {
        this.tamano = tamano;
        generateMapa();
    }

    public void generateMapa(){
        mapa = new Celda[this.tamano][this.tamano];
        for(int i = 0; i<tamano; i++){
            for(int j=0; j<tamano; j++){
                this.mapa[i][j] = new Celda(false, false);
            }
        }
    }

    public void insertarTorre(Tower torre, int x, int y){
        getCelda(x,y).setTorre(torre);
    }

    public void insertarCamino(int x, int y){
        getCelda(x, y).setEsCamino(true);
    }

    public void insertarBase(int x, int y){
        getCelda(x, y).setEsBase(true);
    }

    public Tower getTorre(int x, int y){
        return getCelda(x, y).getTorre();
    }

    private Celda getCelda(int x, int y){
        return this.mapa[x][y];
    }

    public void imprimirMapa(){
        for(int i = 0; i<tamano; i++){
            for(int j=0; j<tamano; j++){
                if(getCelda(i,j).esVacio()) System.out.print("  ");
                else if(getCelda(i,j).esCamino()) System.out.print("C ");
                else if(getCelda(i,j).esBase()) System.out.print("B ");
                else if(getCelda(i,j).esTorre()) System.out.println("T  ");
            }
            System.out.println();
        }
    }
}