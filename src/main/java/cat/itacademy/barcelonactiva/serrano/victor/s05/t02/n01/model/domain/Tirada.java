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
@Table(name="tbl_tirada")
public class Tirada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id_tirada", columnDefinition = "LONG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "dado1", columnDefinition = "INT")
	private int dado1;
	
	@NotEmpty
	@Column(name = "dado2", columnDefinition = "INT")
	private int dado2;

	public Tirada() {
		
	}

	public Tirada(Long id, @NotEmpty int dado1, @NotEmpty int dado2) {
		super();
		this.id = id;
		this.dado1 = dado1;
		this.dado2 = dado2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Tirada [id=" + id + ", dado1=" + dado1 + ", dado2=" + dado2 + "]";
	}

}
