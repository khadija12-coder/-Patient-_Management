package emsi.ma.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // classes traiter au demarage de l'application priorité avec annotation Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // pour personnalisé spring security

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		
	  	PasswordEncoder passwordEncoder = passwordEncoder(); // bcrypte pour hacher les mots de passe 
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");
		
	
		
	}
	
	
	
	
	
	
	
	
	
    @Override
		protected void configure(HttpSecurity http) throws Exception {
		
    
  
	 	
		
		 // http.httpBasic(); // un formulaire d'anthentification basic le navigateur web qui vas se charger de l'afficher 
         http.formLogin(); // formulaire d'anthenfication par defaut par spring security
         // http.authorizeRequests().anyRequest().authenticated(); // toutes les requettes http necessite une authentification
         
         http.authorizeRequests().antMatchers("/save**/**","/delete**/**").hasRole("ADMIN");
		 http.authorizeRequests().anyRequest().authenticated(); 
		 http.csrf(); // activer le mécanisme contre les attaques de types csrf
		 
		 
		 
	}	

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
		
	}	
	
	
	
	
}













