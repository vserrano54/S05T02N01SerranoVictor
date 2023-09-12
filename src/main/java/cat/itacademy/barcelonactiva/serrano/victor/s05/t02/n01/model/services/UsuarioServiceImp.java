package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.excepciones.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadaPorJugadorDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadorPorcentajeAciertosDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.RankinDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioRegistroDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.RolRepository;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private ModelMapper modelMapper;

	private UsuarioRepository usuarioRepository;
	private RolRepository rolRepository;
	private BCryptPasswordEncoder passwordEncoder;

	private final JdbcTemplate jdbcTemplate;

	private final SimpleJdbcCall jdbcCall1;
	private final SimpleJdbcCall jdbcCall2;
	private final SimpleJdbcCall jdbcCall3;
	private final SimpleJdbcCall jdbcCall4;

	@Autowired
	public UsuarioServiceImp(UsuarioRepository usuarioRepository, RolRepository rolRepository,
			BCryptPasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
		this.jdbcTemplate = jdbcTemplate;

		this.jdbcCall1 = new SimpleJdbcCall(jdbcTemplate).withCatalogName("juegodb")
				.withProcedureName("getListarUsuarios").declareParameters(new SqlParameter("idrol", Types.INTEGER));

		this.jdbcCall2 = new SimpleJdbcCall(jdbcTemplate).withCatalogName("juegodb")
				.withProcedureName("lista_porcentaje_logrado");
		

		this.jdbcCall3 = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("juegodb")
				.withProcedureName("lista_jugadas_por_jugador")
				.declareParameters(
			            new SqlParameter("idrol", Types.INTEGER),
			            new SqlParameter("IDUSER", Types.INTEGER)
			     );
		
		this.jdbcCall4 = new SimpleJdbcCall(jdbcTemplate).withCatalogName("juegodb")
				.withProcedureName("lista_rankin_por_jugador").declareParameters(new SqlParameter("idrol", Types.INTEGER));

	}

	@Override
	public Usuario save(UsuarioRegistroDto registroDto) {
		Usuario usuario = new Usuario(registroDto.getUsername(), registroDto.getNombre(),
				passwordEncoder.encode(registroDto.getPassword()),
				Arrays.asList(rolRepository.findByDescripcion("ROLE_JUGADOR")));

		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public UsuarioDto save(UsuarioDto usuaroDto) {

		Usuario usuario = usuarioRepository.findById(usuaroDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuaroDto.getId()));

		usuario.setUsuario(usuaroDto.getUsername());
		usuario.setNombre(usuaroDto.getNombre());

		Usuario nuevoUsuario = usuarioRepository.save(usuario);

		UsuarioDto usuariodto = mapearDTO(nuevoUsuario);

		return usuariodto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}

		return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String user) {
		return usuarioRepository.findByUsername(user);
	}

	/******************************************************************************************************************/
	@Override
	public List<UsuarioDto> getAllUserByRolSp(int idrol) {
		MapSqlParameterSource inParams = new MapSqlParameterSource().addValue("idrol", idrol);

		Map<String, Object> result = jdbcCall1.execute(inParams);
		UsuarioDto usuario;
		List<UsuarioDto> usuarios = new ArrayList<>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

		for (Map<String, Object> row : resultSet) {
			int id = (int) row.get("id");
			String username = (String) row.get("username");
			String nombre = (String) row.get("nombre");

			/*
			 * Date date = (Date) row.get("fecha"); LocalDate fecha = date.toLocalDate();
			 */

			Timestamp timestamp = (Timestamp) row.get("fecha");
			LocalDate fecha = timestamp.toLocalDateTime().toLocalDate();

			usuario = new UsuarioDto((long) id, username, nombre, fecha);
			usuarios.add(usuario);

		}
		return usuarios;

	}
	
	@Override
	public List<JugadorPorcentajeAciertosDto> getAllUserPorcentajeLogrado(){
		
		return (List<JugadorPorcentajeAciertosDto>) jdbcTemplate.query (
				"call lista_porcentaje_logrado()", 
				new BeanPropertyRowMapper<JugadorPorcentajeAciertosDto>(JugadorPorcentajeAciertosDto.class)
				);
		
		
	}
	
	@Override
	public List<JugadaPorJugadorDto> getAllJugadasPorJugador(int idrol, int iduser){
		 MapSqlParameterSource inParams = new MapSqlParameterSource()
		            .addValue("idrol", idrol)
		            .addValue("IDUSER", iduser);
      
      Map<String, Object> result = jdbcCall3.execute(inParams);
      JugadaPorJugadorDto jugador;
      List<JugadaPorJugadorDto> jugadores=new ArrayList<>();
      List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

      for (Map<String, Object> row : resultSet) {
    	  
    	  int id = (int) row.get("id");
          String usuario = (String) row.get("nombre");
          
          Date date = (Date) row.get("fecha");
          LocalDate fecha = date.toLocalDate();
          
         // Timestamp timestamp = (Timestamp) row.get("fecha");
         // LocalDate fecha = timestamp.toLocalDateTime().toLocalDate();
          
          
          int dado1 = (int) row.get("dado1");
          int dado2 = (int) row.get("dado2");
          
          String resultado = (String) row.get("resultado");
          
          //LocalDate ahora = LocalDate.now();
          
          
          
          jugador = new JugadaPorJugadorDto(iduser,id,usuario,fecha,dado1,dado2,resultado);
          
          System.out.println("Desde el controlador: " + jugador);
          jugadores.add(jugador);
    
      }
      
      return jugadores;
	}
	
	@Override
	public List<RankinDto> getAllRankinPorUsuario(int idrol){
		System.out.println("Enttra al servicio");
		
		MapSqlParameterSource inParams = new MapSqlParameterSource().addValue("idrol", idrol);

		Map<String, Object> result = jdbcCall4.execute(inParams);
		RankinDto rankin;
		List<RankinDto> rankins = new ArrayList<>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

		for (Map<String, Object> row : resultSet) {
		
			String jugador = (String) row.get("Jugador");
			System.out.println(row.get("rankin"));
			double ranki= (double) row.get("rankin");


			rankin = new RankinDto(jugador, ranki);
			rankins.add(rankin);

		}
		return rankins;
		
		
	}
	
	public JugadorPorcentajeAciertosDto getPeorPorcentajeLogrado() {
	    List<JugadorPorcentajeAciertosDto> jugadores = jdbcTemplate.query(
	            "CALL peor_porcentaje_logrado()",
	            new BeanPropertyRowMapper<>(JugadorPorcentajeAciertosDto.class)
	    );

	    if (!jugadores.isEmpty()) {
	        return jugadores.get(0);
	    } else {
	        return null; // O cualquier valor por defecto que desees si no se encuentra ningún registro
	    }
	}
	
	public RankinDto getPeorRankinLogrado() {
		
		  List<RankinDto> jugadores = jdbcTemplate.query(
		            "CALL peor_rankin_logrado()",
		            new BeanPropertyRowMapper<>(RankinDto.class)
		    );

		    if (!jugadores.isEmpty()) {
		    	
		        return jugadores.get(0);
		    } else {
		        return null; // O cualquier valor por defecto que desees si no se encuentra ningún registro
		    }
		
	}
	
	/*

	@Override
	public List<JugadorPorcentajeAciertosDto> getAllUserPorcentajeLogrado() {
		
		System.out.println("ENTRA EL SERVICIO IMP");
		Map<String, Object> result = jdbcCall2.execute();
		JugadorPorcentajeAciertosDto usuario;
		List<JugadorPorcentajeAciertosDto> usuarios = new ArrayList<>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

		for (Map<String, Object> row : resultSet) {
			System.out.println("ENTRO AL FOR");
			String nombre = (String) row.get("nombre");
			System.out.println(nombre);
			System.out.println(row.get("PARTIDAS_TOTALES"));
			
			Long longValue1 = (Long) row.get("PARTIDAS_TOTALES");
			int partidasTotales = longValue1.intValue();
			
			Long longValue2 = (Long) row.get("PARTIDAS_GANADAS");
			int partidasGanadas = longValue2.intValue();
			
			
			long porcentajeAciertos = (long) row.get("PORCENTAJE_ACIERTOS");
			

			usuario = new JugadorPorcentajeAciertosDto(nombre, partidasTotales, partidasGanadas, porcentajeAciertos);
			System.out.println("Porcentaje logrados: " + usuario.toString());
			
			usuarios.add(usuario);
			System.out.println("Porcentaje logrados: " + usuarios.toString());

		}
		System.out.println("Porcentaje logrados: " + usuarios.toString());
		
		return usuarios;

	}
	*/

	private UsuarioDto mapearDTO(Usuario usuario) {

		UsuarioDto usuariodto = modelMapper.map(usuario, UsuarioDto.class);

		return usuariodto;
	}

	private Usuario mapearEntidad(UsuarioDto usuariodto) {
		Usuario usuario = modelMapper.map(usuariodto, Usuario.class);

		return usuario;
	}

	
}
