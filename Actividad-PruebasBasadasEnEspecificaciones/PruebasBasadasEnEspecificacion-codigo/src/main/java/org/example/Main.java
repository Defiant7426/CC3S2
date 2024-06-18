package org.example;

public class Main {
    public static void main(String[] args) {
        SubstringBetween sb = new SubstringBetween();
        String[] respuesta = sb.substringsBetween("axcaycazc", "a", "c");
        for (String s : respuesta) {
            System.out.println(s);
        }
    }
}
