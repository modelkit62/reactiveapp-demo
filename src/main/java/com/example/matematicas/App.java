package com.example.matematicas;

import com.example.reactiveappdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class.getName());

    List<Persona> personas;
    public App() {
        personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(12, "Fidel", 20));
        personas.add(new Persona(13, "Anastasia", 25));
    }

    public void average(){
        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> log.info(p.toString()));
    }

    public void count(){
        Flux.fromIterable(personas)
                .count()
                .subscribe(p -> log.info(p.toString()));
    }

    public void min(){
        Flux.fromIterable(personas)
                // ojo, devuelve un OPTIONAL
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> log.info(p.toString()));
    }

    public void sum(){
        Flux.fromIterable(personas)
                // ojo, devuelve un OPTIONAL
                .collect(Collectors.summingInt((Persona::getEdad)))
                .subscribe(p -> log.info(p.toString()));
    }

    public void resumen(){
        Flux.fromIterable(personas)
                // ojo, devuelve un OPTIONAL
                .collect(Collectors.summarizingInt((Persona::getEdad)))
                .subscribe(p -> log.info(p.toString()));
    }

    public static void main(String[] args) {
        App app = new App();
        // app.average();
        // app.count();
        // app.min();
        // app.sum();
        app.resumen();
    }
}
