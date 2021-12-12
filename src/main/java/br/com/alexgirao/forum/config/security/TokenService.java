package br.com.alexgirao.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alexgirao.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
			// quem esta gerando o token
			.setIssuer("Api Forum")
			// dono do token(usuario autenticado), valor unico
			.setSubject(logado.getId().toString())
			// data de geracao
			.setIssuedAt(hoje)
			// data de validade 
			.setExpiration(dataExpiracao)
			// criptorgrafia
			.signWith(SignatureAlgorithm.HS256, secret)
			// transformar em string
			.compact()
		;
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
