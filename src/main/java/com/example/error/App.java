package com.example.error;

import com.example.reactiveappdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void gestionError() {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 30));
        personas_1.add(new Persona(2, "Fidel", 18));
        personas_1.add(new Persona(3, "Anastasia", 26));

        Flux.fromIterable(personas_1)
                .concatWith(Flux.error(new RuntimeException("ERROR!!!")))
                .retry(1)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }

    public static void errorReturn() {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 30));
        personas_1.add(new Persona(2, "Fidel", 18));
        personas_1.add(new Persona(3, "Anastasia", 26));

        Flux.fromIterable(personas_1)
                .concatWith(Flux.error(new RuntimeException("ERROR!!!")))
                .onErrorReturn(new Persona(0, "xxx", 00))
                // .doOnNext(x -> log.info(x.toString())) --- seria lo mismo!
                .subscribe(x -> log.info(x.toString()));
    }

    public static void errorResume() {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 30));
        personas_1.add(new Persona(2, "Fidel", 18));
        personas_1.add(new Persona(3, "Anastasia", 26));

        Flux.fromIterable(personas_1)
                .concatWith(Flux.error(new RuntimeException("ERROR!!!")))
                .onErrorResume(e -> Mono.just(new Persona(0, "xxx", 00)))
                // .onErrorReturn(new Persona(0, "xxx", 00))
                // .doOnNext(x -> log.info(x.toString())) --- seria lo mismo!
                .subscribe(x -> log.info(x.toString()));
    }

    public static void errorMap() {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 30));
        personas_1.add(new Persona(2, "Fidel", 18));
        personas_1.add(new Persona(3, "Anastasia", 26));

        Flux.fromIterable(personas_1)
                .concatWith(Flux.error(new RuntimeException("ERROR!!!")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> log.info(x.toString()));
    }

    public static void main(String[] args) {
        // gestionError();
        // errorReturn();
        // errorResume();
        errorMap();
    }

}
