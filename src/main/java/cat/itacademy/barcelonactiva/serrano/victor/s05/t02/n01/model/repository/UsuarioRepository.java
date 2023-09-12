package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;

//@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);

}

