package com.example.sqlite;

public class Receta {
    private String nombre;
    private String ingredientes;
    private String preparacion;


    public Receta(String nombre, String ingredientes, String preparacion) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;

    }

    // Método getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Método getter para los ingredientes
    public String getIngredientes() {
        return ingredientes;
    }

    // Método getter para la preparación
    public String getPreparacion() {
        return preparacion;
    }

    // Método setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método setter para los ingredientes
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    // Método setter para la preparación
    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }


}