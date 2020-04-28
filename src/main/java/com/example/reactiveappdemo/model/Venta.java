package com.example.reactiveappdemo.model;

import java.time.LocalDate;

public class Venta {

    Integer id;
    LocalDate date;

    public Venta(Integer id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Venta setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Venta setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
