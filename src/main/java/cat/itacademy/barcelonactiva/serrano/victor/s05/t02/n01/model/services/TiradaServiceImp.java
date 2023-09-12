package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Tirada;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.TiradaRepository;

@Service
public class TiradaServiceImp implements ITiradaService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TiradaRepository tiradaRepository;
	
	
	@Override
	@Transactional
	public Tirada save(Tirada tirada) {
	
		return tiradaRepository.save(tirada);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		
		tiradaRepository.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Tirada> findById(Long id) {
		
		return Optional.ofNullable(tiradaRepository.findById(id).orElse(null));
	}
	
	@Override
	@Transactional
	public void deleteByIdWithForeignKeyCheck(Long id) {
		
		 // Deshabilitar temporalmente las restricciones de clave foránea
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

        // Ejecutar la eliminación del registro
        jdbcTemplate.update("DELETE FROM `tbl_tirada` WHERE `ID_TIRADA` = ?", id);

        // Volver a habilitar las restricciones de clave foránea
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
		
		
	}
	

}
