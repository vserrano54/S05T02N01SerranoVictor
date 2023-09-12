package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="tbl_partida")
public class Partida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id_partida", columnDefinition = "LONG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

	public Partida() {
		
	}

	public Partida(Long id, @NotEmpty LocalDate fecha) {
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
		return "Partida [id=" + id + ", fecha=" + fecha + "]";
	}
	
}
