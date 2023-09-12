package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

public class RolDto {
	
	private Long id;
	private String descripcion;
	
	public RolDto() {
	
	}
	
	

	public RolDto(String descripcion) {
		this.descripcion = descripcion;
	}



	public RolDto(Long id, String descripcion) {
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
		return "RolDto [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	
	
	

}
