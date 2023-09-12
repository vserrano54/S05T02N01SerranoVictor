package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class UsuarioDto {
	
	private Long id;
	private String username;
	private String nombre;
	private LocalDate fecha;
	
	public UsuarioDto() {
		super();
	}

	public UsuarioDto(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}



	public UsuarioDto(Long id, String username, String nombre, LocalDate fecha) {
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		return "UsuarioDto [id=" + id + ", username=" + username + ", nombre=" + nombre + ", fecha=" + fecha
				+ "]";
	}
	
	
	
	
	
	
	

}
