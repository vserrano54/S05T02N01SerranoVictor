package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class JugadorDto {
	
	private int id;
	private String usuario;
	private String nombre;
	private LocalDate fecha;
	
	public JugadorDto() {
		super();
	}

	public JugadorDto(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}



	public JugadorDto(int id, String usuario, String nombre, LocalDate fecha) {
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "JugadorDto [id_user=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", fecha=" + fecha
				+ "]";
	}
	
	
	
	
	
	
	

}
