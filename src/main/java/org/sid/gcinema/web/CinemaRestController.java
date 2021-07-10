package org.sid.gcinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.sid.gcinema.dao.FilmRepository;
import org.sid.gcinema.dao.TicketRepository;
import org.sid.gcinema.entities.Film;
import org.sid.gcinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
	@Autowired	
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name = "id") Long id) throws Exception {
		Film f=filmRepository.findById(id).get();
		String photoName=f.getPhoto();
		String theAbsolutePath=Paths.get(photoName).toAbsolutePath().toString().replace(photoName, "image/"+photoName);
		File file= new File(theAbsolutePath);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	/*@PostMapping("/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketFrom ticketFrom) {
		List<Ticket> listTickets=new ArrayList<>();
		ticketFrom.getTickets().forEach(idTicket->{
			Ticket ticket=ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketFrom.getNomClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketFrom.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}*/
	
	@PostMapping(path = "/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketFrom ticketFrom){
		List<Ticket> tickets = new ArrayList<Ticket>();
		ticketFrom.getTickets().forEach(idTicket->{
			Ticket ticket = ticketRepository.findById(idTicket).get();
			ticket.setReserve(true);
			ticket.setNomClient(ticketFrom.getNomClient());
			ticket.setCodePayement(ticketFrom.getCodePayement());
			ticketRepository.save(ticket);
			tickets.add(ticket);
		});
		return tickets;
	}
	
}

@Data
class TicketFrom{
	private String nomClient;
	private int codePayement;
	private List<Long> tickets= new ArrayList<>();
}