package org.sid.gcinema;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.sid.gcinema.entities.Film;
import org.sid.gcinema.entities.Salle;
import org.sid.gcinema.entities.Ticket;
import org.sid.gcinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GetionCinemaApplication implements CommandLineRunner {
	@Autowired
	private ICinemaInitService cinemaInitService;
	
	@Autowired
	private RepositoryRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(GetionCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class,Salle.class,Ticket.class);
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
		
		Path path = Paths.get("Catwoman.jpg");
		System.out.println(path.toAbsolutePath());
		System.out.println(path.getRoot());
		System.out.println(path.getParent());
		System.out.println(path.getFileSystem());
		System.out.println(15+(int)(Math.random() * 2));
		
		
		
	}

}
