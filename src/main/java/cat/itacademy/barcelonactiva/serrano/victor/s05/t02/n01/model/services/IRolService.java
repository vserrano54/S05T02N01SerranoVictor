package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.Optional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;


public interface IRolService {
	
	public Iterable<Rol> findAll();
	
	public Rol save(Rol rol);
	
	public void deleteById(Long id);
	
	public Optional<Rol> findById(Long id);
	
	


}
