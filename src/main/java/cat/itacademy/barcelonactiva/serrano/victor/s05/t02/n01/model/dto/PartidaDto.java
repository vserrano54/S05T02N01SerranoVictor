package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class PartidaDto {
	
	private Long id;
	
	private LocalDate fecha;

	public PartidaDto() {
		
	}

	public PartidaDto(Long id, LocalDate fecha) {
		this.id = id;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "PartidaDto [id=" + id + ", fecha=" + fecha + "]";
	}
	
	
	
	
}
