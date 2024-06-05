package com.example;

import java.util.*;

public class SalaManager {
    private Map<Integer, Sala> salas;

    public SalaManager() {
        salas = new HashMap<>();
    }

    public void agregarSala(Sala sala) {
        salas.put(sala.getId(), sala);
    }

    public boolean eliminarSala(int id) {
        return salas.remove(id) != null;
    }

    public int contarSalas() {
        return salas.size();
    }
}