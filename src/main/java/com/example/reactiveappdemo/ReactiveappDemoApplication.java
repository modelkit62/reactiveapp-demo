package com.example.reactiveappdemo;


import com.example.reactiveappdemo.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ReactiveappDemoApplication implements CommandLineRunner {

	static final Logger logger = LoggerFactory.getLogger(ReactiveappDemoApplication.class.getName());

	public void reactor(){
		Mono.just(new Persona(1, "Alan", 31))
				.subscribe(p -> logger.info("LOGGER_REACTOR: " + p));
	}

	public void rxJava2(){
		Observable.just(new Persona(2, "Marion", 27))
				// con doOnNext tambien hago lo mismo, es como el metodo peak(), pero necesito el subscribe!
				// necesito llaves si tengo mas de una linea!
				.doOnNext(p -> {
					logger.info("LOGGER_RXJAVA2: " + p);
				})
				.subscribe(p -> logger.info("LOGGER_RXJAVA2: " + p));
	}

	private void mono() {
		Mono.just(new Persona(3, "Karen", 43)).subscribe(s -> logger.info(s.toString()));
	}

	private void flux() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(4, "Matias", 28));
		personas.add(new Persona(5, "Susana", 38));
		personas.add(new Persona(6, "Michael", 23));
		Flux.fromIterable(personas).subscribe(p -> logger.info(p.toString()));
	}

	private void fromFluxToMono() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(7, "Sharon", 19));
		personas.add(new Persona(8, "Rick", 52));
		personas.add(new Persona(9, "Dan", 40));
		Flux<Persona> personaFlux = Flux.fromIterable(personas);
		personaFlux.collectList().subscribe(p -> logger.info(p.toString()));
	}

	@Override
	public void run(String... args) throws Exception {
		// reactor();
		// rxJava2();
		mono();
		flux();
		fromFluxToMono();
	}

	public static void main(String[] args) {
		logger.info("method main");
		SpringApplication.run(ReactiveappDemoApplication.class, args);
	}
}
