package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.List;
import java.util.Optional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.PartidaDto;


public interface IPartidaService {
	
	public List<PartidaDto> findAll();
	
	public Partida save(Partida partida);
	
	public Optional<Partida> findById(int id);

}
