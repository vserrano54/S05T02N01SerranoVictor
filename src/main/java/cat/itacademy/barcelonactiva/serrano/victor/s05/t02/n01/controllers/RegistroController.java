package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IUsuarioService;

@Controller
public class RegistroController {
	
	@Autowired
	private IUsuarioService iUsuarioService; 
	
	@GetMapping("/login")
	public String iniciarSession() {
		System.out.println("entra a login");
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaInicio(Model modelo) {
		System.out.println("entra a ver pagina inicio");
	
		modelo.addAttribute("usuarios", iUsuarioService.listarUsuarios());
		
		 // List<Usuario> usuario = iUsuarioService.listarUsuarios();
	     //  System.out.println("Nombre: "+ usuario.get(0).getNombre() + " Id: "+ usuario.get(0).getId());
		return "index";
	}
	
	

}
