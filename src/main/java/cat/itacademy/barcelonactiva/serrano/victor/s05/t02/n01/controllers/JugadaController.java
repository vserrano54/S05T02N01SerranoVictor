package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;

import java.util.Locale;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Jugada;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Tirada;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.TiradaDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.dto.UsuarioDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IJugadaService;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.ITiradaService;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IUsuarioService;


@Controller
//@Slf4j
@RequestMapping("/views/jugada")
public class JugadaController {

	
	@Autowired
	private ITiradaService iTiradaService;
	
	@Autowired
	private IUsuarioService iUsuarioService;
	
	@Autowired
	private IJugadaService iJugadaService;
	
	@PostMapping("/save")
	@Transactional
	public String save(@Valid @ModelAttribute TiradaDto tiradadto, Errors errores, 
				Model model,RedirectAttributes attribute, Locale locale ) {
		
		System.out.println("Entra al procedimiento save\n\n\n\n");
		
		System.out.println("Id partida: " + tiradadto.getId());
		
		System.out.println("Dado 1 :" + tiradadto.getDado1());
		
		System.out.println("Dado 2 :" + tiradadto.getDado2());
		
		System.out.println("Usuario a buscar: " + tiradadto.getUsuario());
		
		System.out.println("DESPUES DE ENTRAR");

		Usuario usuario = iUsuarioService.findByUsername(tiradadto.getUsuario());
		
		System.out.println("Encontro el usuario: " + usuario.toString());
		
		System.out.println("Id del Usuario: " + usuario.getId());
		
		Jugada jugada = new Jugada();
		
		Tirada tirada = new Tirada();
		tirada.setDado1(tiradadto.getDado1());
		tirada.setDado2(tiradadto.getDado2());
		
		Tirada nuevaTirada = iTiradaService.save(tirada);
		
		if (nuevaTirada!=null) {
			
			jugada.setJugador(usuario.getId());
			jugada.setPartida(tiradadto.getId());
			jugada.setTirada(tirada.getId());
			
			Jugada nuevaJugada = iJugadaService.save(jugada);
			
			System.out.println("Graba la jugada");
		}
	
		return "redirect:/views/juego/getPartida/"+tiradadto.getId();
	}

}
