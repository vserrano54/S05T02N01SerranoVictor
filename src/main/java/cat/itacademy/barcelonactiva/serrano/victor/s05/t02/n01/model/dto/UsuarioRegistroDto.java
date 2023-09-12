package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class UsuarioRegistroDto {

	private Long id;
	private String username;
	private String nombre;
	private String password;
	private String fecha;
	private int enabled;

	public UsuarioRegistroDto() {

	}

	public UsuarioRegistroDto(Long id, String username, String nombre, String password, int enabled) {
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.enabled = enabled;
	}

	public UsuarioRegistroDto(String username, String nombre, String password, int enabled) {
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.enabled = enabled;
	}

	public UsuarioRegistroDto(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id= id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fechaFormateada) {
		this.fecha = fechaFormateada;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}

