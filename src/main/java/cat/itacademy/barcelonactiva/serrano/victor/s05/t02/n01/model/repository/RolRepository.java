package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

	Rol findByDescripcion(String descripcion);

	

}
