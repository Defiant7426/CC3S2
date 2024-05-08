package org.example;

public class Quiz {
    //manejará el flujo del juego,
    // incluyendo cargar preguntas,
    // presentarlas al usuario y recibir respuestas.

    final int numero_preguntas;
    int puntuacion;

    public Quiz(int numero_preguntas){
        this.numero_preguntas = numero_preguntas;
        this.puntuacion = 0;
    }

    public int getPuntuacion(){
        return puntuacion;
    }

    public boolean verificarRespuesta(Question question,int respuesta){
        if(question.getRespuesta() == respuesta -1){
            puntuacion++;
            return true;
        }
        return false;
    }

    public Question getQuestion(int numero_pregunta){
        if(numero_pregunta<0 || numero_pregunta>numero_preguntas){
            throw new IllegalArgumentException("El argumento esta fuera de rango");
        }
        Question question = new Question();
        String[] respuestas = new String[4];
        switch (numero_pregunta){
            case 1 -> {
                question.setDescripcion("Quien es el presidente actual del Peru?");
                respuestas[0] = "1. Maduro";
                respuestas[1] = "2. Dina Boluarte";
                respuestas[2] = "3. Pedro Castillo";
                respuestas[3] = "4. Donald Trump";

                question.setOpciones(respuestas);

                question.setRespuesta(1);
            }
            case 2 -> {
                question.setDescripcion("Cual es la capital de Peru?");
                respuestas[0] = "1. Ayacucho";
                respuestas[1] = "2. Tacna";
                respuestas[2] = "3. Lima";
                respuestas[3] = "4. Cusco";

                question.setOpciones(respuestas);

                question.setRespuesta(2);
            }
            case 3 -> {
                question.setDescripcion("Cual es el organo mas grande del ser humano?");
                respuestas[0] = "1. piel";
                respuestas[1] = "2. el corazon";
                respuestas[2] = "3. Riñon";
                respuestas[3] = "4. Lengua";

                question.setOpciones(respuestas);

                question.setRespuesta(0);
            }
            case 4 -> {
                question.setDescripcion("¿Cuál es la capital de Francia?");
                respuestas[0] = "1. Madrid";
                respuestas[1] = "2. Londres";
                respuestas[2] = "3. Pariz";
                respuestas[3] = "4. Berlin";

                question.setOpciones(respuestas);

                question.setRespuesta(2);
            }
            case 5 -> {
                question.setDescripcion("¿Cual es el componente de una computadora donde se maneja la logica y las operaciones matematicas?");
                respuestas[0] = "1. Ram";
                respuestas[1] = "2. Placa Madre";
                respuestas[2] = "3. GPU";
                respuestas[3] = "4. Procesador";

                question.setOpciones(respuestas);

                question.setRespuesta(3);
            }
            case 6 ->{
                question.setDescripcion("2+2-2*2");
                respuestas[0] = "1. 0";
                respuestas[1] = "2. 2";
                respuestas[2] = "3. 4";
                respuestas[3] = "4. -2";

                question.setOpciones(respuestas);

                question.setRespuesta(0);
            }
            case 7 ->{
                question.setDescripcion("Quien tiene el cerebro mas grande?");
                respuestas[0] = "1. Uni niño";
                respuestas[1] = "2. Un Hombre";
                respuestas[2] = "3. Una mujer";
                respuestas[3] = "4. Todos tienen el mismo tamaño";

                question.setOpciones(respuestas);

                question.setRespuesta(1);
            }
            case 8 ->{
                question.setDescripcion("Cuantos dedos tiene un ser humano");
                respuestas[0] = "1. 5";
                respuestas[1] = "2. 7";
                respuestas[2] = "3. 10";
                respuestas[3] = "4. 20";

                question.setOpciones(respuestas);

                question.setRespuesta(3);
            }
            case 9 -> {
                question.setDescripcion("La raiz cuadrada de 4");
                respuestas[0] = "1. 2";
                respuestas[1] = "2. -2";
                respuestas[2] = "3. 2 o -2";
                respuestas[3] = "4. 2 y -2";

                question.setOpciones(respuestas);

                question.setRespuesta(0);
            }
            case 10 ->{
                question.setDescripcion("Que es lo que usualmente se encuentra en un baño");
                respuestas[0] = "1. un tomacorriente";
                respuestas[1] = "2. una computadora";
                respuestas[2] = "3. una esponja";
                respuestas[3] = "4. una estufa";

                question.setOpciones(respuestas);

                question.setRespuesta(2);
            }
            default -> {
                return null;
            }
        }
        return question;
    }
}
