package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S05T02N01SerranoVictorApplication {
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(S05T02N01SerranoVictorApplication.class, args);
	}

}
