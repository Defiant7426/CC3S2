package org.example;

public class Map {

    private char[][] grid; // matriz bidimencional que representa el mapa

    public Map(){ // constructor Map
        grid = new char[5][5]; // Se crea un mapa de 5x5
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                grid[i][j] = ' '; // '0' char vacio en cada celda
            }
        }
    }

    public void placeTower(Tower tower, int x, int y) {
        grid[x][y] = tower.getSymbol(); // colocamos la torre en el mapa
    }

    @Override
    public String toString(){ // podemos imprimir el map con esto
        StringBuilder sb = new StringBuilder();
        for(char[] row : grid){
            for(char cell : row){
                sb.append("[").append(cell).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
