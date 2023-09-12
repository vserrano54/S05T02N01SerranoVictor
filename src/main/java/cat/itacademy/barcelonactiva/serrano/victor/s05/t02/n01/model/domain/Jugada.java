package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="tbl_jugada")
public class Jugada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id_jugada", columnDefinition = "LONG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "id_partida", columnDefinition = "LONG")
	private Long partida;
	
	@NotEmpty
	@Column(name = "id_user", columnDefinition = "LONG")
	private Long jugador;
	
	@NotEmpty
	@Column(name = "id_tirada", columnDefinition = "LONG")
	private Long tirada;

	public Jugada() {
		
	}

	public Jugada(Long id, @NotEmpty Long partida, @NotEmpty Long jugador, @NotEmpty Long tirada) {
		this.id = id;
		this.partida = partida;
		this.jugador = jugador;
		this.tirada = tirada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartida() {
		return partida;
	}

	public void setPartida(Long partida) {
		this.partida = partida;
	}

	public Long getJugador() {
		return jugador;
	}

	public void setJugador(Long jugador) {
		this.jugador = jugador;
	}

	public Long getTirada() {
		return tirada;
	}

	public void setTirada(Long tirada) {
		this.tirada = tirada;
	}

	@Override
	public String toString() {
		return "Jugada [id=" + id + ", partida=" + partida + ", jugador=" + jugador + ", tirada=" + tirada + "]";
	}
	
	
	
	

	

}
