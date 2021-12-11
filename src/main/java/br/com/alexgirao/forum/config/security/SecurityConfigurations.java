package br.com.alexgirao.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe de seguranca
 */
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	// configuracoes de autenticacao(Controle de acesso, login e etc.)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// apontar a classe com logica de autenticacao
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// configuracoes de autorizacao(Url, perfil e etc.)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// mapeando urls publicas
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		// autenticacao necessaria para todas as outras
		.anyRequest().authenticated()
		// formulario de autenticacao
		.and().formLogin();
	}

	// configuracoes de recursos estaticos(css, js, imgs e etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}
