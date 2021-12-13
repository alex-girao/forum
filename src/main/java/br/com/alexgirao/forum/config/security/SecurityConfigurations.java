package br.com.alexgirao.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alexgirao.forum.repository.UsuarioRepository;

/**
 * Classe de seguranca
 */
@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		// url de autenticacao
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		// url para consumo do actuator
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		// somente moderadores podem remover topicos
		.antMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("MODERADOR")
		// autenticacao necessaria para todas as outras
		.anyRequest().authenticated()
		// formulario de autenticacao, mas neste caso utiliza sessao 
		//.and().formLogin()
		// desabilitando o CSRF, apis web não sofrem com esse tipo de ataque e essa validacao deve ser ignorada
		.and().csrf().disable()
		// o spring nao deve criar sessao na autenticacao
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// filtros: Primeiro o de verificacao do token e depois o de dados do usuario
		.and().addFilterBefore(
			new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), 
			UsernamePasswordAuthenticationFilter.class
		);
	}

	// configuracoes de recursos estaticos(css, js, imgs e etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		//ignorar as requisicoes do swagger
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
	// cofiguracao para injecao de dependencia do AutenticationManager
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
