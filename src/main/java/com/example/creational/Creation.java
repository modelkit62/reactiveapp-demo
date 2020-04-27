package com.example.creational;

import com.example.reactiveappdemo.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class Creation {

    static final Logger log = LoggerFactory.getLogger(Creation.class);

    public void fromJust(){
        Mono.just(new Persona(10, "Marcos", 32));
        Flux.fromIterable(new ArrayList<>());
    }

    public void fromEmpty(){
        Mono<Object> empty = Mono.empty();
        Flux<Object> empty1 = Flux.empty();
        Observable<Object> empty2 = Observable.empty();
    }

    public static Flux<Integer> range(){
        return Flux.range(1, 3);
    }

    public static void main(String[] args) {
        // me lo traigo por return, lo repito una vez!
        log.info("RESULTADO " + range().collectList());
        Flux<Integer> range = range();
        range.doOnNext(i -> log.info( "i: " + i)).repeat(1).subscribe();

    }


}
