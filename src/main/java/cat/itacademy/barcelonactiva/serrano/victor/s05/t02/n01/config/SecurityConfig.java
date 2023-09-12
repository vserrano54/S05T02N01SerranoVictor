package cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.repository.UsuarioRepository;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t02.n01.model.services.IUsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
/*
	@Autowired
	private IUsuarioService iUsuarioService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	*/
	private IUsuarioService iUsuarioService;
 	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(IUsuarioService iUsuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

	
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(iUsuarioService);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/js/**",
				"/css/**",
				"/fontawesome/**",
				"/img/**",
				"/registro**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}
}