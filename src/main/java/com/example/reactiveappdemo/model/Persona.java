package com.example.reactiveappdemo.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return new EqualsBuilder()
                .append(id, persona.id)
                .append(nombre, persona.nombre)
                .append(edad, persona.edad)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(nombre)
                .append(edad)
                .toHashCode();
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
