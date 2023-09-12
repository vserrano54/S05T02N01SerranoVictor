package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;
import javax.validation.Valid;

//import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadaPorJugadorDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.JugadorPorcentajeAciertosDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.RankinDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IUsuarioService;

@Controller
//@Slf4j
@RequestMapping("/views/jugador")
public class JugadorController {
	
	@Autowired
	private IUsuarioService iUsuarioService;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/getAllUserByRol")
	public String readAll(Model model, Locale locale) {
		
		int idRol = 2;
		
		List<UsuarioDto> usuarios = StreamSupport
				.stream(iUsuarioService.getAllUserByRolSp(idRol).spliterator(), false)
				.collect(Collectors.toList());
		
		System.out.println(usuarios.toString());
		
		String titulo = messageSource.getMessage("jugador.lista.titulo", null, locale);
		model.addAttribute("jugadores",usuarios);
		model.addAttribute("titulo",titulo);
		return "/views/jugador/listajugadores";
		
	}
	
	@GetMapping("/getAllUserByPorcenjaAcierto")
	public String readAllPorcentajeAcierto(Model model, Locale locale) {
		
		List<JugadorPorcentajeAciertosDto> usuarios = StreamSupport
				.stream(iUsuarioService.getAllUserPorcentajeLogrado().spliterator(), false)
				.collect(Collectors.toList());
		
		System.out.println("Porcentaje acierto " + usuarios.toString());
		
		String titulo = messageSource.getMessage("jugador.lista.titulo.pocentaje.aciertos", null, locale);
		model.addAttribute("jugadores",usuarios);
		model.addAttribute("titulo",titulo);
		System.out.println("Antes de enviar a la vista");
		return "/views/jugador/listaporcentajeaciertos";
		
	}
	
	
	
	
	
	
	@GetMapping("/getAllJugadasPorJugador/{id}")
	public String readAllJugadaPorJugador(@PathVariable("id") int id,Model model, Locale locale) {
		
		System.out.println("entra Jugadas por Jugador");
		
		int idRol = 2;
		int idUser=id;
		
		
		List<JugadaPorJugadorDto> usuarios = StreamSupport
				.stream(iUsuarioService.getAllJugadasPorJugador(idRol,idUser).spliterator(), false)
				.collect(Collectors.toList());
		
		//System.out.println("Porcentaje acierto " + usuarios.toString());
		
		String titulo = messageSource.getMessage("jugador.lista.titulo.jugada.porjugador", null, locale);
		model.addAttribute("jugadores",usuarios);
		model.addAttribute("titulo",titulo);
		//System.out.println("Jugadas por jugador: " + usuarios);
		return "/views/jugador/listajugadasporjugador";
		
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable("id") Long id, Model model, RedirectAttributes attribute, Locale locale){
		
		Optional<Usuario> usuario=null;
		
		if (id>0)
		{
			usuario = iUsuarioService.findById(id);
			
			if (usuario.equals(null)||(usuario.equals(Optional.empty()))) {
				System.out.println("El id del usuaro no existe");
				String error = messageSource.getMessage("jugador.accion.guardar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/jugador/listajugadores";
			}
		}else {
			System.out.println("Error con el id de la sucursal");
			String error = messageSource.getMessage("jugador.accion.guardar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/jugador/listajugadores";
		}
		
		String titulo = messageSource.getMessage("jugador.editar.titulo", null, locale);
		model.addAttribute("titulo", titulo);
		model.addAttribute("jugador",usuario);
		
		System.out.println(usuario.toString());
		
		return "/views/jugador/editar";
	
	}
	
	@PostMapping("/save")
	@Transactional
	public String save(@Valid @ModelAttribute UsuarioDto usuario, Errors errores, 
				Model model,RedirectAttributes attribute, Locale locale ) {
		
		System.out.println("Entra al procedimiento save\n\n\n\n");
	
		if (errores.hasErrors()) {
			System.out.println("Entra al hasssErrors\n\n\n\n");
			String titulo = messageSource.getMessage("usuario.editar.titulo", null, locale);
			model.addAttribute("titulo", titulo);
			model.addAttribute("usuario",usuario);
			
			
			return "/views/jugador/editar";
		}
		
		System.out.println("No encontro errores\n\n\n\n");
		System.out.println(usuario.toString());
		
		iUsuarioService.save(usuario);
		//System.out.println("Sucursal guardada con exito");
		String success = messageSource.getMessage("jugador.accion.guardar.exito", null, locale);
		attribute.addFlashAttribute("success", success);
		
		return "redirect:/views/jugador/getAllUserByRol";
	}
	
	@GetMapping("/getAllUserPorRankin")
	public String readAllRankin(Model model, Locale locale) {
		System.out.println("en al control rankin");
		
		int idRol = 2;
		
		List<RankinDto> rankins = StreamSupport
				.stream(iUsuarioService.getAllRankinPorUsuario(idRol).spliterator(), false)
				.collect(Collectors.toList());
		
		System.out.println(rankins.toString());
		
		String titulo = messageSource.getMessage("jugador.lista.rankin", null, locale);
		model.addAttribute("rankins",rankins);
		model.addAttribute("titulo",titulo);
		return "/views/jugador/listarankinporusuarios";
		
	}
	
	@GetMapping("/getPeorPorcenjaAcierto")
	public String readPeorPorcentajeAcierto(Model model, Locale locale) {
		
		System.out.println("Entra al controlador peor porcentaje acierto");
		
	    JugadorPorcentajeAciertosDto usuario = iUsuarioService.getPeorPorcentajeLogrado();

	    if (usuario != null) {
	        System.out.println("Porcentaje acierto " + usuario.toString());
	        System.out.println("Entro al if");
	        String titulo = messageSource.getMessage("jugador.peorporcentaje.acierto", null, locale);
	        model.addAttribute("jugador", usuario);
	        model.addAttribute("titulo", titulo);

	        System.out.println("Antes de enviar a la vista");
	        return "/views/jugador/peorporcentaje";
	    
	        
	    } else {
	       
	    	 System.out.println("Porcentaje acierto " + usuario.toString());

		        String titulo = messageSource.getMessage("jugador.peorporcentaje.acierto", null, locale);
		        model.addAttribute("jugador", usuario);
		        model.addAttribute("titulo", titulo);

		        System.out.println("Antes de enviar a la vista");
		        return "/views/jugador/peorporcentaje";
		    
	    	
	    }
	    
	}
	
	@GetMapping("/getPeorRankin")
	public String readPeorRankin(Model model, Locale locale) {
		
		System.out.println("Entra al controlador peor rankin acierto");
		
		RankinDto usuario = iUsuarioService.getPeorRankinLogrado();

	    if (usuario != null) {
	       
	        String titulo = messageSource.getMessage("jugador.peorrankin.logrado", null, locale);
	        model.addAttribute("jugador", usuario);
	        model.addAttribute("titulo", titulo);

	      
	        return "/views/jugador/peorrankin";
	    
	        
	    } else {
	       

		        String titulo = messageSource.getMessage("jugador.peorporcentaje.acierto", null, locale);
		        model.addAttribute("jugador", usuario);
		        model.addAttribute("titulo", titulo);

		     
		        return "/views/jugador/peorrankin";
		    
	    	
	    }
	    
	}

	
	
	
	
	
	
	
/*
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable("id") Long id, RedirectAttributes attribute, Locale locale){
		
		UsuarioDto usuariodto=null;
		
		if (id>0)
		{
			usuariodto = iUsuarioService.findById(id);
			
			if (usuariodto.equals(null)||(usuariodto.equals(Optional.empty()))) {
				String error = messageSource.getMessage("jugador.accion.eliminar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/jugador/listajugadores";
			}
		}else {
		
			String error = messageSource.getMessage("jugador.accion.eliminar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/jugador/listajugadores";
		}
		
		iUsuarioService.disableById(id);
		
		System.out.println("Registro eliminado con exito");
		String warning = messageSource.getMessage("jugador.accion.eliminar.exito", null, locale);
		attribute.addFlashAttribute("warning", warning);
		
		return "redirect:/views/jugador/listajugadores";
	
	}
	
	

	
	
	
	
*/
}
