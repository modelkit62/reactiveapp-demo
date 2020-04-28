package com.example.combinacion;

import com.example.reactiveappdemo.model.Persona;
import com.example.reactiveappdemo.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void merge(){
        List<Persona> personas_1 = new ArrayList<>();
        personas_1.add(new Persona(1, "Eustaquio", 30));
        personas_1.add(new Persona(2, "Fidel", 18));
        personas_1.add(new Persona(3, "Anastasia", 26));

        List<Persona> personas_2 = new ArrayList<>();
        personas_2.add(new Persona(4, "Eustaquio", 30));
        personas_2.add(new Persona(5, "Fidel", 18));
        personas_2.add(new Persona(6, "Anastasia", 26));

        Flux<Persona> personaFlux_1 = Flux.fromIterable(personas_1);
        Flux<Persona> personaFlux_2 = Flux.fromIterable(personas_2);

        // Ahora meto venta

        Venta venta = new Venta(1, LocalDate.now());
        // Mono.just(venta);

        List<Venta> ventas = new ArrayList<>();
        ventas.add(venta);
        Flux<Venta> ventaFlux = Flux.fromIterable(ventas);

        Flux.merge(personaFlux_1, personaFlux_2, ventaFlux)
                // .filter(p -> p.getId() == 5) -- NO FUNCIONA, SON OBJETOS DIFERENTES AUNQUE SI QUE LOS PUEDE IMPRIMIR!!!
                .subscribe(p -> log.info(p.toString()));

        log.info("SEPARACION!!!");

        // Mira con ZIP, los concatena!!!
        Flux.zip(personaFlux_1,personaFlux_2,ventaFlux)
                .subscribe(p -> log.info(
                        p.toString()
                ));
        /* [Persona{id=1, nombre='Eustaquio', edad=30},Persona{id=4, nombre='Eustaquio', edad=30},Venta{id=1, date=2020-04-28}] */
    }

    public static void main(String[] args) {
        merge();
    }
}
