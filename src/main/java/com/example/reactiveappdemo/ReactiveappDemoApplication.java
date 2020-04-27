package com.example.reactiveappdemo;


import com.example.reactiveappdemo.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class ReactiveappDemoApplication implements CommandLineRunner {

	static final Logger logger = LoggerFactory.getLogger(ReactiveappDemoApplication.class.getName());

	public void reactor(){
		Mono.just(new Persona(1, "Alan", 31))
				.subscribe(p -> logger.info("LOGGER_REACTOR: " + p));
	}

	public void rxJava2(){
		Observable.just(new Persona(2, "Marion", 27))
				.subscribe(p -> logger.info("LOGGER_RXJAVA2: " + p));
	}

	public static void main(String[] args) {
		logger.info("method main");
		SpringApplication.run(ReactiveappDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxJava2();
	}
}
