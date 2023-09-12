package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto;

public class RankinDto {
	
	private String nombre;
	private double rankin;
	
	public RankinDto() {
		super();
	}

	public RankinDto(String nombre, double rankin) {
		this.nombre = nombre;
		this.rankin = rankin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getRankin() {
		return rankin;
	}

	public void setRankin(double rankin) {
		this.rankin = rankin;
	}

	@Override
	public String toString() {
		return "RankinDto [nombre=" + nombre + ", rankin=" + rankin + "]";
	}
	
	
}
