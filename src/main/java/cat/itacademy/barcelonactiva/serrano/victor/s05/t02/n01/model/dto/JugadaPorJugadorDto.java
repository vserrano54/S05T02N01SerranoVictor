package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

import java.time.LocalDate;

public class JugadaPorJugadorDto {
	
	private int iduser;
	private int id;
	private String jugador;
	private LocalDate fecha;
	private int Dado1;
	private int Dado2;
	private String resultado;
	
	public JugadaPorJugadorDto() {
		
	}
	
	

	public JugadaPorJugadorDto(int iduser, int id, String jugador, LocalDate fecha, int dado1, int dado2, String resultado) {
		this.iduser= iduser;
		this.id=id;
		this.jugador = jugador;
		this.fecha = fecha;
		Dado1 = dado1;
		Dado2 = dado2;
		this.resultado = resultado;
	}

	
	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getDado1() {
		return Dado1;
	}

	public void setDado1(int dado1) {
		Dado1 = dado1;
	}

	public int getDado2() {
		return Dado2;
	}

	public void setDado2(int dado2) {
		Dado2 = dado2;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "JugadaPorJugadorDto [iduser="  + id + " ,id= " + id + ", jugador=" + jugador + ", fecha=" + fecha + ", Dado1=" + Dado1 + ", Dado2=" + Dado2
				+ ", resultado=" + resultado + "]";
	}
	
	
	
	
	
}
