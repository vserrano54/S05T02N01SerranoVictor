package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.RolDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IRolService;

@Controller
//@Slf4j
@RequestMapping("/views/rol")
public class RolController {
	
	@Autowired
	private IRolService iRolService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/add")
	public String create (Model model, Locale locale){
		
		Rol rol = new Rol();
		
		String titulo = messageSource.getMessage("rol.nuevo.titulo", null, locale);
		
		model.addAttribute("titulo", titulo);
		model.addAttribute("rol",rol);
		return "/views/rol/crear";
	
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Rol rol, Errors errores, 
				Model model,RedirectAttributes attribute, Locale locale ) {
	
		System.out.println("Entro al procedimiento save");
		if (errores.hasErrors()) {
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
	}
	
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable("id") Long id, Model model, RedirectAttributes attribute, Locale locale){
		
		Optional<Rol> rol=null;
		
		if (id>0)
		{
			rol = iRolService.findById(id);
			
			if (rol.equals(null)||(rol.equals(Optional.empty()))) {
				System.out.println("El id de la sucursal no existe");
				String error = messageSource.getMessage("rol.accion.guardar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/rol/getAll";
			}
		}else {
			System.out.println("Error con el id de la rol");
			String error = messageSource.getMessage("rol.accion.guardar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/rol/getAll";
		}
		
		String titulo = messageSource.getMessage("rol.editar.titulo", null, locale);
		model.addAttribute("titulo", titulo);
		model.addAttribute("rol",rol);
		return "/views/rol/crear";
	
	}
	
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable("id") Long id, RedirectAttributes attribute, Locale locale){
		
		Optional<Rol> rol=null;
		
		if (id>0)
		{
			rol = iRolService.findById(id);
			
			if (rol.equals(null)||(rol.equals(Optional.empty()))) {
				String error = messageSource.getMessage("sucursal.accion.eliminar.errorNoExiste", null, locale);
				attribute.addFlashAttribute("error", error);
				return "redirect:/views/rol/getAll";
			}
		}else {
			System.out.println("Error con el id del rol");
			String error = messageSource.getMessage("rol.accion.eliminar.errorconid", null, locale);
			attribute.addFlashAttribute("error", error);
			return "redirect:/views/rol/getAll";
		}
		
		iRolService.deleteById(id);
		
		System.out.println("Registro eliminado con exito");
		String warning = messageSource.getMessage("rol.accion.eliminar.exito", null, locale);
		attribute.addFlashAttribute("warning", warning);
		
		return "redirect:/views/rol/getAll";
	
	}
	
	@GetMapping("/getAll")
	public String readAll(Model model, Locale locale) {
		
		List<Rol> rol = StreamSupport
				.stream(iRolService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		
		RolDto rolDto;
		List<RolDto> roles = new ArrayList<>();
		
		
		for (int i=0;i<rol.size();i++) {
			rolDto=new RolDto();
			
			rolDto.setId(rol.get(i).getId());
			rolDto.setDescripcion(rol.get(i).getDescripcion());
			
			roles.add(rolDto);
		}
		
		String titulo = messageSource.getMessage("rol.lista.titulo", null, locale);
		model.addAttribute("roles",roles);
		model.addAttribute("titulo",titulo);
		return "/views/rol/listar";
		
	}
	
	
	
	
	

}
