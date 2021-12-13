package br.com.alexgirao.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe de seguranca
 */
@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations  extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// liberando todas as Url's
		.antMatchers("/**").permitAll()
		.and().csrf().disable();
	}

}