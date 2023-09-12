package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.Optional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Tirada;

public interface ITiradaService {
	
	public Tirada save(Tirada tirada);
	
	public void deleteById(Long id);
	
	public void deleteByIdWithForeignKeyCheck(Long id);
	
	public Optional<Tirada> findById(Long id);

}
