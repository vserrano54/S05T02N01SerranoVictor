package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.RolRepository;

@Service
public class RolServiceImp  implements IRolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Rol> findAll() {
		
		return rolRepository.findAll();
	}

	@Override
	@Transactional
	public Rol save(Rol rol) {
	
		return rolRepository.save(rol);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		rolRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Rol> findById(Long id) {
		
		return Optional.ofNullable(rolRepository.findById(id).orElse(null));
	}
	
	private Rol getRolExistente(String descipcion) {
        return rolRepository.findByDescripcion(descipcion); // Recuperar el rol existente por su nombre
    }
	
	
	

}
