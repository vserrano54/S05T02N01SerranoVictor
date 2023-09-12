package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

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

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.PartidaDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.TiradaDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IPartidaService;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IUsuarioService;

@Controller
//@Slf4j
@RequestMapping("/views/juego")
public class PartidaController {
	
	
	
	 @Autowired
	 private MessageSource messageSource;
	 
	 private final IPartidaService iPartidaService;
	 private IUsuarioService iUsuarioService; 

	    @Autowired
	    public PartidaController(IPartidaService iPartidaService) {
	        this.iPartidaService = iPartidaService;
	    }
	 
	 
	 @GetMapping("/getAll")
		public String readAll(Model model, Locale locale) {
			
			List<PartidaDto> partidas = (List<PartidaDto>) iPartidaService.findAll();
			
			
			String titulo = messageSource.getMessage("partida.lista.titulo", null, locale);
			model.addAttribute("partidas",partidas);
			model.addAttribute("titulo",titulo);
			return "/views/juego/partidas";
			
		}

	 @GetMapping("/getPartida/{id}")
	    public String mostrarFormulario(@PathVariable("id") int id, Model model, Locale locale) {
	        System.out.println("Entrando a mostrarFormulario");

	        Optional<Partida> optionalPartida = iPartidaService.findById(id);
	        String titulo = messageSource.getMessage("partida.nueva", null, locale);
	        
	        TiradaDto partida = new TiradaDto();
	        
	        
	     
	        
	        
	        if (optionalPartida.isPresent()) {
	            Partida opartida = optionalPartida.get();
	            
	            partida.setId(opartida.getId());
	            partida.setFecha(opartida.getFecha());
	            model.addAttribute("partida", partida);
	            model.addAttribute("titulo",titulo);
	          //  model.addAttribute("usuarios", iUsuarioService.listarUsuarios());
	            return "/views/juego/nueva"; // Suponiendo que "nueva" es el nombre de la vista Thymeleaf
	        } else {
	            // Manejo de partida no encontrada
	            return "partidaNoEncontrada"; // Puedes redirigir a una página de error personalizada
	        }
	    }

	
	 	@GetMapping("/savePartida")
		public String save(@ModelAttribute Partida partida, 
					Model model,RedirectAttributes attribute, Locale locale ) {
		
	    	LocalDate fecha= LocalDate.now(); 
	    	partida.setFecha(fecha);
	    	
	    	Partida partidaGuardada = iPartidaService.save(partida);
	    	
		
			//String success = messageSource.getMessage("rol.accion.guardar.exito", null, locale);
			//attribute.addFlashAttribute("success", success);
			return "redirect:/views/juego/getPartida/"+partidaGuardada.getId();
		}
	
	

}
