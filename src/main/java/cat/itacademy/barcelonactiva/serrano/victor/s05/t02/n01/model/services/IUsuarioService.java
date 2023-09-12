package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;


import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadaPorJugadorDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadorPorcentajeAciertosDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.RankinDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioRegistroDto;

public interface IUsuarioService extends UserDetailsService{
	
	
	//public Usuario save(UsuarioRegistroDto registroDto);
	public Usuario save(UsuarioRegistroDto registroDto);
	
	public UsuarioDto save(UsuarioDto usuariodto);
	
	public List<UsuarioDto> getAllUserByRolSp(int idrol);
	
	public List<JugadorPorcentajeAciertosDto> getAllUserPorcentajeLogrado();
	
	public List<JugadaPorJugadorDto> getAllJugadasPorJugador(int idrol, int iduser);
	
	public List<RankinDto> getAllRankinPorUsuario(int idrol);
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario findByUsername(String user);
	
	public JugadorPorcentajeAciertosDto getPeorPorcentajeLogrado();
	
	public RankinDto getPeorRankinLogrado();
	
	
	
	

	
	public List<Usuario> listarUsuarios();
	
	public void deleteById(Long id);
	
	
	
	
	
	

	
}
