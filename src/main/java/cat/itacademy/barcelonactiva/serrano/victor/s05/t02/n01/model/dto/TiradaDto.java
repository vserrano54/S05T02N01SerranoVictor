package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class TiradaDto {
	private Long id;
	private LocalDate fecha;
	private String usuario;
	private int dado1;
	private int dado2;
	
	public TiradaDto() {
		
	}

	public TiradaDto(Long id, LocalDate fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}

	public TiradaDto(Long id, LocalDate fecha, String usuario, int dado1, int dado2) {
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.dado1 = dado1;
		this.dado2 = dado2;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getDado1() {
		return dado1;
	}

	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}

	public int getDado2() {
		return dado2;
	}

	public void setDado2(int dado2) {
		this.dado2 = dado2;
	}

	@Override
	public String toString() {
		return "TiradaDto [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", dado1=" + dado1 + ", dado2="
				+ dado2 + "]";
	}
	
	

	

	
	
	
	

}
