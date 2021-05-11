package fr.aprr.formationjavaavance;

import fr.aprr.formationjavaavance.services.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FormationjavaavanceApplication implements CommandLineRunner {

	@Autowired
	private MainService service;

	public static void main(String[] args) {
		SpringApplication.run(FormationjavaavanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start workflow");
		log.info(args[0]);
		service.setOutCharset("Cp1252");
		service.workflow("data/export.csv", "data/output.csv");
		log.info("End workflow");

	}
}
