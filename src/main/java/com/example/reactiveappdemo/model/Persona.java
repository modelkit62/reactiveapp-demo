package com.example.reactiveappdemo.model;


public class Persona {

    private Integer id;
    private String nombre;
    private Integer edad;

    public Persona() {
    }

    public Persona(Integer id, String nombre, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public Persona setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Integer getEdad() {
        return edad;
    }

    public Persona setEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }


}
