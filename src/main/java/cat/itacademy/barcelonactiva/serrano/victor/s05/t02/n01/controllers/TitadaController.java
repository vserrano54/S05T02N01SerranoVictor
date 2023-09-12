package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

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

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Tirada;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.ITiradaService;

@Controller
//@Slf4j
@RequestMapping("/views/tirada")
public class TitadaController {
	
	
	@Autowired
	private ITiradaService iTiradaService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping("/delete/{id}/{iduser}")
	public String delete ( @PathVariable("id") Long id, @PathVariable("iduser") Long iduser, RedirectAttributes attribute, Locale locale){
		
		System.out.println("Entra a delete tirada");
		//System.out.println("iduser: " + iduser);
		
		Optional<Tirada> tirada=null;
		
		if (id>0)
		{
			tirada = iTiradaService.findById(id);
			
			if (tirada.equals(null)||(tirada.equals(Optional.empty()))) {
				String error = messageSource.getMessage("tirada.accion.eliminar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				//return "redirect:/views/jugador/getAllUserByRol";
				return "redirect:/views/jugador/getAllJugadasPorJugador/+{iduser}";
				
								
			}
		}else {
			System.out.println("Error con el id de la tirada");
			String error = messageSource.getMessage("tirada.accion.eliminar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/jugador/getAllJugadasPorJugador/+{iduser}";
		}
		
		iTiradaService.deleteByIdWithForeignKeyCheck(id);
		
		System.out.println("Registro eliminado con exito");
		String warning = messageSource.getMessage("tirada.accion.eliminar.exito", null, locale);
		attribute.addFlashAttribute("warning", warning);
		
		return "redirect:/views/jugador/getAllJugadasPorJugador/+{iduser}";
		
	
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Rol rol, Errors errores, 
				Model model,RedirectAttributes attribute, Locale locale ) {
	
		System.out.println("Entra a salvar la tirada");
	/*	if (errores.hasErrors()) {
			String titulo = messageSource.getMessage("rol.nuevo.titulo", null, locale);
			model.addAttribute("titulo", titulo);
			model.addAttribute("rol",rol);
			
			return "/views/rol/crear";
		}
		
		iRolService.save(rol);
		//System.out.println("Sucursal guardada con exito");
		String success = messageSource.getMessage("rol.accion.guardar.exito", null, locale);
		attribute.addFlashAttribute("success", success);
		return "redirect:/views/rol/getAll";
		
		*/
		return "index";
		
	}
}
