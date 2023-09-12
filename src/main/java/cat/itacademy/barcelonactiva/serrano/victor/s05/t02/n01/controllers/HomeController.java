package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.controllers;




import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping
	@GetMapping("/")
	public String inicio(@AuthenticationPrincipal User user) {
		System.out.println("Usuario que hizo login: "+ user.getUsername());
		return "index";
	}
}