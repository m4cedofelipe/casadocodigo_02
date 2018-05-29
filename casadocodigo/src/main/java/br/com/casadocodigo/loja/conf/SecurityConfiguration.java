package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //proteção CSRF (Cross Site Request Forgery) em seu aplicativo Spring está ativada. Na verdade, está ativado por padrão.
			
		
		http.authorizeRequests()
	    .antMatchers("/resources/**").permitAll()
	    .antMatchers("/carrinho/**").permitAll()
	    .antMatchers("/pagamento/**").permitAll()
	    .antMatchers("/produtos/form").hasRole("ADMIN")
	    .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
	    .antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
	    .antMatchers("/produtos/**").permitAll()
	    .antMatchers("/").permitAll()
	    .anyRequest().authenticated()
	    .and().formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}