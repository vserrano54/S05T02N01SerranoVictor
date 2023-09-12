package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Jugada;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.JugadaRepository;

@Service
public class JugadaServiceImp implements IJugadaService{
	
	@Autowired
	private JugadaRepository jugadaRepository;
	
	@Override
	@Transactional
	public Jugada save(Jugada jugada) {
	
		return jugadaRepository.save(jugada);
	}

}
