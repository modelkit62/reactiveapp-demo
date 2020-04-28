package com.example.filtrado;

import com.example.reactiveappdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void filtrado(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(12, "Fidel", 18));
        personas.add(new Persona(13, "Anastasia", 26));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() >= 30)
                .subscribe(p -> log.info(p.toString()));
    }

    // implemento apachecommons3 y seteo que tienen que ser exactamente igual las 3!
    static void distinto(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(13, "Anastasia", 26));

        Flux.fromIterable(personas)
                // para que disctinct actue SOBRE EL PRIMER DATO TENGO QUE SOBREESCIBIR LOS METODOS EQUALS y HASTTAGH!!!
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }

    static void take(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(11, "Eustaquio", 30));
        personas.add(new Persona(13, "Anastasia", 26));

        // skip, take and takelast (tb pudiera elegir skipLast())
        Flux.fromIterable(personas)
                .skip(1)
                .take(2)
                .takeLast(1)
                .subscribe(p -> log.info(p.toString()));
    }

    public static void main(String[] args) {
       // filtrado();
       // distinto();
        take();
    }
}
