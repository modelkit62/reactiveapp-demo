package com.example.trasnformacion;

import com.example.creational.Creation;
import com.example.reactiveappdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class);

    public static void map(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(12, "Fidel", 18));
        personas.add(new Persona(13, "Anastasia", 26));

        // los mapeo y los altero uno a uno!
        Flux.fromIterable(personas)
                .map(persona -> {
                    persona.setId(0);
                    persona.setEdad(persona.getEdad() + 1);
                    return persona;
                }).subscribe(p -> log.info(p.toString()));
    }

    public static void flatMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(12, "Fidel", 18));
        personas.add(new Persona(13, "Anastasia", 26));

        // CUIDADO!!! EL FLATMAP pide que retorne otro FLUJO DE DATOS MONO!
        Flux.fromIterable(personas)
                .flatMap(persona -> {
                    persona.setId(5);
                    persona.setEdad(persona.getEdad() + 5);
                    return Mono.just(persona);
                }).subscribe(p -> log.info(p.toString()));
    }

    static void mapWithRange(){
        Flux<Integer> range = Flux.range(5, 5);
        // ojo!!! Tengo que guardarlo en otra variable!!!
        Flux<Integer> map = range.map(x -> x + 10);
        map.subscribe(i -> log.info("i: " + i));

    }

    public static void groupBy(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(12, "Eustaquio", 18));
        personas.add(new Persona(13, "Anastasia", 26));

        // AGRUPO LOS EUSTAQUIOS!!! Y MIRA COMO SE IMPRIME UN MONO!!!
        Flux.fromIterable(personas)
                .groupBy(Persona::getNombre)
                .flatMap(s -> s.collectList())
                .subscribe(p -> log.info(p.toString()));
    }

    public static void main(String[] args) {
        map();
        flatMap();
        mapWithRange();
        groupBy();
    }
}
