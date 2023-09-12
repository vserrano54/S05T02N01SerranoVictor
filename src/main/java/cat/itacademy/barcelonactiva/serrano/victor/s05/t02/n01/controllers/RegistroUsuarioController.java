package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioRegistroDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.UsuarioServiceImp;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
	
	private final UsuarioServiceImp usuarioServiceImp;

    
    public RegistroUsuarioController(UsuarioServiceImp usuarioServiceImp) {
        this.usuarioServiceImp = usuarioServiceImp;
    }

	
	//private UsuarioService usuarioService;
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDto retornarUsuarioNuevoDto() {
		return new UsuarioRegistroDto();
	}
	
	@GetMapping
	public String mostrarFormularioDeRegistro(Model model) {
		
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto resgistroDto) {
		
		/*
		LocalDate fechaActual = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        
        usuario.setFecha(fechaFormateada);
		
		*/
		Usuario usuario = usuarioServiceImp.save(resgistroDto);
		
		
		
		return "redirect:/registro?exito";
	}
}
