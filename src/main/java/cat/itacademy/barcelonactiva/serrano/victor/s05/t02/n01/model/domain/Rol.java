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
@Table(name = "tbl_rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_rol", columnDefinition = "LONG")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "descripcion", columnDefinition = "VARCHAR(45)")
    private String descripcion;

	public Rol() {
		
	}

	public Rol(@NotEmpty String descripcion) {
		
		this.descripcion = descripcion;
	}

	public Rol(Long id, @NotEmpty String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", descripcion=" + descripcion + "]";
	} 

}
