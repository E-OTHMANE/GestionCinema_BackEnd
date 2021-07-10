package org.sid.gcinema.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = {org.sid.gcinema.entities.Projection.class})
public interface ProjectionProj {

	public Long getId();
	public double getPrix();
	public Date getDateProjection();
	public Film getFilm();
	public Salle getSalle();
	public Seance getSeance();
	public List<Ticket> getTickets();
}
