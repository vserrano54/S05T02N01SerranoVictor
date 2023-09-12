package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

public class JugadorPorcentajeAciertosDto {
	
	private String jugador;
	private int partidasTotales;
	private int partidasGanadas;
	private float porcentajeAciertos;
	public JugadorPorcentajeAciertosDto() {
		
	}
	public JugadorPorcentajeAciertosDto(String jugador, int partidasTotales, int partidasGanadas,
			float porcentajeAciertos) {
		
		this.jugador = jugador;
		this.partidasTotales = partidasTotales;
		this.partidasGanadas = partidasGanadas;
		this.porcentajeAciertos = porcentajeAciertos;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public int getPartidasTotales() {
		return partidasTotales;
	}
	public void setPartidasTotales(int partidasTotales) {
		this.partidasTotales = partidasTotales;
	}
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	public float getPorcentajeAciertos() {
		return porcentajeAciertos;
	}
	public void setPorcentajeAciertos(float porcentajeAciertos) {
		this.porcentajeAciertos = porcentajeAciertos;
	}
	@Override
	public String toString() {
		return "JugadorPorcentajeAciertosDto [jugador=" + jugador + ", partidasTotales=" + partidasTotales
				+ ", partidasGanadas=" + partidasGanadas + ", porcentajeAciertos=" + porcentajeAciertos + "]";
	}
	
	
	
	
	
	

}
