package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_user", columnDefinition = "LONG")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username", columnDefinition = "VARCHAR(45)")
    private String username;

    @NotEmpty
    @Column(name = "nombre", columnDefinition = "VARCHAR(80)")
    private String nombre;

    @NotEmpty
    @Column(name = "password", columnDefinition = "VARCHAR(128)")
    private String password;
    
    @NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate  fecha;

    @NotEmpty
    @Column(name = "enabled", columnDefinition = "TINYINT")
    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "tbl_user_rol",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    )
    private Collection<Rol> roles;

    public Usuario() {}
    
    
    
	public Usuario(Long id, @NotEmpty String username, @NotEmpty String nombre, @NotEmpty String password,
			@NotEmpty int enabled, Collection<Rol> roles) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}



	public Usuario(@NotEmpty String username, @NotEmpty String nombre, @NotEmpty String password,
			Collection<Rol>  roles) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.enabled=1;
		this.fecha = LocalDate.now();
		this.roles = roles;
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



	public void setUsuario(String username) {
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

	
	

	public @NotEmpty LocalDate getFecha() {
		return fecha;
	}



	public void setFecha(@NotEmpty LocalDate fecha) {
		this.fecha = fecha;
	}



	public int getEnabled() {
		return enabled;
	}



	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}



	public Collection<Rol> getRoles() {
		return roles;
	}



	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}



	@Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", fecha=" + fecha + ",  enabled="
            + enabled + ", roles=" + roles + "]";
    }
}
