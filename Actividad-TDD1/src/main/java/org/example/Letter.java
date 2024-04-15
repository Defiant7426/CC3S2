package org.example;

public class Letter {

    private int puntuacion;

    private Letter(String puntuacion){
        if(puntuacion == "Letra incorrecta"){
            this.puntuacion = -1;
        }
        else if(puntuacion == "Letra correcta y posicion correcta"){
            this.puntuacion = 1;
        }
        else if(puntuacion == "Letra correcta y posicion incorrecta"){
            this.puntuacion = 0;
        }
        else{
            this.puntuacion = -2; // puntuacion no definido
        }
    }


    public int getpuntuacion() {
        return puntuacion;
    }

    public void setpuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "puntuacion=" + puntuacion +
                '}';
    }

    public static final Letter INCORRECT = new Letter("Letra incorrecta");
    public static final Letter CORRECT = new Letter("Letra correcta y posicion correcta");
    public static final Letter PART_CORRECT = new Letter("Letra correcta y posicion incorrecta");
}
