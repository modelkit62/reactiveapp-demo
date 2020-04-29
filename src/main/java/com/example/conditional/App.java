package com.example.conditional;

import com.example.reactiveappdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void defaultIfEmpty() {
        Mono.just(new Persona(1, "zzz", 01))
        // Mono.empty()
        // Flux.empty()
                // si recibo esta no es necesario el opcional por si falla, no entra aqui
                .defaultIfEmpty(new Persona(0, "xxx", 00))
                .subscribe(x -> log.info(x.toString()));
    }

    public static void takeUntil() {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 18));
        personas_1.add(new Persona(2, "Fidel", 30));
        personas_1.add(new Persona(3, "Anastasia", 26));

        // ejecuta hasta la primera vez que se cumple que sea mayor a 27!!!
        Flux.fromIterable(personas_1)
                .takeUntil(x -> x.getEdad() > 27)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }

    public static void timeOut() throws InterruptedException {
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 18));
        personas_1.add(new Persona(2, "Fidel", 30));
        personas_1.add(new Persona(3, "Anastasia", 26));

        // ejecuta hasta la primera vez que se cumple que sea mayor a 27!!!
        Flux.fromIterable(personas_1)
                .delayElements(Duration.ofSeconds(1)) // se demora 1 seg
                .timeout(Duration.ofSeconds(2)) // ESTA PERMITIDO HASTA 2 SEGUNDOS
                .subscribe(x -> log.info(x.toString()));

        Thread.sleep(1000); // LO DEMORO 1 SEGUNDO, LLEGA AL LIMITE, NO HAY TIMEOUT!
    }

    public static void main(String[] args) throws InterruptedException {
        // defaultIfEmpty();
        // takeUntil();
        timeOut();
    }



}
