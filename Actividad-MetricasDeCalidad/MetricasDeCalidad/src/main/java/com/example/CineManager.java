package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class CineManager {
    private List<Pelicula> peliculas;
    private Map<Integer, Sala> salas;

    public CineManager() {
        peliculas = new ArrayList<>();
        salas = new HashMap<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public boolean eliminarPelicula(String titulo) {
        return peliculas.removeIf(p -> p.getTitulo().equals(titulo));
    }

    public void agregarSala(Sala sala) {
        salas.put(sala.getId(), sala);
    }

    public boolean eliminarSala(int id) {
        return salas.remove(id) != null;
    }

    public List<Sesion> sesionesPorPelicula(String titulo) {
        return peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .flatMap(p -> p.getSesiones().stream())
                .collect(Collectors.toList());
    }
    public void programarSesion(String titulo, Sesion sesion) {
        peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .findFirst()
                .ifPresent(p -> p.agregarSesion(sesion));
    }

    public int contarPeliculas() {
        return peliculas.size();
    }

    public int contarSalas() {
        return salas.size();
    }
}

class Pelicula {
    private String titulo;
    private List<Sesion> sesiones;

    public Pelicula(String titulo) {
        this.titulo = titulo;
        sesiones = new ArrayList<>();
    }

    public void agregarSesion(Sesion sesion) {
        sesiones.add(sesion);
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Sesion> getSesiones() {
        return new ArrayList<>(sesiones);
    }
}

class Sala {
    private int id;
    private int capacidad;

    public Sala(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }
}

class Sesion {
    private String horaInicio;
    private int duracion; // Duración en minutos
    public Sesion(String horaInicio, int duracion) {
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }
}
