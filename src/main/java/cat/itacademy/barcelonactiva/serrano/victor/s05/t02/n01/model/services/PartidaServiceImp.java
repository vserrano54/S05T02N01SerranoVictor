package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.PartidaDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.PartidaRepository;

@Service
public class PartidaServiceImp implements IPartidaService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<PartidaDto> findAll(){
		
		List<Partida> partidas = partidaRepository.findAll();
		
		return partidas.stream().map(partida -> mapearDTO(partida)).collect(Collectors.toList());
		
		
	}
	
	@Transactional
	@Override
	public Partida save(Partida partida) {
		
		LocalDate fecha = LocalDate.now();
		
		partida.setFecha(fecha);
		
		return partidaRepository.save(partida);
		
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Partida> findById(int id){
		
		return partidaRepository.findById((long) id);
		
	}
	
	private PartidaDto mapearDTO(Partida partida) {
		
		PartidaDto partidadto= modelMapper.map(partida, PartidaDto.class);
	
		
		return partidadto;
	}
	
	private Partida mapearEntidad(PartidaDto partidadto) {
		Partida partida= modelMapper.map(partidadto, Partida.class);
		
		return partida;
	}

}
