package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadaPorJugadorDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadorDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadorPorcentajeAciertosDto;

@Repository
public class ProcedureRepository {
	
	//@Autowired
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcCall jdbcCall1;
	private final SimpleJdbcCall jdbcCall2;
	private final SimpleJdbcCall jdbcCall3;
	
	final String STORED_PRECEDURE2 = "call lista_porcentaje_logrado()";
	
	public ProcedureRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
		this.jdbcCall1 = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("juegodb")
				.withProcedureName("lista_usuario")
				.declareParameters(
			            new SqlParameter("idrol", Types.INTEGER)
			     );
				
		
		this.jdbcCall2=new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(STORED_PRECEDURE2);
		
		
		this.jdbcCall3 = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("juegodb")
				.withProcedureName("lista_jugadas_por_jugador")
				.declareParameters(
			            new SqlParameter("idrol", Types.INTEGER),
			            new SqlParameter("IDUSER", Types.INTEGER)
			     );
				
	}
	
	
	public List<JugadorDto> mostrarJugadoresSp(int idrol){
		 MapSqlParameterSource inParams = new MapSqlParameterSource()
		            .addValue("idrol", idrol);
        
        Map<String, Object> result = jdbcCall1.execute(inParams);
        @SuppressWarnings("unchecked")
        
        JugadorDto jugador;
        List<JugadorDto> jugadores=new ArrayList<>();
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

        for (Map<String, Object> row : resultSet) {
            int id = (int) row.get("id");
            String usuario = (String) row.get("usuario");
            String nombre = (String) row.get("nombre");
            
            Timestamp timestamp = (Timestamp) row.get("fecha");
            LocalDate fecha = timestamp.toLocalDateTime().toLocalDate();
            
            jugador = new JugadorDto(id,usuario,nombre,fecha);
            jugadores.add(jugador);
      
        }
       
       // System.out.println(jugadores.toString());
        return jugadores;
		
	}
	
	public List<JugadorPorcentajeAciertosDto> mostrarListaPorsentajeLogradoSp(){
		
		return (List<JugadorPorcentajeAciertosDto>) jdbcTemplate.query (
				STORED_PRECEDURE2, 
				new BeanPropertyRowMapper<JugadorPorcentajeAciertosDto>(JugadorPorcentajeAciertosDto.class)
				);
		
	}
	
	public List<JugadaPorJugadorDto> mostrarJugadasPorJugadorSp(int idrol, int iduser){
		 MapSqlParameterSource inParams = new MapSqlParameterSource()
		            .addValue("idrol", idrol)
		            .addValue("iduser", iduser);
       
       Map<String, Object> result = jdbcCall3.execute(inParams);
       JugadaPorJugadorDto jugador;
       List<JugadaPorJugadorDto> jugadores=new ArrayList<>();
       List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

       for (Map<String, Object> row : resultSet) {
    	   int id = (int) row.get("id");
           String usuario = (String) row.get("jugador");
           
           Date date = (Date) row.get("fecha");
           LocalDate fecha = date.toLocalDate();
           
          // Timestamp timestamp = (Timestamp) row.get("fecha");
          // LocalDate fecha = timestamp.toLocalDateTime().toLocalDate();
           
           
           int dado1 = (int) row.get("dado1");
           int dado2 = (int) row.get("dado2");
           
           String resultado = (String) row.get("resultado");
           
           //LocalDate ahora = LocalDate.now();
           
           
           
           jugador = new JugadaPorJugadorDto(iduser,id,usuario,fecha,dado1,dado2,resultado);
           jugadores.add(jugador);
     
       }
       
       System.out.println("Entro a mostrarJugadas por jugador");
       
       return jugadores;
		
	}
	
	
	public List<JugadorDto> comboJugadoresSp(int idrol){
		 MapSqlParameterSource inParams = new MapSqlParameterSource()
		            .addValue("idrol", idrol);
       
       Map<String, Object> result = jdbcCall1.execute(inParams);
       @SuppressWarnings("unchecked")
       
       JugadorDto jugador;
       List<JugadorDto> jugadores=new ArrayList<>();
       List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

       for (Map<String, Object> row : resultSet) {
           int id = (int) row.get("id");
           String nombre = (String) row.get("nombre");
          
           
           jugador = new JugadorDto(id,nombre);
           jugadores.add(jugador);
     
       }
       
       return jugadores;
		
	}
	
	

}
