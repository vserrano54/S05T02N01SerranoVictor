package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Tirada;

public interface TiradaRepository extends JpaRepository<Tirada, Long>  {

}
